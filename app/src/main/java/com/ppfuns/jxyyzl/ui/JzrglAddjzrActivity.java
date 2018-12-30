package com.ppfuns.jxyyzl.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.ppfuns.jxyyzl.R;

/**
 * Author:Administrator
 * Time:2017/12/27.23:41
 */

public class JzrglAddjzrActivity extends LvFlActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void initData() {
        mMenuList.add("成人");
        mMenuList.add("儿童");
        menuText1 = "添加就诊人";
    }

    @Override
    protected void listViewSelect(int i, long l, String s) {
        if(i==0){
            replaceFragment("JzrAdd1Fragment");
        }else{
            replaceFragment("JzrAdd2Fragment");
        }
    }

    @Override
    protected void setBackground() {
        background.setBackgroundResource(R.drawable.jzrglbak);
    }
}
