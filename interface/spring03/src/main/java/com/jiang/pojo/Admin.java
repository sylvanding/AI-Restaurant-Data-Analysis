package com.jiang.pojo;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class Admin {

    private String username;
    //密码以MD5形式存储
    private String password;
    //盐
    private String salt;

    public Admin() {
    }

    public Admin(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
