package ht.statictrafficmanagement.base.communication.inner;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ht.statictrafficmanagement.base.Message;
import ht.statictrafficmanagement.base.communication.IMessageFailedHandler;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 封装发送的需要应答的消息(有Server主动向其它客户端发起)
 * Created by wudw on 2017/11/02.
 */
public class SentMessage {
    //发送的消息
    private Message message;
    //消息发送目的地址
    private String host;
    //消息接收方端口号
    private int port;
    //消息ID
    private long messageId;
    @JsonIgnore
    private IMessageFailedHandler failedHandler;
    //消息发送时间
    private long sendTime;
    //消息发送次数,当超过设定的次数时，认为该消息发送失败，停止发送
    private int sendCount;
    //消息最大发送次数
    private int maxResendCount;
    private int agvId;
    @JsonIgnore
    Lock lock;

    public SentMessage() {
        lock = new ReentrantLock();
    }

    public SentMessage(Message message, int agvId, String host, int port, int maxResendCount, IMessageFailedHandler failedHandler) {
        this.message = message;
        this.host = host;
        this.port = port;
        this.agvId = agvId;
        this.failedHandler = failedHandler;
        this.messageId = message.getMessageId();
        sendTime = System.currentTimeMillis();
        this.maxResendCount = maxResendCount < 1 ? 1 : maxResendCount;
        sendCount = 1;
        lock = new ReentrantLock();
    }

    public int getAgvId() {
        return agvId;
    }

    public Message getMessage() {
        return message;
    }

    public long getMessageId() {
        return messageId;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public void increaseCount() {
        try{
            lock.lock();
            sendCount++;
        }finally {
            lock.unlock();
        }
    }

    public String getHost() {
        return host;
    }


    public int getPort() {
        return port;
    }


    public int getSendCount() {
        return sendCount;
    }

    public int getMaxResendCount() {
        return maxResendCount;
    }

    public IMessageFailedHandler getFailedHandler() {
        return failedHandler;
    }
}
