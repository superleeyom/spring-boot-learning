package com.leeyom.dubbo.pojo;

import java.io.Serializable;

/**
 * 用户
 * @author Leeyom Wang
 * @date 2018年02月07日 11:39
 */
public class UserEntity implements Serializable {

    private Integer id;

    private String userName;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity() {
    }

    public UserEntity(Integer id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
