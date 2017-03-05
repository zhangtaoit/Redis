package com.kaisheng.dao;

import com.kaisheng.pojo.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,String> {


    User findUserByUserName(String userName);
}
