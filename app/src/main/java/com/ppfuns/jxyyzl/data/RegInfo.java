package com.ppfuns.jxyyzl.data;

import java.io.Serializable;

/**
 * Author:Administrator
 * Time:2017/12/22.11:33
 */

public class RegInfo implements Serializable{
    private String deptId;//科室编号
    private String doctorId;//医生编号
    private String regDate;//号源日期
    private String code;//号源ID(唯一，重新生成号表后也不会变化)
    private String scheduleId;//排班id
    private String regType;//号别名称如普通门诊，专家门诊
    private String regFee;//挂号费
    private String seqNO;//号序
    private String startTime;//号源开始时间如8:30
    private String endTime;//号源结束时间9:30
    private String state;//号源状态：3、已就诊，2、已取号（表示付了钱），1已预约  0 可用\

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getRegFee() {
        return regFee;
    }

    public void setRegFee(String regFee) {
        this.regFee = regFee;
    }

    public String getSeqNO() {
        return seqNO;
    }

    public void setSeqNO(String seqNO) {
        this.seqNO = seqNO;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "<Reginfo>\n" +
                "   <deptId>" + deptId + "</deptId>\n" +
                "   <doctorId>" + doctorId + "</doctorId>\n" +
                "   <regDate>" + regDate + "</regDate>\n" +
                "   <code>" + code + "</code>\n" +
                "   <scheduleId>" + scheduleId + "</scheduleId>\n" +
                "   <regType>" + regType + "</regType>\n" +
                "   <regFee>" + regFee + "</regFee>\n" +
                "   <seqNO>" + seqNO + "</seqNO>\n" +
                "   <startTime>" + startTime + "</startTime>\n" +
                "   <endTime>" + endTime + "</endTime>\n" +
                "   <state>" + state + "</state>\n" +
                "</Reginfo>\n";
    }
}
