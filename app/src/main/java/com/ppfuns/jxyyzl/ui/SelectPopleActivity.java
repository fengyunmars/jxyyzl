package com.ppfuns.jxyyzl.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.ppfuns.jxyyzl.MyApp;
import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.Pople;
import com.ppfuns.jxyyzl.module.HealthAppTools;
import com.ppfuns.jxyyzl.module.PopleManager;
import com.ppfuns.jxyyzl.ui.adapter.PopleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Fly
 * Time: 17-12-27 上午10:15.
 * Discription: This is SelectPopleActivity
 */

public class SelectPopleActivity extends LvGvActivity implements PopleManager.ResultAllPople {
    private PopleManager popleManager;
    private List<Pople> showList = new ArrayList<>();
    private List<Pople> popleList = new ArrayList<>();
    private PopleAdapter popleAdapter;
    private int currentSelectId = 0;
    private ProgressDialog progressDialog;

    @Override
    public void initData() {
        mMenuList.add("就诊人");
        mMenuList.add("就诊卡");

        menuText1 = "选择就诊人";
        tv01.setTextColor(0xFF056B8D);
        iv01.setImageResource(R.drawable.num1y);
        menuText2 = "";
        ll02.setVisibility(View.GONE);
        menuText3 = "";
        ll03.setVisibility(View.GONE);
        menuText4 = "";
        ll04.setVisibility(View.GONE);
    }

    @Override
    protected void initView(Context context) {
        super.initView(context);
        popleAdapter = new PopleAdapter(this, showList);
        gridView.setNumColumns(2);
        gridView.setAdapter(popleAdapter);

        popleAdapter.setItemClickListener(new PopleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, Pople pople) {
                if ("添加就诊人".equals(pople.getName())) {
                    Intent intent = new Intent(SelectPopleActivity.this, JzrglAddjzrActivity.class);
                    startActivity(intent);
                } else if ("添加就诊卡".equals(pople.getName())) {
                    Intent intent = new Intent(SelectPopleActivity.this, JzrglAddjzkActivity.class);
                    startActivity(intent);
                } else {
                    ((MyApp) getApplication()).setPople(pople);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popleManager = new PopleManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在同步服务器数据......");
        popleManager.getAllPople(HealthAppTools.getUserToken(this), this);
        progressDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        upDateView();
        listView.requestFocus();
        listView.setSelection(currentSelectId);
    }

    private void upDateView() {
        popleList.clear();
        List<Pople> list = popleManager.getAllLocalPople(HealthAppTools.getUserToken(this));
        if (list != null) {
            popleList.addAll(list);
            upListViewSelect();
        }
    }

    @Override
    protected void listViewSelect(int i, long l, String s) {
        currentSelectId = i;
        upListViewSelect();
    }


    private void upListViewSelect() {
        if (currentSelectId == 0) {
            showList.clear();
            for (Pople pople1 : popleList) {
                if (TextUtils.isEmpty(pople1.getCardNo())) {
                    showList.add(pople1);
                }
            }
            Pople pople2 = new Pople();
            pople2.setName("添加就诊人");
            showList.add(pople2);
            popleAdapter.notifyDataSetChanged();
        } else {
            showList.clear();
            for (Pople pople1 : popleList) {
                if (!TextUtils.isEmpty(pople1.getCardNo())) {
                    showList.add(pople1);
                }
            }
            Pople pople2 = new Pople();
            pople2.setName("添加就诊卡");
            showList.add(pople2);
            popleAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onDestroy() {
        popleManager.cancle();
        super.onDestroy();
    }

    @Override
    public void result(Boolean flag, List<Pople> list) {
        progressDialog.dismiss();
        upDateView();
        if (!flag) {
            Toast.makeText(this, "同步服务器数据失败", Toast.LENGTH_LONG).show();
        }
    }
}
