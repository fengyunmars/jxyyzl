package com.ppfuns.jxyyzl.module;

/**
 * Author:Administrator
 * Time:2017/12/24.9:08
 */

public interface HisError {
    int TimeOutError = -1;//请求超时
    int GetTokeyFailed = 1;//获取Token失败
    int GetHisDateFailed = 2;
    int XmlGetNull = 3;
}
