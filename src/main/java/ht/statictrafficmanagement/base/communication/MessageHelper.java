package ht.statictrafficmanagement.base.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ht.statictrafficmanagement.base.Message;
import ht.statictrafficmanagement.base.communication.inner.Cache;
import ht.statictrafficmanagement.base.communication.inner.SentMessage;
import ht.statictrafficmanagement.base.communication.inner.SentResponseMessage;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 记录已发送的消息，当收到消息确认后，删除该消息
 * 当超时未收到消息确认，再次发送该消息
 * 记录已接收消息，再次接收到该消息之后的处理（预留）
 * Created by wudw on 2017/11/02.
 */
@Component
public class MessageHelper {
    private static Logger logger = LoggerFactory.getLogger(MessageHelper.class);
    @Autowired
    Cache cache;
    @Autowired
    Configure configure;
    int resendMessageInterval;

    ISender messageSender;

    private volatile boolean isAddResponseMessage = false;
    private Lock lock1 = new ReentrantLock();

    private volatile boolean isAddNeedResponseMessage = false;
    private Lock lock = new ReentrantLock();
    @Qualifier("default")
    IMessageFailedHandler sendFailedHandler;

    /**
     * 记录可能需要重复应答的响应消息（当收到同一个消息ID的请求时，直接从缓存中获取响应消息重新发送即可）
     *
     * @param message
     * @param host
     * @param port
     */
    void addNoResponseMessage(Message message, int agvId, String host, int port) {
        if (message instanceof NeedResendResponseMessage) {
            String key = getKey(message);
            cache.addSentResponseMessage(key, new SentResponseMessage(message, agvId, host, port));
            if (isAddResponseMessage == false) {
                try {
                    lock1.lock();
                    if (!isAddResponseMessage) {
                        Thread clearCacheThread = new ClearCache();
                        clearCacheThread.setName("Cache Clear");
                        clearCacheThread.start();
                    }
                } finally {
                    lock1.unlock();
                }

                isAddResponseMessage = true;
            }
        }
    }

    /**
     * 在将消息分发给业务模块前，先处理一次，判断该消息是否发送过回复
     *
     * @return true : 接收到的消息已回复
     * false ： 接收到的消息未回复
     */
    boolean handlerMessage(Message message) {
        if (cache.isMessageHasResponse(getKey(message))) {
            SentResponseMessage sentResponseMessage = cache.getSentResponseMessage(getKey(message));
            try {
                ((MessageSender) messageSender).send(sentResponseMessage.getMessage(), sentResponseMessage.getHost(), sentResponseMessage.getPort());
                sentResponseMessage.setSendTime(System.currentTimeMillis());
                cache.addSentResponseMessage(getKey(message), sentResponseMessage);
                logger.info("Receive Request Message Again,Resend Response Message To Agv-{},Message : {}", sentResponseMessage.getAgvId(), sentResponseMessage.getMessage());
                return true;
            } catch (IOException e) {
                return false;
            }
        } else {
            return false;
        }

    }

    //记录需要重新发送的消息和需要应答的消息
    void addMessage(Message message, int agvId, String host, int port, int resendCount, IMessageFailedHandler failedHandler) {
        //根据类型判断是否需要重新发送，只记录超时需要重新发送的消息
        assert message != null;
        if (message instanceof NeedConfirmMessage) {
            if (resendCount > 1) {
                SentMessage sentMessage = new SentMessage(message, agvId, host, port, resendCount, failedHandler);
                cache.addSentMessage(message.getMessageId(), sentMessage);
            }
        }
        if (isAddNeedResponseMessage == false) {
            try {
                lock.lock();
                if (!isAddNeedResponseMessage) {
                    Thread timeoutThread = new ResendMessage();
                    timeoutThread.setName("Message Resend");
                    timeoutThread.start();
                }
            } finally {
                lock.unlock();
            }


        }
        isAddNeedResponseMessage = true;
    }

    /**
     * 根据messageId删除消息
     *
     * @param messageId    ： 消息对应的ID
     * @param isSendFailed ： 是否是因为连续3次或以上发送失败的删除,如果是，考虑通知发送方
     */
    void removeMessage(long messageId, boolean isSendFailed) {
        //将messageId对应的消息删除
        SentMessage sentMessage = cache.removeSentMessageByKey(messageId);
        if (sentMessage != null && isSendFailed) {
            IMessageFailedHandler messageFailedHandler = sentMessage.getFailedHandler();
            if (messageFailedHandler == null) {
                logger.info("Message Failed Handler is Null From Cache");
                sendFailedHandler.handlerMessageSendFailed(sentMessage.getMessage(), sentMessage.getAgvId());
            } else {
                messageFailedHandler.handlerMessageSendFailed(sentMessage.getMessage(), sentMessage.getAgvId());
            }

        }
    }

    /**
     * 认为AGV关机，清理发送给AGV的消息
     *
     * @param agvId
     */
    public void removeMessage(int agvId) {
        for (String key : cache.getAllKeyOfSentResponseMessage()) {
            SentResponseMessage sentResponseMessage = cache.getSentResponseMessage(key);
            if (sentResponseMessage != null && sentResponseMessage.getAgvId() == agvId) {
                cache.removeSentResponseMessageByKey(key);
            }
        }
    }

    private String getKey(Message message) {
        byte prev;
        if (message instanceof NeedResendResponseMessage) {
            prev = ((NeedResendResponseMessage) message).getRequestType();
        } else {
            prev = message.getMessageType();
        }
        return prev + "-" + message.getMessageId();
    }

    /**
     * 确认消息对方已接收，删除缓存中的消息，不再重发
     *
     * @param messageId : 消息ID
     */
    public void handlerResponseMessage(Long messageId) {
        removeMessage(messageId, false);
    }

    /**
     * 清理发送的响应消息
     */
    private class ClearCache extends Thread {
        @Override
        public void run() {
            int timeIntervalForDelete = configure.getClear_sent_message_interval() * 1000;
            logger.info("[Config] - [clear_sent_message_interval] = {} Second", timeIntervalForDelete / 1000);
            while (true) {
                try {
                    long currentTime = System.currentTimeMillis();
                    for (String key : cache.getAllKeyOfSentResponseMessage()) {
                        SentResponseMessage sentResponseMessage = cache.getSentResponseMessage(key);
                        if (sentResponseMessage != null && (currentTime - sentResponseMessage.getSendTime() > timeIntervalForDelete)) {
                            cache.removeSentResponseMessageByKey(key);
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } catch (Exception e) {
                    logger.error("Catch Exception : {}", e.getMessage(), e);
                }

            }
        }
    }

    /**
     * 重发消息
     */
    private class ResendMessage extends Thread {
        @Override
        public void run() {
            resendMessageInterval = configure.getResend_message_interval() * 1000;
            logger.info("[Config] - [resend_message_interval] = {}", resendMessageInterval);
            while (true) {
                try {
                    long currentTime = System.currentTimeMillis();
                    for (long messageId : cache.getAllKeyOfSentMessage()) {
                        SentMessage message = cache.getSentMessage(messageId);
                        if (message != null) {
                            if (message.getSendCount() > message.getMaxResendCount()) {
                                removeMessage(messageId, true);
                                continue;
                            } else {
                                if (currentTime - message.getSendTime() > resendMessageInterval) {
                                    try {
                                        ((MessageSender) messageSender).send(message.getMessage(), message.getHost(), message.getPort());
                                        message.setSendTime(System.currentTimeMillis());
                                        message.increaseCount();
                                        cache.addSentMessage(messageId, message);
                                    } catch (IOException e) {
                                        logger.error("Resend Message-{} Catch Exception,Error Message:{}", message.getMessageId(), e.getMessage(), e);
                                    }
                                }
                            }
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } catch (Exception e) {
                    logger.error("Catch Exception :{}", e.getMessage(), e);
                }

            }
        }
    }

}
