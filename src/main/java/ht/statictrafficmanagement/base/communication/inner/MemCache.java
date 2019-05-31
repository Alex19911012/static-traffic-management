package ht.statictrafficmanagement.base.communication.inner;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wudw on 2017/11/02.
 */
@Component
public class MemCache implements Cache{
    /**
     * 保存在接收数据时被拆包的数据包
     */
    private Map<String, CacheMessage[]> buffer = new ConcurrentHashMap<>();

    /**
     * 保存已发送的需要AGV应答的消息
     */
    Map<Long, SentMessage> sentMessageMap = new ConcurrentHashMap<>();

    /*
     *  记录已发送的给AGV的应答消息,再次收到messageId相同的请求时重复发送应答即可
     */
    Map<String, SentResponseMessage> sentResponseMessageMap = new ConcurrentHashMap<>();


    @Override
    public void addSentResponseMessage(String key, SentResponseMessage message) {
        sentResponseMessageMap.put(key,message);
    }

    @Override
    public boolean isMessageHasResponse(String key) {
        return sentResponseMessageMap.containsKey(key);
    }

    @Override
    public SentResponseMessage getSentResponseMessage(String key) {
        return sentResponseMessageMap.get(key);
    }

    @Override
    public Set<String> getAllKeyOfSentResponseMessage() {
        return sentResponseMessageMap.keySet();
    }

    @Override
    public void removeSentResponseMessageByKey(String key) {
        sentResponseMessageMap.remove(key);
    }


    @Override
    public void addSentMessage(long messageId, SentMessage sentMessage) {
        sentMessageMap.put(messageId,sentMessage);
    }

    @Override
    public SentMessage getSentMessage(long messageId) {
        return sentMessageMap.get(messageId);
    }

    @Override
    public Set<Long> getAllKeyOfSentMessage() {
        return sentMessageMap.keySet();
    }

    @Override
    public SentMessage removeSentMessageByKey(long key) {
        return sentMessageMap.remove(key);
    }

    @Override
    public CacheMessage[] getCacheMessageArray(String key) {
        return buffer.get(key);
    }

    @Override
    public void putCacheMessageArray(String key, CacheMessage[] cacheMessages) {
        buffer.put(key,cacheMessages);
    }

    @Override
    public CacheMessage[] removeCacheMessageArray(String key) {
        return buffer.remove(key);
    }
}

