package com.ropulva.CalendarManagement.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisConnectionTest implements CommandLineRunner {

    private final StringRedisTemplate redisTemplate;

    public RedisConnectionTest(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // Attempt to set and get a key
        redisTemplate.opsForValue().set("testKey", "testValue");
        String value = redisTemplate.opsForValue().get("testKey");
        System.out.println("Successfully connected to Redis and retrieved key: " + value);
    }
}
