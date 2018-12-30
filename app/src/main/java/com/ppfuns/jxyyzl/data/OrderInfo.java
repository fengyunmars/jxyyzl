package com.ppfuns.jxyyzl.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:Administrator
 * Time:2017/12/28.19:41
 */

public class OrderInfo implements Serializable{
    private String userToken;
    private String phone;
    private String hisOrderId;//订单号
    private String hospitalId;//	医院编号
    private String hosName;//	医院名称
    private String deptId	;//	科室编号
    private String deptName	;//	科室名称
    private String regDate;//号源日期
    private String createTime;//申请日期
    private String name;//姓名
    private String sex;//性别
    private String denNo;//身份证号
    private String upstate = "0";//是否跟服务器同步，0不同步，1同步
    private String startendTime;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHisOrderId() {
        return hisOrderId;
    }

    public void setHisOrderId(String hisOrderId) {
        this.hisOrderId = hisOrderId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getStartendTime() {
        return startendTime;
    }

    public void setStartendTime(String startendTime) {
        this.startendTime = startendTime;
    }

    public String getUpstate() {
        return upstate;
    }

    public void setUpstate(String upstate) {
        this.upstate = upstate;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "userToken='" + userToken + '\'' +
                ", phone='" + phone + '\'' +
                ", hisOrderId='" + hisOrderId + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                ", hosName='" + hosName + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", regDate='" + regDate + '\'' +
                ", createTime='" + createTime + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", denNo='" + denNo + '\'' +
                ", upstate='" + upstate + '\'' +
                ", startendTime='" + startendTime + '\'' +
                '}';
    }

    public Map<String,String> toMap() {
        Map<String,String> map = new HashMap<>();
        map.put("userToken",userToken==null?"":userToken);
        map.put("phone",phone==null?"":phone);
        map.put("hisOrderId",hisOrderId==null?"":hisOrderId);
        map.put("hospitalId",hospitalId==null?"":hospitalId);
        map.put("hosName",hosName==null?"":hosName);
        map.put("deptId",deptId==null?"":deptId);
        map.put("deptName",deptName==null?"":deptName);
        map.put("regDate",regDate==null?"":regDate);
        map.put("createTime",createTime==null?"":createTime);
        map.put("name",name==null?"":name);
        map.put("sex",sex==null?"":sex);
        map.put("denNo",denNo==null?"":denNo);
        map.put("startendTime",startendTime==null?"":startendTime);
        return map;
    }
}
