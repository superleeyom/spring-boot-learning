package com.leeyom.mongodb;

import com.leeyom.mongodb.prop.MultipleMongoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(MultipleMongoProperties.class)
@SpringBootApplication
public class SpringBootMongodbMultiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongodbMultiApplication.class, args);
	}
}
