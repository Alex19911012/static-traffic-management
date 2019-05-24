package ht.statictrafficmanagement.base;

/**
 * 无唯一标识消息类型
 * @author Alex
 *
 */
public abstract class NotUniquenIDMessage implements Message{
	 @Override
	    public long getMessageId() {
	        return -1;
	    }
	
}
