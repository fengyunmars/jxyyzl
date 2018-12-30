package com.ppfuns.jxyyzl.data;

/**
 * Author:Administrator
 * Time:2017/12/28.17:05
 */

public class AppointInfo {
    private String cardNo;//卡号
    private String name;//姓名
    private String sex;//性别
    private String birthday;//生日
    private String denNo;//身份证号
    private String phone;//手机号
    private String date;//就诊日期
    private String regType;//号别名称
    private String timeSlice;//班次 例如 1上午、2下午等
    private String deptId;//科室编号
    private String deptName;//科室名称
    private String doctorId;//医生编号
    private String doctorName;//医生姓名
    private String ispayed;//是否支付（取号）（0未支付1已支付）
    private String regNo;//序号
    private String hisOrderId;//订单号
    private String fee;//费用
    private String state;// 预约登记成功 = 0,已就诊 = 1,预约登记取消成功 = 2,逾期 = 3,作废 = 4,HIS预约登记中 = 5,支付中 = 6,取消预约中 = 7,预约登记失败 = 8
    private String SmsCode;//短信取号码，该码仅用于预约订单但未支付的情况下，返还给用户的一个用来支付取号的验证码
    private String BizCode;//预约时传入的原始BizCode。\

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDenNo() {
        return denNo;
    }

    public void setDenNo(String denNo) {
        this.denNo = denNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getTimeSlice() {
        return timeSlice;
    }

    public void setTimeSlice(String timeSlice) {
        this.timeSlice = timeSlice;
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

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getIspayed() {
        return ispayed;
    }

    public void setIspayed(String ispayed) {
        this.ispayed = ispayed;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getHisOrderId() {
        return hisOrderId;
    }

    public void setHisOrderId(String hisOrderId) {
        this.hisOrderId = hisOrderId;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSmsCode() {
        return SmsCode;
    }

    public void setSmsCode(String smsCode) {
        SmsCode = smsCode;
    }

    public String getBizCode() {
        return BizCode;
    }

    public void setBizCode(String bizCode) {
        BizCode = bizCode;
    }

    @Override
    public String toString() {
        return "AppointInfo{" +
                "cardNo='" + cardNo + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", denNo='" + denNo + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                ", regType='" + regType + '\'' +
                ", timeSlice='" + timeSlice + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", ispayed='" + ispayed + '\'' +
                ", regNo='" + regNo + '\'' +
                ", hisOrderId='" + hisOrderId + '\'' +
                ", fee='" + fee + '\'' +
                ", state='" + state + '\'' +
                ", SmsCode='" + SmsCode + '\'' +
                ", BizCode='" + BizCode + '\'' +
                '}';
    }
}
