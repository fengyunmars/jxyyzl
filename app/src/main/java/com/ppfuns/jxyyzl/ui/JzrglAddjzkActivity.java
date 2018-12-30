package com.ppfuns.jxyyzl.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ppfuns.jxyyzl.R;

/**
 * Author:Administrator
 * Time:2017/12/27.23:41
 */

public class JzrglAddjzkActivity extends LvFlActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ll02.setVisibility(View.VISIBLE);
        listView.setNextFocusRightId(R.id.jzk1_et_xm);
    }

    @Override
    public void initData() {
        mMenuList.add("就诊卡");
        menuText1 = "添加就诊卡";
        menuText2 = "补充就诊信息";
        replaceFragment("JzkAddFragment");
    }

    @Override
    protected void listViewSelect(int i, long l, String s) {
    }

    @Override
    protected void setBackground() {
        background.setBackgroundResource(R.drawable.jzrglbak);
    }
}
