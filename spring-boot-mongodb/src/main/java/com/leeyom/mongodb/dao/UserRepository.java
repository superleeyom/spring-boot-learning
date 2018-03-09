package com.leeyom.mongodb.dao;

import com.leeyom.mongodb.pojo.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, Long> {

    UserEntity findUserByUserName(String userName);

    /**
     * 分页查找
     * @param pageable
     * @return
     */
    Page<UserEntity> findAll(Pageable pageable);

}
