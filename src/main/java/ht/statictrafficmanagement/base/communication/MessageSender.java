package ht.statictrafficmanagement.base.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ht.statictrafficmanagement.base.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 消息发送接口
 * Created by wudw on 2017/11/02.
 */

public class MessageSender implements ISender {
    private DatagramSocket socket;
    @Autowired
    MessageHelper messageHelper;
    //@Autowired
    Configure configure = new Configure();
    int SEND_PACKET_MAX_LEN;
    Lock lock = new ReentrantLock();
    private static Logger logger = LoggerFactory.getLogger(MessageSender.class);

    public MessageSender() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            logger.error("Create UDP Data Gram Socket Failed,System Exit!!! Error Message : {}", e.getMessage(), e);
            System.exit(1);
        }
    }


    /**
     * 使用UDP发送消息,发送失败后需要重发，主要用于Server主动发送的消息类型
     * 例如：任务消息、地图消息
     *
     * @param message       : 需要发送的消息
     * @param agvId         : 发送目的标识
     * @param host          : 发送目的地址
     * @param port          : 发送目的端口号
     * @param failedHandler : 发送失败处理回调接口
     * @return
     */
    public boolean sendMessage(Message message, int agvId, String host, int port, int resendCount, IMessageFailedHandler failedHandler) {
        boolean res = false;
        try {
            if (send(message, host, port)) {
                logger.debug("Send Message To Agv-{} Success : {}", agvId, message.toString());
                res = true;
            } else {
                logger.error("Send Message to Agv-{} Failed!!!! Message is {}", agvId, message);
            }
        } catch (IOException e) {
            logger.error("Send Message to Agv-{} Failed,Catch Exception : {} !!!! Message is {}", agvId, e.getMessage(), message, e);
        }
        try {
            lock.lock();
            messageHelper.addMessage(message, agvId, host, port, resendCount, failedHandler);
        } finally {
            lock.unlock();
        }
        return res;
    }

    public boolean send(Message message, String host, int port) throws IOException {
        SEND_PACKET_MAX_LEN = configure.getSend_packet_max_length() - 100;
        InetAddress address = InetAddress.getByName(host);
        if (port > 65535 || port < 0) {
            logger.error("Port out of range:{}", port);
            return false;
        }
        byte[] data;

        byte[] serialized = message.encode();
        System.out.println(serialized.length);
        //数据包大小小于SEND_PACKET_MAX_LEN，不拆包，直接发送
        if (serialized.length < SEND_PACKET_MAX_LEN) {
        	System.out.println(SEND_PACKET_MAX_LEN);
            ByteBuffer byteBuf = ByteBuffer.allocate(serialized.length + 21);
            byteBuf.put(message.getMessageType());
            int payloadLength = serialized.length;
            byteBuf.putInt(payloadLength);
            byteBuf.putLong(message.getMessageId());
            byteBuf.putInt(1);
            byteBuf.putInt(1);
            byteBuf.put(serialized);
            data = new byte[byteBuf.position()];
            byteBuf.flip();
            byteBuf.get(data);
            send(data, address, port);
        } else {
        	System.out.println("1#"+SEND_PACKET_MAX_LEN);
            int packetNum = serialized.length / SEND_PACKET_MAX_LEN;
            if (serialized.length % SEND_PACKET_MAX_LEN > 0) {
                packetNum++;
                System.out.println("2#"+packetNum);
            }
            for (int i = 1; i < packetNum; i++) {
                byte[] bytes = new byte[SEND_PACKET_MAX_LEN];
                System.arraycopy(serialized, SEND_PACKET_MAX_LEN * (i - 1), bytes, 0, SEND_PACKET_MAX_LEN);

                ByteBuffer byteBuf = ByteBuffer.allocate(SEND_PACKET_MAX_LEN + 21);
                byteBuf.put(message.getMessageType());
                byteBuf.putInt(SEND_PACKET_MAX_LEN);
                byteBuf.putLong(message.getMessageId());
                byteBuf.putInt(packetNum);
                System.out.println(packetNum);
                byteBuf.putInt(i);
                System.out.println(i);
                byteBuf.put(bytes);

                data = new byte[byteBuf.position()];
                byteBuf.flip();
                byteBuf.get(data);
                send(data, address, port);
            }
            int remainDataLength = serialized.length - (packetNum - 1) * SEND_PACKET_MAX_LEN;
            byte[] bytes = new byte[remainDataLength];
            System.arraycopy(serialized, SEND_PACKET_MAX_LEN * (packetNum - 1), bytes, 0, remainDataLength);
            ByteBuffer byteBuf = ByteBuffer.allocate(remainDataLength + 21);
            byteBuf.put(message.getMessageType());
            int payloadLength = remainDataLength;
            byteBuf.putInt(payloadLength);
            byteBuf.putLong(message.getMessageId());
            byteBuf.putInt(packetNum);
            byteBuf.putInt(packetNum);
            byteBuf.put(bytes);
            data = new byte[byteBuf.position()];
            byteBuf.flip();
            byteBuf.get(data);
            send(data, address, port);
        }
        return true;
    }


    private void send(byte[] data, InetAddress address, int port) throws IOException {
        DatagramPacket packet = new DatagramPacket(data, data.length);
        packet.setAddress(address);
        packet.setPort(port);
        socket.send(packet);
    }

    /**
     * 使用UDP发送消息，主要用于发送响应消息（不需要重发）
     * 消息类型：事件响应消息、握手应答消息，片段预留应答消息、路径规划应答消息
     *
     * @param message : 需要发送的消息
     * @param host    : 发送目的地址
     * @param port    : 发送目的端口号
     * @return
     */
    public boolean sendMessage(Message message, int agvId, String host, int port) {
        try {
            logger.debug("Send Message To Agv-{}: {}", agvId, message);
            boolean success = send(message, host, port);
            if (success) {
                messageHelper.addNoResponseMessage(message, agvId, host, port);
            }
            return success;

        } catch (IOException e) {
            return false;
        }
    }
}
