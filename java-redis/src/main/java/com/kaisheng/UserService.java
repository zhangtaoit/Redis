package com.kaisheng;

import com.kaisheng.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class UserService {


    private RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        //默认是以jdk序列化 改为string格式
       /* redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());*/

       //将一个对象存放redis中 序列化json存入redis中
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
    }

    // spring date redis 连接
    public void springRedisTest () {
        //redisTemplate.opsForValue().set("name:1","hello");
        //将user对象放入redis中
        User user = new User(101,"jack",88.8F);
        redisTemplate.opsForValue().set("user:101",user);

    }

    public String getSpringRedisTest() {
        //return (String) redisTemplate.opsForValue().get("name:1");

        User user = (User) redisTemplate.opsForValue().get("user:101");
        System.out.println(user);
        return null;
    }


   // spring 连接
    /*@Autowired
    private JedisPool jedisPool;
    public void springRedisTest () {
        Jedis jedis = jedisPool.getResource();
        jedis.set("names","jack");
        jedis.close();
    }*/
}
