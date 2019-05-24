package ht.statictrafficmanagement.base;

/**
 * 消息接口
 * @author Alex
 *
 */
public interface Message {
	void decode(byte[] bytes);
    byte[] encode();
    byte getMessageType();
    long getMessageId();

}
