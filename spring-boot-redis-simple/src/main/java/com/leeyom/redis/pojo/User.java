package com.leeyom.redis.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    private String userName;

    private String password;

    private String userSex;

    private String nickName;

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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public User() {
    }

    public User(String userName, String password, String userSex, String nickName) {
        this.userName = userName;
        this.password = password;
        this.userSex = userSex;
        this.nickName = nickName;
    }

    public User(Integer id, String userName, String password, String userSex, String nickName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userSex = userSex;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", userSex=").append(userSex);
        sb.append(", nickName=").append(nickName);
        sb.append("]");
        return sb.toString();
    }
}