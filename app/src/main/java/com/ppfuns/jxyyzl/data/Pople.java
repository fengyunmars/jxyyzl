package com.ppfuns.jxyyzl.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:Administrator
 * Time:2017/12/28.9:43
 */

public class Pople implements Serializable{
    private int id;//本地数据库id
    private String userToken;
    private String cardNo = ""; //就诊卡号
    private String cardType = ""; //就诊卡类型
    private String name;//姓名
    private String sex;//性别
    private String denNo;//身份证号
    private String guardian;//监护人姓名
    private String birthday;//出生日期
    private String phone;//手机号码
    private String defult = "0";//是否默认 值1为默认
    private String upstate = "0";//是否跟服务器同步，0不同步，1同步
    private String isguardian = "0";//是否监护人
    private String hospitalId = "0";//	就诊卡医院编号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDenNo() {
        return denNo;
    }

    public void setDenNo(String denNo) {
        this.denNo = denNo;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDefult() {
        return defult;
    }

    public void setDefult(String defult) {
        this.defult = defult;
    }

    public String getIsguardian() {
        return isguardian;
    }

    public void setIsguardian(String isguardian) {
        this.isguardian = isguardian;
    }

    public String getUpstate() {
        return upstate;
    }

    public void setUpstate(String upstate) {
        this.upstate = upstate;
    }

    @Override
    public String toString() {
        return "Pople{" +
                "id=" + id +
                ", userToken='" + userToken + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", cardType='" + cardType + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", denNo='" + denNo + '\'' +
                ", guardian='" + guardian + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", defult='" + defult + '\'' +
                ", upstate='" + upstate + '\'' +
                ", isguardian='" + isguardian + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                '}';
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Map<String,String> toMap() {
        Map<String,String> map = new HashMap<>();
        map.put("userToken",userToken==null?"":userToken);
        map.put("cardNo",cardNo==null?"":cardNo);

        map.put("cardType",cardType==null?"":cardType);
        map.put("name",name==null?"":name);
        map.put("sex",sex==null?"":sex);
        map.put("denNo",denNo==null?"":denNo);
        map.put("guardian",guardian==null?"":guardian);
        map.put("birthday",birthday==null?"":birthday);
        map.put("phone",phone==null?"":phone);
        map.put("defult",defult==null?"0":defult);
        map.put("isguardian",isguardian==null?"0":isguardian);
        map.put("hospitalId",hospitalId==null?"0":hospitalId);
        return map;
    }
}
