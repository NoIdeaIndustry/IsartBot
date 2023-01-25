package com.noideaindustry.isartbot.redis.utils;

public enum RedisKeys {
    STARBOARD("starboard:");

    private final String key;

    RedisKeys(final String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
