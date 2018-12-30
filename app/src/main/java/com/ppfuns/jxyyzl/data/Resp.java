package com.ppfuns.jxyyzl.data;

import java.io.Serializable;

/**
 * Author:Administrator
 * Time:2017/12/28.13:41
 */

public class Resp implements Serializable {
    private String C;//接口代码1006
    private String R;//处理结果代码0成功,其他失败，参见<处理结果代码>
    private String M;//处理结果描述

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getR() {
        return R;
    }

    public void setR(String r) {
        R = r;
    }

    public String getM() {
        return M;
    }

    public void setM(String m) {
        M = m;
    }


    @Override
    public String toString() {
        return "HisOrder{" +
                "C='" + C + '\'' +
                ", R='" + R + '\'' +
                ", M='" + M + '\'' +
                '}';
    }
}
