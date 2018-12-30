package com.ppfuns.jxyyzl.data;

import java.io.Serializable;

/**
 * Author: Fly
 * Time: 17-12-20 上午8:29.
 * Discription: 医院
 */

public class Hospital implements Serializable{
    private String hospitalId;//	医院编号
    private String hosName;//	医院名称
    private String hosLevel;//	医院等级，如三级甲等
    private String nature;//	医院性质，如公立医院
    private String phone;//	医院电话
    private String address;//	医院地址
    private String busLine;//	乘车路线
    private String webSite;//	医院网站
    private String introduction;//	医院介绍
    private String isDuiJie;//	是否跟医院对接：1是，0否


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

    public String getHosLevel() {
        return hosLevel;
    }

    public void setHosLevel(String hosLevel) {
        this.hosLevel = hosLevel;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusLine() {
        return busLine;
    }

    public void setBusLine(String busLine) {
        this.busLine = busLine;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIsDuiJie() {
        return isDuiJie;
    }

    public void setIsDuiJie(String isDuiJie) {
        this.isDuiJie = isDuiJie;
    }


    @Override
    public String toString() {
        return "<HospitalInfo>\n" +
                "   <hospitalId>" + hospitalId + "</hospitalId>\n" +
                "   <hosName>" + hosName + "</hosName>\n" +
                "   <hosLevel>" + hosLevel + "</hosLevel>\n" +
                "   <nature>" + nature + "</nature>\n" +
                "   <phone>" + phone + "</phone>\n" +
                "   <address>" + address + "</address>\n" +
                "   <busLine>" + busLine + "</busLine>\n" +
                "   <webSite>" + webSite + "</webSite>\n" +
                "   <introduction>" + introduction + "</introduction>\n" +
                "   <isDuiJie>" + isDuiJie + "</isDuiJie>\n" +
                "</HospitalInfo>\n";
    }
}
