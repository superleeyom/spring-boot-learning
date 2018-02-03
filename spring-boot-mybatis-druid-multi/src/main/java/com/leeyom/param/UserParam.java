package com.leeyom.param;

public class UserParam extends PageParam {

    private String userName;

    private String userSex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public UserParam(Integer pageSize, Integer pageNumber, String userName, String userSex) {
        super(pageSize, pageNumber);
        this.userName = userName;
        this.userSex = userSex;
    }
}
