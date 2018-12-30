package com.ppfuns.jxyyzl.data;

import java.io.Serializable;

/**
 * Author: Fly
 * Time: 17-12-18 上午5:50.
 * Discription: 地区
 */

public class CommonRegion implements Serializable{
    private String regionCode;//区域编码
    private String name;//区域名称

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "<CommonRegion>\n" +
                "   <regionCode>" + regionCode + "</regionCode>\n" +
                "   <name>" + name +"</name>\n"+
                "</CommonRegion>\n";
    }
}
