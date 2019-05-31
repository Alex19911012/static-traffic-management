package ht.statictrafficmanagement.base.communication.inner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wudw on 2017/11/02.
 */
@Component
@Profile("withRedis")
@Primary
public class RedisCache implements Cache {
    @Autowired
    RedisTemplate redisTemplate;
    private final static String SENT_MESSAGE = "SENT_RESPONSE_MESSAGE";
    private final static String SENT_RESPONSE_MESSAGE = "SENT_MESSAGE";
    private final static String RECEIVE_PACKET = "RECEIVE_PACKET";

    @Override
    public void addSentResponseMessage(String key, SentResponseMessage sentResponseMessage) {
        redisTemplate.opsForHash().put(SENT_RESPONSE_MESSAGE, key, sentResponseMessage);
    }

    @Override
    public boolean isMessageHasResponse(String key) {
        return redisTemplate.opsForHash().hasKey(SENT_RESPONSE_MESSAGE, key);
    }

    @Override
    public SentResponseMessage getSentResponseMessage(String key) {
        return (SentResponseMessage) redisTemplate.opsForHash().get(SENT_RESPONSE_MESSAGE, key);
    }

    @Override
    public Set<String> getAllKeyOfSentResponseMessage() {
        return redisTemplate.opsForHash().keys(SENT_RESPONSE_MESSAGE);
    }

    @Override
    public void removeSentResponseMessageByKey(String key) {
        redisTemplate.opsForHash().delete(SENT_RESPONSE_MESSAGE, key);
    }

    @Override
    public void addSentMessage(long messageId, SentMessage sentMessage) {
        redisTemplate.opsForHash().put(SENT_MESSAGE, String.valueOf(messageId), sentMessage);
    }

    @Override
    public SentMessage getSentMessage(long messageId) {
        return (SentMessage) redisTemplate.opsForHash().get(SENT_MESSAGE, String.valueOf(messageId));
    }

    @Override
    public Set<Long> getAllKeyOfSentMessage() {
        Set<String> keys = redisTemplate.opsForHash().keys(SENT_MESSAGE);
        Set<Long> res = new HashSet<>();
        for (String key : keys) {
            try {
                res.add(Long.valueOf(key));
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return res;
    }

    @Override
    public SentMessage removeSentMessageByKey(long messageId) {
        SentMessage message = (SentMessage) redisTemplate.opsForHash().get(SENT_MESSAGE, String.valueOf(messageId));
        redisTemplate.opsForHash().delete(SENT_MESSAGE, String.valueOf(messageId));
        return message;
    }

    @Override
    public CacheMessage[] getCacheMessageArray(String key) {
        return (CacheMessage[]) redisTemplate.opsForHash().get(RECEIVE_PACKET, key);
    }

    @Override
    public void putCacheMessageArray(String key, CacheMessage[] cacheMessages) {
        redisTemplate.opsForHash().put(RECEIVE_PACKET, key, cacheMessages);
    }

    @Override
    public CacheMessage[] removeCacheMessageArray(String key) {
        CacheMessage[] result = (CacheMessage[]) redisTemplate.opsForHash().get(RECEIVE_PACKET, key);
        redisTemplate.opsForHash().delete(RECEIVE_PACKET, key);
        return result;
    }
}

