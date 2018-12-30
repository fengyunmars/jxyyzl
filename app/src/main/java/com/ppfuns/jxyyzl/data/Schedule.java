package com.ppfuns.jxyyzl.data;

import java.io.Serializable;

/**
 * Author:Administrator
 * Time:2017/12/22.11:38
 */

public class Schedule implements Serializable{
    private String scheduleId;//排班ID(唯一，重新生成排班后也不会变化)
    private String regType;//号源类别, 如普通门诊，专家门诊。
    private String regTypecode;//号源类别code:为普通门诊，专家门诊这些的编码。
    private String regDate;//排班日期（“YYYY-MM-dd”）
    private String timeFlag;//班次 例如 1上午、2下午等
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String deptId;//科室编号
    private String doctorId;//医生编号
    private String regFee;//挂号费(如 4.00)
    private String leaveCount;//剩余号数
    private String isHalt;//排班状态(0可用，1停诊)

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

    public String getRegTypecode() {
        return regTypecode;
    }

    public void setRegTypecode(String regTypecode) {
        this.regTypecode = regTypecode;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getTimeFlag() {
        return timeFlag;
    }

    public void setTimeFlag(String timeFlag) {
        this.timeFlag = timeFlag;
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

    public String getRegFee() {
        return regFee;
    }

    public void setRegFee(String regFee) {
        this.regFee = regFee;
    }

    public String getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(String leaveCount) {
        this.leaveCount = leaveCount;
    }

    public String getIsHalt() {
        return isHalt;
    }

    public void setIsHalt(String isHalt) {
        this.isHalt = isHalt;
    }

    @Override
    public String toString() {
        return "<ScheduleInfo>\n" +
                "   <scheduleId>" + scheduleId + "</scheduleId>\n" +
                "   <regType>" + regType + "</regType>\n" +
                "   <regTypecode>" + regTypecode + "</regTypecode>\n" +
                "   <regDate>" + regDate + "</regDate>\n" +
                "   <timeFlag>" + timeFlag + "</timeFlag>\n" +
                "   <startTime>" + startTime + "</startTime>\n" +
                "   <endTime>" + endTime + "</endTime>\n" +
                "   <deptId>" + deptId + "</deptId>\n" +
                "   <doctorId>" + doctorId + "</doctorId>\n" +
                "   <regFee>" + regFee + "</regFee>\n" +
                "   <leaveCount>" + leaveCount + "</leaveCount>\n" +
                "   <isHalt>" + isHalt + "</isHalt>\n" +
                "</ScheduleInfo>\n";
    }
}
