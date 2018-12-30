package com.ppfuns.jxyyzl.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.constant.HisReqXmlText;
import com.ppfuns.jxyyzl.data.AppointInfo;
import com.ppfuns.jxyyzl.data.OrderInfo;
import com.ppfuns.jxyyzl.module.HealthAppTools;
import com.ppfuns.jxyyzl.module.HisHttpHandle;
import com.ppfuns.jxyyzl.module.OrdelManager;
import com.ppfuns.jxyyzl.ui.adapter.AppointInfoAdapter;
import com.ppfuns.jxyyzl.utils.MyLog;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

/**
 * Author: Fly
 * Time: 17-12-21 上午8:26.
 * Discription: This is DataFragment
 */

public class AppointInfoActivity extends LvGvActivity implements OrdelManager.ResultAllOrderinfo {
    HisHttpHandle<AppointInfo> hisHttpHandleAinfo = new HisHttpHandle<>();
    private String crtMenuStr = "进行中";
    private ProgressDialog progressDialog;
    private Queue<OrderInfo> orderInfoArrayDeque = new ArrayDeque<>();
    private List<AppointInfo> allList = new ArrayList<>();
    private List<AppointInfo> showList = new ArrayList<>();
    private AppointInfoAdapter appointInfoAdapter;
    HisHttpHandle.HisResult<AppointInfo> appointInfoHisResult = new HisHttpHandle.HisResult<AppointInfo>() {
        @Override
        public void result(List<AppointInfo> list) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            MyLog.d("AppointInfo=" + list);
            if (list != null && !list.isEmpty()) {
                allList.addAll(list);
            }
            updateGridView();
            getNextAppinfo();
        }

        @Override
        public void faile(int hisError) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    };
    private OrdelManager ordelManager;

    @Override
    public void initData() {
        mMenuList.add("进行中");
        mMenuList.add("已完成");
        menuText1 = "查看或取消预约";
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
        appointInfoAdapter = new AppointInfoAdapter(this, showList);
        gridView.setNumColumns(1);
        gridView.setAdapter(appointInfoAdapter);
    }

    @Override
    protected void listViewSelect(int i, long l, String s) {
        crtMenuStr = s;
        updateGridView();
    }

    @Override
    protected void setBackground() {
        background.setBackgroundResource(R.drawable.yyjlbak);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        HisReqXmlText.userToken = HealthAppTools.getUserToken(this);
        ordelManager = new OrdelManager(this);
        progressDialog = new ProgressDialog(this);
        try {
            ordelManager.getAllOrderInfo(HealthAppTools.getUserToken(this), this);
        } catch (Exception e) {
            Toast.makeText(this, "读取预约订单失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        updateGridView();
    }

    private void getNextAppinfo() {
        if (!orderInfoArrayDeque.isEmpty()) {
            OrderInfo info = orderInfoArrayDeque.poll();
            hisHttpHandleAinfo.requset(this,
                    "appointInfo",
                    HisReqXmlText.requestOrderInfo,
                    AppointInfo.class,
                    appointInfoHisResult,
                    info.getHospitalId(),
                    info.getHisOrderId());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void updateGridView() {
        showList.clear();
        for (AppointInfo info : allList) {
            switch (info.getState()) {
                case "0":
                case "5":
                case "6":
                case "7":
                    if ("进行中".equals(crtMenuStr)) {
                        showList.add(info);
                    }
                    break;
                case "1":
                case "2":
                case "3":
                case "4":
                case "8":
                default:
                    if ("已完成".equals(crtMenuStr)) {
                        showList.add(info);
                    }
                    break;
            }
        }
        if ("已完成".equals(crtMenuStr)) {
            Collections.sort(showList, new Comparator<AppointInfo>() {
                @Override
                public int compare(AppointInfo lhs, AppointInfo rhs) {
                    if (lhs.getDate().compareTo(rhs.getDate()) > 0) {
                        return -1;
                    } else if (lhs.getDate().compareTo(rhs.getDate()) == 0) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
        } else {
            Collections.sort(showList, new Comparator<AppointInfo>() {
                @Override
                public int compare(AppointInfo lhs, AppointInfo rhs) {
                    return lhs.getDate().compareTo(rhs.getDate());
                }
            });
        }

        appointInfoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (ordelManager != null) {
            ordelManager.cancle();
        }
        if (hisHttpHandleAinfo != null) {
            hisHttpHandleAinfo.cancle();
        }
        super.onDestroy();
    }

    @Override
    public void result(Boolean flag, List<OrderInfo> list) {
        if (!flag) {
            Toast.makeText(this, "同步服务器数据失败", Toast.LENGTH_LONG).show();
        }
        if (list == null) return;
        orderInfoArrayDeque.clear();
        allList.clear();
        showList.clear();
        appointInfoAdapter.notifyDataSetChanged();
        Collections.sort(list, new Comparator<OrderInfo>() {
            @Override
            public int compare(OrderInfo lhs, OrderInfo rhs) {
                if (lhs.getCreateTime().compareTo(rhs.getCreateTime()) > 0) {
                    return -1;
                } else if (lhs.getCreateTime().compareTo(rhs.getCreateTime()) == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        orderInfoArrayDeque.addAll(list);
        if (!orderInfoArrayDeque.isEmpty()) {
            progressDialog.setMessage("正在连接服务器读取数据，请稍候.....");
            progressDialog.show();
            getNextAppinfo();
        } else {
            Toast.makeText(this, "没有预约订单", Toast.LENGTH_SHORT).show();
        }
    }
}
