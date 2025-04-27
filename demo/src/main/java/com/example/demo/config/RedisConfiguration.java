package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration //defines beans
public class RedisConfiguration {
    @Bean //tells spring to manage the returned object as a bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory){ //RedisConnectionFactory - manages connections to redis
        RedisTemplate<String,String> template = new RedisTemplate<>();//new instance
        template.setConnectionFactory(connectionFactory);//links the template to the redis server
        template.setKeySerializer(new StringRedisSerializer());//converts redis keys (always String) to byte arrays for storage
        template.setValueSerializer(new GenericToStringSerializer<>(Long.class));//converts the counter value (Long) to String before storing in Redis
        return template;
    }
}
