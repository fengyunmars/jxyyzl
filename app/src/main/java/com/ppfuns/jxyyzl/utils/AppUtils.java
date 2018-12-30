package com.ppfuns.jxyyzl.utils;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;

/**
 * Author: Fly
 * Time: 18-1-7 下午3:37.
 * Discription: This is AppUtils
 */

public class AppUtils {

    public static boolean startActivity(Context context, String packageName, String className) {
        try {
            Intent intent = new Intent();
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            @SuppressLint("WrongConstant")
            ActivityInfo info = intent.resolveActivityInfo(context.getPackageManager(), PackageManager.GET_ACTIVITIES);
            if (info != null) {
                MyLog.d(info.toString());
                context.startActivity(intent);
                return true;
            } else {
                MyLog.d("no activity found to handle Intent :" + packageName);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
