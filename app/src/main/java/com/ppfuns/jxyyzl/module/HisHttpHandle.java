package com.ppfuns.jxyyzl.module;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.ppfuns.jxyyzl.constant.HttpApi;
import com.ppfuns.jxyyzl.data.Token;
import com.ppfuns.jxyyzl.http.IHttp;
import com.ppfuns.jxyyzl.http.MyHttp;
import com.ppfuns.jxyyzl.utils.MyLog;
import com.ppfuns.jxyyzl.utils.XmlHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Author:Administrator
 * Time:2017/12/24.8:44
 */

public class HisHttpHandle<T> {
    private final static ExecutorService executors = Executors.newFixedThreadPool(1);
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private AtomicBoolean isCancle = new AtomicBoolean(false);
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private IHttp iHttp = MyHttp.getInstance();
    private String HTTPTAG = "HisHttpHandle";
    private String mCurrentTask = "";

    public void requset(final Context context, final String matchesNode, final String strFormat, final Class<?> cls, final HisResult<?> hisResult, final Object... args) {
        isCancle.set(true);
        mCurrentTask =  args.length > 0 ? String.format(strFormat, args) : strFormat;
        final String requstStr = mCurrentTask;
        executors.execute(new Runnable() {
            @Override
            public void run() {
                while (isRunning.get()){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isRunning.set(true);
                isCancle.set(false);
                //获取Token
                Token token = TokenHandler.getInstance().getToken(context);
                if (token == null) {
                    if (isCancle.get()) {
                        isRunning.set(false);
                        return;
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            hisResult.faile(HisError.GetTokeyFailed);
                        }
                    });
                    isRunning.set(false);
                    return;
                }
                String postStr = requstStr.replace(">null<", "><");
                postStr = postStr.replace("<K></K>", "<K>" + token.getToken() + "</K>");
                //发送请求
                MyLog.d("postString:\n" + postStr);
                Map<String, String> map = new HashMap<String, String>();
                map.put("reqXml", postStr);
                String responetStr = iHttp.postStrAndResp(HttpApi.getHisApiUrl(context), map, HTTPTAG);
                if (TextUtils.isEmpty(responetStr)) {
                    if (isCancle.get()) {
                        isRunning.set(false);
                        return;
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            hisResult.faile(HisError.GetHisDateFailed);
                        }
                    });
                    isRunning.set(false);
                    return;
                }
                responetStr = responetStr.replaceAll("&lt;", "<");
                responetStr = responetStr.replaceAll("&gt;", ">");
                final List responeList =  XmlHelper.getInstance().getList(cls, responetStr, matchesNode);
                if (responeList==null) {
                    if (isCancle.get()) {
                        isRunning.set(false);
                        return;
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            hisResult.faile(HisError.XmlGetNull);
                        }
                    });
                    isRunning.set(false);
                    return;
                }
                if (isCancle.get()){
                    isRunning.set(false);
                    return;
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCurrentTask.equals(requstStr)) {
                            hisResult.result(responeList);
                        } else {
                            MyLog.d("HisHttpHandler %s is cancel!");
                        }
                    }
                });
                isRunning.set(false);
            }
        });
    }

    public void cancle() {
        isCancle.set(true);
        iHttp.cancelAll(HTTPTAG);
        mHandler.removeCallbacksAndMessages(null);
    }

    public interface HisResult<T> {
        void result(List<T> list);

        void faile(int hisError);
    }
}
