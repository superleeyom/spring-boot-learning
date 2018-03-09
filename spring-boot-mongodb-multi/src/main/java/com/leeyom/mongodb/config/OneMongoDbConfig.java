package com.leeyom.mongodb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.leeyom.mongodb.repository.one",
        mongoTemplateRef = OneMongoDbConfig.MONGO_TEMPLATE)
public class OneMongoDbConfig {
    protected static final String MONGO_TEMPLATE = "oneMongoTemplate";
}
