package com.leeyom.mongodb.config;

import com.leeyom.mongodb.prop.MultipleMongoProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MultipleMongoConfig {

    @Autowired
    private MultipleMongoProperties mongoProperties;

    @Primary
    @Bean(name = OneMongoDbConfig.MONGO_TEMPLATE)
    public MongoTemplate oneMongoTemplate() throws Exception {
        return new MongoTemplate(oneFactory(this.mongoProperties.getOne()));
    }

    @Bean
    @Qualifier(TwoMongoDbConfig.MONGO_TEMPLATE)
    public MongoTemplate twoMongoTemplate() throws Exception {
        return new MongoTemplate(twoFactory(this.mongoProperties.getTwo()));
    }

    @Bean
    @Primary
    public MongoDbFactory oneFactory(MongoProperties mongo) throws Exception {
        MongoClient client = new MongoClient(new MongoClientURI(mongoProperties.getOne().getUri()));
        return new SimpleMongoDbFactory(client, mongoProperties.getOne().getDatabase());
    }

    @Bean
    public MongoDbFactory twoFactory(MongoProperties mongo) throws Exception {
        MongoClient client = new MongoClient(new MongoClientURI(mongoProperties.getTwo().getUri()));
        return new SimpleMongoDbFactory(client, mongoProperties.getTwo().getDatabase());
    }
}