package com.ppfuns.jxyyzl.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.VerticalGridView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.constant.HisReqXmlText;
import com.ppfuns.jxyyzl.data.CommonRegion;
import com.ppfuns.jxyyzl.data.Hospital;
import com.ppfuns.jxyyzl.module.HealthAppTools;
import com.ppfuns.jxyyzl.module.HisHttpHandle;
import com.ppfuns.jxyyzl.ui.adapter.HospitalAdapter;
import com.ppfuns.jxyyzl.ui.adapter.RegionAdapter;
import com.ppfuns.jxyyzl.utils.MyLog;
import com.ppfuns.jxyyzl.view.ListViewTv;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Fly
 * Time: 17-12-18 上午6:54.
 * Discription: This is RegionFragment
 */

public class SelectHospitalActivity extends Activity {
    private HisHttpHandle<CommonRegion> hisHttpHandleCommonRegion;
    private HisHttpHandle<Hospital> hisHttpHandleHospital;
    private List<CommonRegion> mCommonRegionList;
    private List<Hospital> mHospitalInfoList;
    private ListViewTv listView;
    private VerticalGridView verticalGridView;
    private RegionAdapter regionAdapter;
    private HospitalAdapter hospitalAdapter;
    private ProgressDialog progressDialog;
    Runnable dialogTask = new Runnable() {
        @Override
        public void run() {
            progressDialog.setMessage("正在获取医院信息，请稍候.....");
            progressDialog.show();
        }
    };
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private HisHttpHandle.HisResult<Hospital> hospitalHisResult = new HisHttpHandle.HisResult<Hospital>() {
        @Override
        public void result(List<Hospital> list) {
            mHandler.removeCallbacksAndMessages(null);
            if (progressDialog == null) return;
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            mHospitalInfoList.clear();
            mHospitalInfoList.addAll(list);
            hospitalAdapter.notifyDataSetChanged();
        }

        @Override
        public void faile(int hisError) {
            try {
                if (progressDialog == null) return;
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(SelectHospitalActivity.this, "获取医院信息失败！", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private HisHttpHandle.HisResult<CommonRegion> commonRegionHisResult = new HisHttpHandle.HisResult<CommonRegion>() {
        @Override
        public void result(List<CommonRegion> list) {
            if (progressDialog == null) return;
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            mCommonRegionList.clear();
            mCommonRegionList.addAll(list);
            regionAdapter.notifyDataSetChanged();
            listView.requestFocus();
            listView.setSelection(0);
        }

        @Override
        public void faile(int hisError) {
            try {
                if (progressDialog == null) return;
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(SelectHospitalActivity.this, "获取地区信息失败！", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public SelectHospitalActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在获取地区信息，请稍候.....");
        progressDialog.show();
        setContentView(R.layout.activity_hospital);
        //如果没有登陆跳到登陆界面，如果已登陆读取登陆ID生成TOKEN
        if (TextUtils.isEmpty(HealthAppTools.getUserToken(this))) {
            return;
        }
        hisHttpHandleCommonRegion = new HisHttpHandle<>();
        hisHttpHandleHospital = new HisHttpHandle<>();
        mCommonRegionList = new ArrayList<>();
        mHospitalInfoList = new ArrayList<>();
        initView(this);

        hisHttpHandleCommonRegion.requset(this,
                "CommonRegion",
                HisReqXmlText.requestCommonRegion,
                CommonRegion.class,
                commonRegionHisResult);
    }


    private void initView(Context context) {
        listView = findViewById(R.id.yygh_lv01);
        verticalGridView = findViewById(R.id.jzrgladd_fl01);

        regionAdapter = new RegionAdapter(this, mCommonRegionList);
        listView.setAdapter(regionAdapter);

        hospitalAdapter = new HospitalAdapter(this, mHospitalInfoList);
        verticalGridView.setNumColumns(2);
        verticalGridView.setAdapter(hospitalAdapter);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mHospitalInfoList.clear();
                hospitalAdapter.notifyDataSetChanged();
                mHandler.removeCallbacksAndMessages(null);
                mHandler.postDelayed(dialogTask,1000);
                hisHttpHandleHospital.requset(
                        SelectHospitalActivity.this,
                        "HospitalInfo",
                        HisReqXmlText.requestHospital,
                        Hospital.class,
                        hospitalHisResult,
                        mCommonRegionList.get((int) l).getRegionCode());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                MyLog.d();
            }
        });

        hospitalAdapter.setItemClickListener(new HospitalAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, Hospital hospitalInfo) {
                Intent intent = new Intent(SelectHospitalActivity.this, SelectDeptDateActivity.class);
                intent.putExtra("HOSNAME", hospitalInfo.getHosName());
                intent.putExtra("HOSPITALID", hospitalInfo.getHospitalId());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        hisHttpHandleCommonRegion.cancle();
        hisHttpHandleHospital.cancle();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
