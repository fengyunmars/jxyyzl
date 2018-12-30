package com.ppfuns.jxyyzl.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.Pople;

/**
 * Author:Administrator
 * Time:2017/12/27.23:41
 */

public class JzrglEditActivity extends LvFlActivity {
    public Pople pople;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ll02.setVisibility(View.VISIBLE);
        try {
            pople = (Pople) getIntent().getSerializableExtra("POPLE");
            replaceFragment(getIntent().getStringExtra("FRAGMENT"));
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            String fm = getIntent().getStringExtra("FRAGMENT");
            switch (fm) {
                case "JzkEditFragment":
                    iv02.setVisibility(View.VISIBLE);
                    break;
                case "JzrEdit1Fragment":
                case "JzrEdit2Fragment":
                    iv02.setVisibility(View.INVISIBLE);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        try {
            String fm = getIntent().getStringExtra("FRAGMENT");
            switch (fm) {
                case "JzkEditFragment":
                    mMenuList.add("就诊卡");
                    menuText1 = "修改就诊卡";
                    menuText2 = "补充就诊信息修改";
                    break;
                case "JzrEdit1Fragment":
                    mMenuList.add("就诊人");
                    menuText1 = "修改成人资料";
                    menuText2 = "";
                    break;
                case "JzrEdit2Fragment":
                    mMenuList.add("就诊人");
                    menuText1 = "修改儿童资料";
                    menuText2 = "";
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void listViewSelect(int i, long l, String s) {
    }

    @Override
    protected void setBackground() {
        background.setBackgroundResource(R.drawable.jzrglbak);
    }
}
