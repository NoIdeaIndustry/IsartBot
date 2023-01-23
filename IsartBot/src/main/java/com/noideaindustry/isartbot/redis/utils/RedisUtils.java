package com.noideaindustry.isartbot.redis.utils;

import com.google.gson.JsonObject;
import com.noideaindustry.isartbot.redis.RedisHandler;
import com.noideaindustry.isartbot.utils.ConstantsUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisUtils {
    private final RedisHandler redisHandler;

    public RedisUtils(final RedisHandler redisHandler) {
        this.redisHandler = redisHandler;
    }

    public JsonObject getFoodTrucks() {
        try (final Jedis jedis = this.redisHandler.pool().getResource()) {
            return ConstantsUtils.GSON.fromJson(redisHandler.getValue(jedis, RedisKeys.FOOD_TRUCK, "food"), JsonObject.class);
        } catch (final JedisConnectionException e) {
            return null;
        }
    }
}
