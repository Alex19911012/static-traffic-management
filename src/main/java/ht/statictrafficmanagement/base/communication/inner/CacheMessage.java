package ht.statictrafficmanagement.base.communication.inner;

import java.nio.ByteBuffer;

/**
 * 封装拆包消息
 * Created by wudw on 2017/11/02.
 */
public class CacheMessage {
    private byte messageType;
    private Long messageId;
    private int messageNum;
    private int messageIndex;
    private byte[] data;

    public CacheMessage() {

    }

    public CacheMessage(byte[] bytes) {
        ByteBuffer byteBuf = ByteBuffer.allocate(bytes.length);
        byteBuf.put(bytes);
        byteBuf.flip();

        messageType = byteBuf.get();
        int payloadLength = byteBuf.getInt();
        messageId = byteBuf.getLong();
        messageNum = byteBuf.getInt();
        messageIndex = byteBuf.getInt();
        data = new byte[payloadLength];
        byteBuf.get(data);
    }

    public byte getMessageType() {
        return messageType;
    }

    public Long getMessageId() {
        return messageId;
    }

    public int getMessageNum() {
        return messageNum;
    }

    public int getMessageIndex() {
        return messageIndex;
    }

    public byte[] getData() {
        return data;
    }

    public void setMessageType(byte messageType) {
        this.messageType = messageType;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    public void setMessageIndex(int messageIndex) {
        this.messageIndex = messageIndex;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
