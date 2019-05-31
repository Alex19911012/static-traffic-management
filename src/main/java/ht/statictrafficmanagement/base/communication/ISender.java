package ht.statictrafficmanagement.base.communication;

import ht.statictrafficmanagement.base.Message;

/**
	 * 消息发送接口
	 * Created by wudw on 2017/11/02.
	 */
	public interface ISender {
	    /**
	     * 发送消息
	     * @param message ： 消息内容
	     * @param agvId  : 消息接收标识
	     * @param host ： 接收方ip
	     * @param port ： 接收方端口号
	     * @param resendCount : 无应答重发次数
	     * @param failedHandler ： 连续重发后无应答的处理回调
	     * @return true : 发送成功
	     *         false ：发送失败
	     */
	    boolean sendMessage(Message message, int agvId, String host, int port, int resendCount, IMessageFailedHandler failedHandler);
	    
	    /**
	     * 发送消息,
	     * @param message ： 消息内容
	     * @param agvId : 接收方标识
	     * @param host ： 接收方ip
	     * @param port ： 接收方端口号
	     * @return true : 发送成功
	     *         false ：发送失败
	     */
	    boolean sendMessage(Message message, int agvId, String host, int port);
	}

