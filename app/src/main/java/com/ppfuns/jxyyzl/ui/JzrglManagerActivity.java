package com.ppfuns.jxyyzl.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.Pople;
import com.ppfuns.jxyyzl.module.HealthAppTools;
import com.ppfuns.jxyyzl.module.PopleManager;
import com.ppfuns.jxyyzl.ui.adapter.PopleMgAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:Administrator
 * Time:2017/12/27.23:41
 */

public class JzrglManagerActivity extends LvGvActivity implements PopleManager.ResultAllPople {
    private PopleManager popleManager;
    private List<Pople> showList = new ArrayList<>();
    private List<Pople> popleList = new ArrayList<>();
    private PopleMgAdapter popleAdapter;
    private int currentSelectId = 0;
    private ProgressDialog progressDialog;

    @Override
    public void initData() {
        mMenuList.add("就诊人");
        mMenuList.add("就诊卡");
        menuText1 = "管理就诊人";
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
        popleAdapter = new PopleMgAdapter(this, showList);
        gridView.setNumColumns(1);
        gridView.setAdapter(popleAdapter);
        popleAdapter.setItemClickListener(new PopleMgAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, Pople pople) {
                switch (view.getId()) {
                    case R.id.item:
                    case R.id.ll01:
                        jumpActivityAddPople(pople);
                        break;
                    case R.id.ll02:
                        progressDialog.setMessage("正在设置.....");
                        progressDialog.show();
                        popleManager.setDefault(pople, new PopleManager.Result() {
                            @Override
                            public void result(boolean flag) {
                                progressDialog.dismiss();
                                if (flag) {
                                    upDateView();
                                }
                            }
                        });
                        break;
                    case R.id.ll03:
                        Intent intent = new Intent(JzrglManagerActivity.this, JzrglEditActivity.class);
                        intent.putExtra("POPLE", pople);
                        if (TextUtils.isEmpty(pople.getCardNo())) {
                            if ("1".equals(pople.getIsguardian())) {
                                intent.putExtra("FRAGMENT", "JzrEdit2Fragment");
                            } else {
                                intent.putExtra("FRAGMENT", "JzrEdit1Fragment");
                            }
                        } else {
                            intent.putExtra("FRAGMENT", "JzkEditFragment");
                        }
                        startActivity(intent);
                        break;
                    case R.id.ll04:
                        progressDialog.setMessage("正在删除.....");
                        progressDialog.show();
                        popleManager.del(pople, new PopleManager.Result() {
                            @Override
                            public void result(boolean flag) {
                                progressDialog.dismiss();
                                if (!flag) {
                                    Toast.makeText(JzrglManagerActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                                } else {
                                    upDateView();
                                }
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void listViewSelect(int i, long l, String s) {
        currentSelectId = i;
        upListViewSelect();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果没有登陆跳到登陆界面，如果已登陆读取登陆ID生成TOKEN
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在同步服务器数据......");
        progressDialog.show();
        popleManager = new PopleManager(this);
        popleManager.getAllPople(HealthAppTools.getUserToken(this), this);

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

    private void upDateView() {
        popleList.clear();
        List<Pople> list = popleManager.getAllLocalPople(HealthAppTools.getUserToken(this));
        if (list != null) {
            popleList.addAll(list);
            upListViewSelect();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        upDateView();
        listView.requestFocus();
        listView.setSelection(currentSelectId);
    }

    private void jumpActivityAddPople(Pople pople) {
        if ("添加就诊人".equals(pople.getName())) {
            Intent intent = new Intent(JzrglManagerActivity.this, JzrglAddjzrActivity.class);
            startActivity(intent);
        } else if ("添加就诊卡".equals(pople.getName())) {
            Intent intent = new Intent(JzrglManagerActivity.this, JzrglAddjzkActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        if (popleManager != null) {
            popleManager.cancle();
        }
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

    @Override
    protected void setBackground() {
        background.setBackgroundResource(R.drawable.jzrglbak);
    }
}
