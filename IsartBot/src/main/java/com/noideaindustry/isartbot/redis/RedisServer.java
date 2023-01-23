package com.noideaindustry.isartbot.redis;

import redis.clients.jedis.JedisPool;

public class RedisServer {
    private final String name;
    private final int port;
    private final JedisPool pool;

    public RedisServer(final String name, final int port) {
        this.name = name;
        this.port = port;

        this.pool = new JedisPool(this.name, this.port);
    }

    public JedisPool getPool() {
        return this.pool;
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }
}