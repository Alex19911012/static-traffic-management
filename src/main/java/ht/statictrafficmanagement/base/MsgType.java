package ht.statictrafficmanagement.base;

/**
 * 消息类型定义
 * @author shuangjiaxu
 *
 */
public interface MsgType {
	/*
     * 状态消息
     */
    byte AGV_STATUS = 1;
    /*
     * AGV事件消息
     */
    byte AGV_EVENT = 2;
    /*
     *  AGV事件应答
     */
    byte AGV_EVNET_RESPONSE = 3;
    /*
     * 握手请求
     */
    byte HAND_REQUEST = 4;
    /*
     * 握手应答
     */
    byte HAND_RESPONSE = 5;
    /*
     * 地图消息
     */
    byte MAP_INFO = 6;
    /*
     * 地图应答
     */
    byte MAP_INFO_RESPONSE=7;
    /*
     * 任务消息
     */
    byte TASK = 8;
    /*
     * 任务确认消息
     */
    byte TASK_RESPONSE=9;
    /*
     * 路径请求消息
     */
    byte PATH_REQUEST=10;
    /*
     * 路径应答消息
     */
    byte PATH_RESPONSE=11;
    /*
     * 预约请求消息
     */
    byte RESERVE_REQUEST =12;
    /*
     * 预约应答消息
     */
    byte RESERVE_RESPONSE = 13;
    /**
     * Server事件消息
      */
    byte SERVER_EVENT = 14;
    /**
     * Server事件应答消息
     */
    byte SERVER_EVENT_RESPONSE=15;
    /**
     * 手动控制消息
     */
    byte MANUAL_CONTROL=16;

    /**
     * 状态消息回复
     */
    byte STATUS_RESPONSE = 19;

    /**
     * 功能点
     */
    byte ACTION_NODE_INFO = 101;
    /**
     * 磁钉点
     */
    byte MAG_NODE_INFO = 102;
    /**
     * 片段
     */
    byte SEGMENT_INFO = 103;
    /**
     * 节点
     */
    byte NODE_INFO = 104;
    /**
     * AGV信息
     */
    byte AGV_INFO = 105;
    /**
     * 单个任务
     */
    byte ROUTE_TASK_INFO=106;

}
