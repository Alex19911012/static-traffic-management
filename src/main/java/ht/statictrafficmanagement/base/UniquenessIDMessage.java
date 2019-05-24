package ht.statictrafficmanagement.base;

/**
 * 拥有唯一标识的消息类型
 * @author shuangjiaxu
 *
 */
public abstract class UniquenessIDMessage implements Message{
	protected long messageId;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "UniquenessIDMessage{" +
                "messageId=" + messageId +
                '}';
    }
}
