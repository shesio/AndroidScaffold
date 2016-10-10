package com.hiquanta.domain;



/**
 * Created by hiquanta on 2016/10/10.
 */

public class LoginInfo extends User {
    private String userName;
    private String passWord;

    public LoginInfo(int userId) {
        super(userId);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
