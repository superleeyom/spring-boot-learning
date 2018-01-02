package com.leeyom.dao;

import com.leeyom.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根于用户名查找
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 根据用户名或者邮箱查找对应的用户信息
     * @param username
     * @param email
     * @return
     */
    User findByUserNameOrEmail(String username, String email);

    /**
     * 分页查询
     * @param pageable 分页参数封装
     * @return
     */
    @Query("select u from User u")
    @Override
    Page<User> findAll(Pageable pageable);


    /**
     * 使用@Query自定HQL语句
     * @param userName
     * @param id
     * @return
     */
    @Transactional(timeout = 10)
    @Modifying
    @Query("update User set userName = ?1 where id = ?2")
    int modifyById(String userName, Long id);

    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteById(Long id);

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);


    /**
     * 限制查询
     * @return
     */
    User findTopByOrderByUserNameDesc();
}
