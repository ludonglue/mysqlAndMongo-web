package com.example.demo.Service.Mongo;

import com.example.demo.Entity.User;

import java.util.List;

/**
 * Created by ludonglue on 2017/9/11.
 */
public interface IUserMongoService {
    List<User> selectUserList();
}
