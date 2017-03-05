package com.kaisheng.pojo;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String name;
    private Float score;

    public User(Integer id, String name, float score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
