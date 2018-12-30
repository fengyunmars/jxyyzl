package com.ppfuns.jxyyzl.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.VerticalGridView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.ui.adapter.StringAdapter;
import com.ppfuns.jxyyzl.utils.MyLog;
import com.ppfuns.jxyyzl.view.ListViewTv;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:Administrator
 * Time:2017/12/27.23:41
 */

public abstract class LvGvActivity extends BaseActivity {
    protected List<String> mMenuList = new ArrayList<>();
    protected RelativeLayout background;
    protected LinearLayout ll01, ll02, ll03, ll04;
    protected ImageView iv01, iv02, iv03, iv04;
    protected TextView tv01, tv02, tv03, tv04;
    protected ListViewTv listView;
    protected VerticalGridView gridView;
    protected StringAdapter menuAdapter;
    protected String menuText1 = "选择医院";
    protected String menuText2 = "选择科室和日期";
    protected String menuText3 = "选择医生";
    protected String menuText4 = "选择时间";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvgv);
        background = findViewById(R.id.lvgv_background);
        ll01 = findViewById(R.id.lvgv_ll01);
        ll02 = findViewById(R.id.lvgv_ll02);
        ll03 = findViewById(R.id.lvgv_ll03);
        ll04 = findViewById(R.id.lvgv_ll04);
        iv01 = findViewById(R.id.lvgv_iv01);
        iv02 = findViewById(R.id.lvgv_iv02);
        iv03 = findViewById(R.id.lvgv_iv03);
        iv04 = findViewById(R.id.lvgv_iv04);
        tv01 = findViewById(R.id.lvgv_tv01);
        tv02 = findViewById(R.id.lvgv_tv02);
        tv03 = findViewById(R.id.lvgv_tv03);
        tv04 = findViewById(R.id.lvgv_tv04);
        listView = findViewById(R.id.lvgv_lv01);
        gridView = findViewById(R.id.lvgv_gv01);
        initData();
        initView(this);
    }

    public abstract void initData();

    protected void initView(Context context) {
        tv01.setText(menuText1);
        tv02.setText(menuText2);
        tv03.setText(menuText3);
        tv04.setText(menuText4);
        setBackground();
        menuAdapter = new StringAdapter(this, mMenuList);
        listView.setAdapter(menuAdapter);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, final long l) {
                listViewSelect(i, l, mMenuList.get((int) l));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                MyLog.d();
            }
        });
    }

    protected void setBackground(){
    };

    protected abstract void listViewSelect(int i, long l, String s);

}
