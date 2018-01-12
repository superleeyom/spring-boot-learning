package com.leeyom.dao;

import com.leeyom.pojo.db.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 用户相关的dao接口
 * @author Leeyom Wang
 * @date 2018年01月04日 17:16
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 分页查询
     * @param pageable 分页参数封装
     * @return
     */
    @Query("select u from User u")
    Page<User> findList(Pageable pageable);

    /**
     * 根据id获取指定用户信息
     * @param id 用户id
     * @return
     */
    User findById(long id);

    /**
     * 根据用户名查找用户信息
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 删除指定的用户信息
     * @param id 用户id
     */
    void deleteById(long id);
}
