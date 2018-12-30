package com.ppfuns.jxyyzl.data;

import java.io.Serializable;

/**
 * Author:Administrator
 * Time:2017/12/28.13:41
 */

public class HisOrderResp implements Serializable{
    private String C;//接口代码1006
    private String R;//处理结果代码0成功,其他失败，参见<处理结果代码>
    private String M;//处理结果描述
    private String hisOrderId;//订单号
    private String orderMessage;//短信取号码，该码仅用于预约订单但未支付的情况下，返还给用户的一个用来支付取号的验证码。 仅为返回医院接口数据，是否有值视具体医院而定。测试接口返回为空。

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

    public String getHisOrderId() {
        return hisOrderId;
    }

    public void setHisOrderId(String hisOrderId) {
        this.hisOrderId = hisOrderId;
    }

    public String getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
    }

    @Override
    public String toString() {
        return "HisOrder{" +
                "C='" + C + '\'' +
                ", R='" + R + '\'' +
                ", M='" + M + '\'' +
                ", hisOrderId='" + hisOrderId + '\'' +
                ", orderMessage='" + orderMessage + '\'' +
                '}';
    }
}
