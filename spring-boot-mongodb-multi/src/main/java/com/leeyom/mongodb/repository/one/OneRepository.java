package com.leeyom.mongodb.repository.one;

import com.leeyom.mongodb.pojo.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OneRepository extends MongoRepository<UserEntity, Long> {

    UserEntity findUserByUserName(String userName);
}
