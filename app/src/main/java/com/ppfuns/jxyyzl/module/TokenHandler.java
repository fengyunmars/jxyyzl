package com.ppfuns.jxyyzl.module;


import android.content.Context;
import android.text.TextUtils;

import com.ppfuns.jxyyzl.constant.HttpApi;
import com.ppfuns.jxyyzl.data.Token;
import com.ppfuns.jxyyzl.http.IHttp;
import com.ppfuns.jxyyzl.http.MyHttp;
import com.ppfuns.jxyyzl.utils.DateUtils;
import com.ppfuns.jxyyzl.utils.GsonUtils;
import com.ppfuns.jxyyzl.utils.MyLog;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:Administrator
 * Time:2017/12/15.9:17
 */

public class TokenHandler {
    private static final String HTTPTAG = "TokenHandler";
    private static long ValidTime = 1195000;
    private static long getTokenTime;
    private IHttp iHttp = MyHttp.getInstance();
    private Token token;

    private TokenHandler() {
    }

    public static TokenHandler getInstance() {
        return TokenHandlerHolder.sInstance;
    }

    public synchronized Token getToken(Context context) {
        if (tokenIsValid()) return token;
        token = null;
        Map<String, String> map = new HashMap<>();
        map.put("key", HttpApi.DEFAULT_TOKEY_KEY);
        String jsonKey = GsonUtils.object2Json(map);
        String result = iHttp.postJsonAndResp(HttpApi.getTokenApiUrl(context), jsonKey, HTTPTAG);
        if (!TextUtils.isEmpty(result)) {
            token = GsonUtils.json2Object(result, Token.class);
        }
        if (token == null) {
            MyLog.d("get token failed!");
            return token;
        }
        MyLog.d("token=" + token.getToken());
        try {
            Date date = DateUtils.string2date(token.getValidTime(), "yyyy/MM/dd HH:mm:ss");
            long currentTime = System.currentTimeMillis();
            if (date != null) {
                ValidTime = date.getTime() - currentTime - 1000;
                MyLog.d("current time=" + DateUtils.getCurrentDateString("yyyy/MM/dd HH:mm:ss") + " token validtime=" + token.getValidTime());
                MyLog.d("token valid:" + date.getTime() + "-" + currentTime + "=" + ValidTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(token.getToken())) {
            getTokenTime = System.currentTimeMillis();
        }
        return token;
    }

    public synchronized boolean tokenIsValid() {
        if (token == null) return false;
        boolean flag = (System.currentTimeMillis() - getTokenTime) < ValidTime;
        try {
            Date date = DateUtils.string2date(token.getValidTime(), "yyyy/MM/dd HH:mm:ss");
            flag = flag && (date.getTime() > (System.currentTimeMillis() + 10 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }

    private static class TokenHandlerHolder {
        static final TokenHandler sInstance = new TokenHandler();
    }
}
