package com.leeyom.mongodb.repository.two;

import com.leeyom.mongodb.pojo.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TwoRepository extends MongoRepository<UserEntity, Long> {

    UserEntity findUserByUserName(String userName);
}
