package com.kaisheng.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash
public class User {

    @Id
    private Integer id;
    private String name;
    private Float score;

    public User(){}

    public User(Integer id, String name, float score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
