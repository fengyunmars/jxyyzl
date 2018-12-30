package com.ppfuns.jxyyzl.data;

import java.io.Serializable;

/**
 * Author: Fly
 * Time: 17-12-21 上午10:33.
 * Discription: 科室和部门
 */

public class Dept implements Serializable{
    private String deptId	;//	科室编号
    private String deptName	;//	科室名称
    private String  descs	;//	科室简介


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

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    @Override
    public String toString() {
        return "<DeptInfo>\n" +
                "   <deptId>" + deptId +"</deptId>\n" +
                "   <deptName>" + deptName +"</deptName>\n" +
                "   <descs>" + descs +"</descs>\n" +
                "</DeptInfo>\n";
    }
}
