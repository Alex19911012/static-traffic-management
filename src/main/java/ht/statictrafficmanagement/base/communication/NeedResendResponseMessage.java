package ht.statictrafficmanagement.base.communication;

/**
 * 标识需要重复发送的响应消息（例如当收到同一个messageId的请求时，如果是已响应过的消息，只需将原响应的消息重发就行）
 * Created by wudw on 2017/11/11.
 */
public interface NeedResendResponseMessage {
    //标识对应是哪一类消息的响应
    byte getRequestType();
}
