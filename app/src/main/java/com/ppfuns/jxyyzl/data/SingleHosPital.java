package com.ppfuns.jxyyzl.data;

/**
 * Author: Fly
 * Time: 18-1-12 下午9:02.
 * Discription: This is sHosPital
 */

public class SingleHosPital {
    private String hospitalId;//	医院编号
    private String hosName;//	医院名称
    private String searthKey;//搜索关键字母

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

    @Override
    public String toString() {
        return "SingleHosPital{" +
                "hospitalId='" + hospitalId + '\'' +
                ", hosName='" + hosName + '\'' +
                ", searthKey='" + searthKey + '\'' +
                '}';
    }

    public String getSearthKey() {
        return searthKey;
    }

    public void setSearthKey(String searthKey) {
        this.searthKey = searthKey;
    }

}
