package com.leeyom.mongodb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.leeyom.mongodb.repository.two",
        mongoTemplateRef = TwoMongoDbConfig.MONGO_TEMPLATE)
public class TwoMongoDbConfig {
    protected static final String MONGO_TEMPLATE = "twoMongoTemplate";
}
