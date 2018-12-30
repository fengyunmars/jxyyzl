package com.ppfuns.jxyyzl.module;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.ppfuns.jxyyzl.utils.AppUtils;
import com.ppfuns.jxyyzl.utils.Base64Utils;
import com.ppfuns.jxyyzl.utils.Md5Util;
import com.ppfuns.jxyyzl.utils.MyLog;
import com.ppfuns.jxyyzl.utils.SystemPropertiesProxy;

/**
 * Author:Administrator
 * Time:2018/1/7.23:18
 */

public class HealthAppTools {
    public static String getUserToken(Context context) {
        String state = SystemPropertiesProxy.get(context, "persist.sys.health.loginstate", "0");
        if ("0".equals(state)) {
            Boolean flag = AppUtils.startActivity(context, "com.ppfuns.health", "com.ppfuns.activity.LoginActivity");
            if (!flag) {
                Toast.makeText(context, "没有安装阖佳健康应用，请安装后再使用相关功能.....", Toast.LENGTH_LONG).show();
            }
            ((Activity)context).finish();
            return null;
        }

        String id = SystemPropertiesProxy.get(context, "persist.sys.health.id", "");
        if (TextUtils.isEmpty(id)||id.equals("default")) {
            Toast.makeText(context, "读取阖佳健康应用登陆信息失败.....", Toast.LENGTH_LONG).show();
            ((Activity)context).finish();
            return null;
        }
        String baseStr = Base64Utils.encode(id.getBytes());
        String lgstr = baseStr + Md5Util.md5(id) + Md5Util.md5(baseStr);
        String userToken = lgstr.substring(0, 64);
        MyLog.d(userToken);
        return userToken;
    }
}
