package com.example.demo.Service.Mongo.Impl;

import com.example.demo.Dao.mongo.UserMongoDao;
import com.example.demo.Entity.User;
import com.example.demo.Service.Mongo.IUserMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ludonglue on 2017/9/11.
 */
@Service("userMongoService")
public class UserMongoServiceImpl implements IUserMongoService {
    @Autowired
    UserMongoDao userMongoDao;
    @Override
    public List<User> selectUserList() {
        return userMongoDao.findAll(User.class,"User");
    }
}
