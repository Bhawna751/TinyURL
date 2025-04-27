package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

@Service
public class CounterService {
    private static final String COUNTER_KEY = "url:counter"; // store a continuously incrementing number in Redis.

    @Autowired
    private RedisTemplate<String, String> redisTemplate; //helper class to perform operations (get/set/delete) on Redis <key, value>

    public long getNextCount(){ //thread-safe counter
        RedisAtomicLong counter = new RedisAtomicLong(COUNTER_KEY, redisTemplate.getConnectionFactory()); // creates a distributed counter stored in COUNTER_KEY key
        return counter.incrementAndGet();//increments value at COUNTER_KEY by 1
    }
}
