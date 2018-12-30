package com.ppfuns.jxyyzl.http;

import java.util.Map;

/**
 *
 * Created by FlyZebra on 2016/3/20.
 */
public interface IHttp {
    /**
     * 发送网络请求
     * @param url 请求的URL地址
     * @param tag 请求标识
     * @param result 主线程回调监听
     */

    void getString(String url, Object tag, HttpResult result);

    void postString(String url, Map<String, String> map, Object tag, HttpResult result);

    String postStrAndResp(String url, Map<String, String> map, Object tag);

    void postJson(String url, String json, final Object tag, final HttpResult result);

    String postJsonAndResp(String url, String json, final Object tag);

    String readDiskCache(String url);

    void cancelAll(Object tag);

    interface HttpResult {
        void succeed(Object object);
        void failed(Object object);
    }

}
