package com.leeyom.mongodb.prop;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mongodb")
public class MultipleMongoProperties {

    MongoProperties one = new MongoProperties();
    MongoProperties two = new MongoProperties();

    public MongoProperties getOne() {
        return one;
    }

    public void setOne(MongoProperties one) {
        this.one = one;
    }

    public MongoProperties getTwo() {
        return two;
    }

    public void setTwo(MongoProperties two) {
        this.two = two;
    }
}
