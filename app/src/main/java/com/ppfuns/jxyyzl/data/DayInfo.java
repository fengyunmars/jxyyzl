package com.ppfuns.jxyyzl.data;

import java.io.Serializable;
import java.util.List;

/**
 * Author:Administrator
 * Time:2017/12/22.7:10
 */

public class DayInfo implements Serializable{
    private String strDay;
    private String week;
    private String requstDay;
    private List<DoctorSchedule> doctorScheduleList;

    public String getStrDay() {
        return strDay;
    }

    public void setStrDay(String strDay) {
        this.strDay = strDay;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getRequstDay() {
        return requstDay;
    }

    public void setRequstDay(String requstDay) {
        this.requstDay = requstDay;
    }

    public List<DoctorSchedule> getDoctorScheduleList() {
        return doctorScheduleList;
    }

    public void setDoctorScheduleList(List<DoctorSchedule> doctorScheduleList) {
        this.doctorScheduleList = doctorScheduleList;
    }
}
