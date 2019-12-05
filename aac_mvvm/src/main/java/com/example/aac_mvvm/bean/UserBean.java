package com.example.aac_mvvm.bean;

/**
 * @time 2019/12/2 19:49
 * @desc
 */
public class UserBean {

    public String userId;

    public String name;

    public String phone;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
