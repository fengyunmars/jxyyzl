package com.ppfuns.jxyyzl.data;

import java.io.Serializable;

/**
 * Author:Administrator
 * Time:2017/12/25.10:55
 */

public class DoctorSchedule implements Serializable{
    private String scheduleId;//排班ID(唯一，重新生成排班后也不会变化)
    private String hospitalId;//	医院编号
    private String hosName;//	医院名称
    private String deptId;//科室编号
    private String deptName;//科室名称
    private String doctorId;//医生编号
    private String doctorName;//医生姓名
    private String title;//医生职称
    private String descs;//医生简介
    private String regType;//专家门诊，普通门诊
    private String regFee;//挂号费(如 4.00)

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
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

    @Override
    public String toString() {
        return "DoctorSchedule{" +
                "scheduleId='" + scheduleId + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                ", hosName='" + hosName + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", title='" + title + '\'' +
                ", descs='" + descs + '\'' +
                ", regType='" + regType + '\'' +
                ", regFee='" + regFee + '\'' +
                '}';
    }
}
