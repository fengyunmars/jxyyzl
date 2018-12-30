package com.ppfuns.jxyyzl.data;

import java.io.Serializable;

/**
 * Author:Administrator
 * Time:2017/12/22.11:26
 */

public class Doctor implements Serializable{
    private String doctorId;//医生编号
    private String doctorName;//医生姓名
    private String deptId;//科室编号
    private String title;//医生职称
    private String descs;//医生简介

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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
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

    @Override
    public String toString() {
        return "<DoctorInfo>\n" +
                "   <doctorId>" + doctorId + "</doctorId>\n" +
                "   <doctorName>" + doctorName + "</doctorName>\n" +
                "   <deptId>" + deptId + "</deptId>\n" +
                "   <title>" + title + "</title>\n" +
                "   <descs>" + descs + "</descs>\n" +
                "</DoctorInfo>\n";
    }
}
