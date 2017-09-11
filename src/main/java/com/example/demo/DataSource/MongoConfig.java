package com.example.demo.DataSource;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ludonglue on 2017/8/31.
 */

@Configuration
@EnableMongoRepositories(basePackages = "com.example.demo.Dao.mongo", mongoTemplateRef = "firstMongo")
public class MongoConfig {

    //第一个数据源
    @Bean(name="firstMongoProperties")
    @Primary
    @ConfigurationProperties(prefix="spring.data.mongodb")
    public MongoProperties firstMongoProperties() {
        return new MongoProperties();
    }


    @Autowired
    @Qualifier("firstMongoProperties")
    private MongoProperties mongoProperties;


    @Bean(name="firstConverter")
    public MappingMongoConverter firstConverter() throws Exception{
        //取出默认存储的 _class字段
        MappingMongoConverter converter =
                new MappingMongoConverter(firstMongoFactory(mongoProperties), new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    @Primary
    @Bean(name = "firstMongo")
    public MongoTemplate firstMongoTemplate() throws Exception {
        MongoDbFactory mongoDbFactory = firstMongoFactory(mongoProperties);
        return new MongoTemplate(mongoDbFactory,firstConverter());
    }


    @Bean("firstMongoFactory")
    @Primary
    public MongoDbFactory firstMongoFactory(MongoProperties mongoProperties) throws Exception {
        ServerAddress serverAdress = new ServerAddress(mongoProperties.getHost(),mongoProperties.getPort());

        List<MongoCredential> credentialList = new ArrayList<>();
        credentialList.add(MongoCredential.createCredential(mongoProperties.getUsername(),mongoProperties.getDatabase(),mongoProperties.getPassword()));
        MongoClient mongoClient = new MongoClient(serverAdress,credentialList);
        return new SimpleMongoDbFactory(mongoClient,mongoProperties.getDatabase());
    }

}
