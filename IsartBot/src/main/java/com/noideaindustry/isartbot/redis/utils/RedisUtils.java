package com.noideaindustry.isartbot.redis.utils;

import com.noideaindustry.isartbot.redis.RedisHandler;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisUtils {
    private final RedisHandler redisHandler;

    public RedisUtils(final RedisHandler redisHandler) {
        this.redisHandler = redisHandler;
    }

    public boolean setStarboardChannel(final String id, final String channelId) {
        try (final Jedis jedis = this.redisHandler.pool().getResource()) {
            return redisHandler.addValue(jedis, RedisKeys.STARBOARD, id, channelId);
        } catch (final JedisConnectionException e) {
            return false;
        }
    }

    public String getStarboardChannel(final String id) {
        try (final Jedis jedis = this.redisHandler.pool().getResource()) {
            return redisHandler.getValue(jedis, RedisKeys.STARBOARD, id);
        } catch (final JedisConnectionException e) {
            return null;
        }
    }
}
