package ht.statictrafficmanagement.base.communication.inner;

import ht.statictrafficmanagement.base.Message;

/**
 * 封装的Server对客户端消息的应答
 * Created by wudw on 2017/11/02.
 */
public class SentResponseMessage {
    private Message message;
    private String host;
    private int port;
    private int agvId;

    public SentResponseMessage() {
    }

    public int getAgvId() {
        return agvId;
    }

    //消息发送时间
    private long sendTime;

    public SentResponseMessage(Message message, int agvId, String host, int port) {
        this.message = message;
        this.host = host;
        this.port = port;
        this.agvId = agvId;
        sendTime = System.currentTimeMillis();
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }
}
