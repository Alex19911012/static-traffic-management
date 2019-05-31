package ht.statictrafficmanagement.base.communication;


import ht.statictrafficmanagement.base.Message;

/**
 * Created by wudw on 2017/11/02.
 */

public interface IMessageFailedHandler {
    /**
     * 消息发送失败回调
     * @param message : 发送的消息内容
     * @param agvId :  消息接收标识
     */
    void handlerMessageSendFailed(Message message, int agvId);

}
