package com.ppfuns.jxyyzl.constant;

import android.content.Context;

import com.ppfuns.jxyyzl.utils.SystemPropertiesProxy;

/**
 * Author:Administrator
 * Time:2018/1/6.10:42
 */

public class HttpApi {
    /**
     * 医院接口获取Token所需要的Key值
     */
    public static final String DEFAULT_TOKEY_KEY = "1dP/OvQeRUVLLq0NEOMIe7VHDW8hCOXDUav9Fm/lh86bn1s4k5BRmKm/JC4E8GfvLWtXfUzWRPrX/nD/BzXD8RnzoJziCF/1SH0hjciou0aoF7Ijr9eHbhePmha+BWG9hyhTSeRv25DTqiOI2UiNbMpm4jZabxnn";
    /**
     * 医院HIS接口默认地址,可从系统属性persist.sys.jxyyzl.hisurl读取
     */
    private static final String DEFAULT_HIS_URL = "http://172.168.31.17";
    /**
     * 就诊人订单信息数据存储服务器默认地址，可从系统属性persist.sys.jxyyzl.urlstore读取
     */
    private static final String DEFAULT_JXRES_URL = "http://10.60.33.243:9080/jxres";
    /**
     * 获取Token地址
     */
    private static final String DEFAULT_TOKEN_API = "/FrontToPublic/a/Confer/token";
    /**
     * 医院数据获取接口
     */
    private static final String DEFAULT_HIS_API = "/InterfaceForThirdPart/InterfaceForThirdPart.asmx/Services";

    private static String getHisUrl(Context context) {
        String str = SystemPropertiesProxy.get(context, SystemProp.SYS_URL_HIS, DEFAULT_HIS_URL);
        if (str.startsWith("http://") || str.startsWith("https://")) {
            return str;
        } else {
            return DEFAULT_HIS_URL;
        }
    }

    /**
     * @param context
     * @return
     */
    public static String getJxresUrl(Context context) {
        String str = SystemPropertiesProxy.get(context, SystemProp.SYS_URL_STORE, DEFAULT_JXRES_URL);
        if (str.startsWith("http://") || str.startsWith("https://")) {
            return str;
        } else {
            return DEFAULT_JXRES_URL;
        }
    }

    /**
     * @param context
     * @return
     */
    public static String getTokenApiUrl(Context context) {
        return getHisUrl(context) + DEFAULT_TOKEN_API;
    }


    /**
     * @param context
     * @return
     */
    public static String getHisApiUrl(Context context) {
        return getHisUrl(context) + DEFAULT_HIS_API;
    }
}
