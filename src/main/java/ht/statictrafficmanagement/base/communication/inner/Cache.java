package ht.statictrafficmanagement.base.communication.inner;

import java.util.Set;

/**
 * 定义缓存，主要保存接收、发送消息相关的缓存信息，包括接收消息时的拆包、发送消息时的重发、处理消息时的重发响应
 * Created by wudw on 2017/11/02.
 */

public interface Cache {
    /**
     * 记录发送的应答响应消息（基于同一个messageId的请求消息只处理一次，每次请求的应答应该是一致的）
     * @param key : 消息Key值
     * @param sentResponseMessage : 发送的封装应答消息
     */
    void addSentResponseMessage(String key, SentResponseMessage sentResponseMessage);

    /**
     * 消息是否已经响应过
     * @param key ： 消息对应的Key值
     * @return true : 已经响应
     *          false ：没有响应过
     */
    boolean isMessageHasResponse(String key);

    /**
     * 根据消息Key值获取发送的消息
     * @param key : 消息Key值
     * @return : 对应的封装消息，如果没有对应的Key值，返回Null
     */
    SentResponseMessage getSentResponseMessage(String key);

    /**
     * 获得所有的应答响应消息的Key值
     * @return Key值组成的Set
     */
    Set<String> getAllKeyOfSentResponseMessage();

    /**
     * 根据消息Key值删除发送过的响应消息
     * @param key : 消息Key值
     */
    void removeSentResponseMessageByKey(String key);


    /**
     * 处理Server消息的重发，对Server主动发送给AGV的消息并要求应答的消息进行缓存，
     * 未收到应答时以固定间隔循环发送，直到收到应答
     * 记录发送的消息（主动发送，非响应消息）
     * @param messageId : 发送消息ID
     * @param sentMessage ： 封装的消息实体
     */
    void addSentMessage(long messageId, SentMessage sentMessage);

    /**
     * 根据消息ID获取发送的消息
     * @param messageId
     * @return
     */
    SentMessage getSentMessage(long messageId);

    /**
     * 获得所有的消息ID
     * @return 消息ID的集合
     */
    Set<Long> getAllKeyOfSentMessage();

    /**
     * 根据消息ID删除发送的消息
     * @param messageId
     * @return
     */
    SentMessage removeSentMessageByKey(long messageId);


    /**
     * 处理接收消息时消息被拆包后数据包的缓存信息
     * 根据消息Key值获得拆包消息
     * @param key 消息Key值
     * @return 拆包消息数组
     */
    CacheMessage[] getCacheMessageArray(String key);

    /**
     * 记录拆包消息
     * @param key ： 消息Key值
     * @param cacheMessages ：Key值对应的拆包消息数组
     */
    void putCacheMessageArray(String key, CacheMessage[] cacheMessages);

    /**
     * 从缓存中删除消息Key值对应的拆包消息
     * @param key ：消息Key值
     * @return ：Key值对应的拆包消息
     */
    CacheMessage[] removeCacheMessageArray(String key);

}
