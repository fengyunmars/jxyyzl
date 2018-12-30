package com.ppfuns.jxyyzl;

import android.app.Application;

import com.ppfuns.jxyyzl.data.Pople;
import com.ppfuns.jxyyzl.http.MyHttp;

/**
 * Author: Fly
 * Time: 17-12-14 下午6:40.
 * Discription: This is MyApp
 */

public class MyApp extends Application {
    private Pople pople;
    @Override
    public void onCreate() {
        super.onCreate();
        MyHttp.getInstance().Init(this);
    }

    public Pople getPople() {
        return pople;
    }

    public void setPople(Pople pople) {
        this.pople = pople;
    }
}
