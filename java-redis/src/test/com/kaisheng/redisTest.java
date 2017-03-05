package com.kaisheng;

import com.kaisheng.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class redisTest {

    @Autowired
    private UserService userService;

    @Test
    public void redisTest() {
        //普通连接
        Jedis jedis = new Jedis("119.29.78.223",6379,5000);
        jedis.set("name","jack");

        jedis.close();
    }

    @Test
    public void redisPoolTest() {
        //普通连接
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);//多少连接
        config.setMaxWaitMillis(5000);//没资源等待5秒
        config.setMinIdle(10);//至少十个

        //创建连接池
        JedisPool pool = new JedisPool(config,"119.29.78.223",6379);

        Jedis jedis = pool.getResource();//获取连接资源
        jedis.lpush("names","tom","jack","rose");
        jedis.close();
        pool.destroy();//销毁
    }

    @Test
    public void springRedisTest() {
        //spring 测试
        //userService.springRedisTest();
    }

    @Test
    public void getSpringRedisTest() {
        //userService.getSpringRedisTest();
    }

    @Test
    public void saveUser() {
        User user = new User(103,"rose",66.6F);
        userService.saveUser(user);
    }

    @Test
    public void findUserByUserName() {
        User user = userService.findUserByUserName("rose");
        System.out.println(user);
    }

}
