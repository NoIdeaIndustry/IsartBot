package com.noideaindustry.isartbot.redis;

import com.noideaindustry.isartbot.redis.utils.RedisKeys;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

public record RedisHandler(JedisPool pool) {
    public boolean existValue(final Jedis jedis, final RedisKeys key, final String identity) {
        return jedis.exists(key.getKey() + identity);
    }

    public boolean deleteValue(final Jedis jedis, final RedisKeys key, final String identity) {
        try {
            if (!this.existValue(jedis, key, identity)) return false;

            jedis.del(key.getKey() + identity);
            return true;
        } catch (JedisConnectionException e) {
            return false;
        }
    }

    public boolean addValue(final Jedis jedis, final RedisKeys key, final String identity, final String content) {
        try {
            if (this.existValue(jedis, key, identity)) return false;
            jedis.set(key.getKey() + identity, content);
            return true;
        } catch (JedisConnectionException e) {
            return false;
        }
    }

    public boolean updateValue(final Jedis jedis, final RedisKeys key, final String identity, final String content) {
        try {
            if (!this.existValue(jedis, key, identity)) return false;

            jedis.set(key.getKey() + identity, content);
            return true;
        } catch (JedisConnectionException e) {
            return false;
        }
    }

    public String getValue(final Jedis jedis, final RedisKeys key, final String identity) {
        try {
            return jedis.get(key.getKey() + identity);
        } catch (JedisConnectionException e) {
            return null;
        }
    }
}
