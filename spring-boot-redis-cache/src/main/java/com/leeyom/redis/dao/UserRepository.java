package com.leeyom.redis.dao;

import com.leeyom.redis.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户dao
 * @author Leeyom Wang
 * @date 2018年02月06日 11:05
 */
public interface UserRepository extends JpaRepository<Users, Long> {

    /**
     * 根据昵称查找指定的用户
     * @param nickName 昵称
     * @return
     */
    List<Users> findByNickName(String nickName);

}
