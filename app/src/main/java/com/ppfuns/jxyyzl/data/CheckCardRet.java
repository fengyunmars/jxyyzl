package com.ppfuns.jxyyzl.data;

import java.io.Serializable;

/**
 * Author: Fly
 * Time: 18-1-3 下午5:48.
 * Discription: This is CheckResult
 */

public class CheckCardRet implements Serializable {
    private String IsValid; //（true）为有效卡

    public String getIsValid() {
        return IsValid;
    }

    public void setIsValid(String isValid) {
        IsValid = isValid;
    }

    @Override
    public String toString() {
        return "CheckResult{" +
                "IsValid='" + IsValid + '\'' +
                '}';
    }
}
