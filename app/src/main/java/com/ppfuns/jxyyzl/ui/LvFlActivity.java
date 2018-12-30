package com.ppfuns.jxyyzl.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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

public abstract class LvFlActivity extends BaseActivity {
    protected List<String> mMenuList = new ArrayList<>();
    protected ListViewTv listView;
    public LinearLayout ll01, ll02;
    public ImageView iv01, iv02;
    public TextView tv01, tv02;
    protected RelativeLayout background;
    protected StringAdapter menuAdapter;
    protected String menuText1 = "";
    protected String menuText2 = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvfm);
        initData();
        initView(this);
    }

    public abstract void initData();

    protected void initView(Context context) {
        background = findViewById(R.id.lvfm_background);
        setBackground();
        listView = findViewById(R.id.yygh_lv01);
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

        ll01 = findViewById(R.id.lvfm_ll01);
        ll02 = findViewById(R.id.lvfm_ll02);
        iv01 = findViewById(R.id.menuiv01);
        iv02 = findViewById(R.id.menuiv02);
        tv01 = findViewById(R.id.menutv01);
        tv01.setText(menuText1);
        tv02 = findViewById(R.id.menutv02);
        tv02.setText(menuText2);
    }

    protected abstract void listViewSelect(int i, long l, String s);

    protected abstract void setBackground();

    protected void replaceFragment(String classname) {
        if(TextUtils.isEmpty(classname)) return;
        try {
            FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
            Class<?> c = Class.forName("com.ppfuns.jxyyzl.ui.fragment." + classname);
            Fragment fragment = (Fragment) c.newInstance();
            transaction1.replace(R.id.jzrgladd_fl01, fragment);
            transaction1.commit();
        } catch (ClassNotFoundException e) {
            MyLog.e("ShowFragment->" + e.toString());
        } catch (InstantiationException e) {
            MyLog.e("ShowFragment->" + e.toString());
        } catch (IllegalAccessException e) {
            MyLog.e("ShowFragment->" + e.toString());
        }

    }
}
