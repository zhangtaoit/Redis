package com.kaisheng;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class ApplicationContent {

    public JedisConnectionFactory   jedisConnectionFactory () {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("119.29.78.223");
        return jedisConnectionFactory;
    }

    public RedisTemplate redisTemplate() {
        RedisTemplate<byte[],byte[]> template = new RedisTemplate<byte[], byte[]>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
