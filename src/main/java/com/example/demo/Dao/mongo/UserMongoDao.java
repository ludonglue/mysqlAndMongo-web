package com.example.demo.Dao.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;


/**
 * Created by ludonglue on 2017/9/11.
 */
@Repository
public class UserMongoDao extends MongoTemplate {
    @Autowired
    public UserMongoDao(@Qualifier("firstMongoFactory") MongoDbFactory firstMongoFactory, @Qualifier("firstConverter") MongoConverter firstConverter) {
        super(firstMongoFactory, firstConverter);
    }

}
