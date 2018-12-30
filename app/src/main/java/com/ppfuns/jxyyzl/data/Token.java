package com.ppfuns.jxyyzl.data;

import java.io.Serializable;

/**
 * Author:Administrator
 * Time:2017/12/15.9:44
 */

public class Token implements Serializable{
    private String Token;
    private String ValidTime;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getValidTime() {
        return ValidTime;
    }

    public void setValidTime(String validTime) {
        ValidTime = validTime;
    }

    @Override
    public String toString() {
        return "{" +
                "Token='" + Token + '\'' +
                ", ValidTime='" + ValidTime + '\'' +
                '}';
    }
}
