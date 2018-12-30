package com.ppfuns.jxyyzl.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v17.leanback.widget.VerticalGridView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.constant.HisReqXmlText;
import com.ppfuns.jxyyzl.data.DoctorSchedule;
import com.ppfuns.jxyyzl.data.RegInfo;
import com.ppfuns.jxyyzl.module.HisHttpHandle;
import com.ppfuns.jxyyzl.module.TokenHandler;
import com.ppfuns.jxyyzl.ui.adapter.RegNumberAdapter;
import com.ppfuns.jxyyzl.ui.adapter.StringAdapter;
import com.ppfuns.jxyyzl.view.ListViewTv;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Author:Administrator
 * Time:2017/12/24.8:40
 */

public class SelectTimeActivity extends BaseActivity{
    HisHttpHandle<RegInfo> hisHttpHandleReg = new HisHttpHandle<>();
    private DoctorSchedule doctorSchedule;
    private ListViewTv listViewTv;
    private VerticalGridView verticalGridView;
    private List<String> doctorList = new ArrayList<>();
    private List<RegInfo> regInfoArrayList = new ArrayList<>();
    private StringAdapter regDoctorAdapter;
    private RegNumberAdapter regNumberAdapter;
    private Queue<String> scheduleIdQueue = new ArrayDeque<>();
    private ProgressDialog progressDialog;
    HisHttpHandle.HisResult<RegInfo> hisResultReg = new HisHttpHandle.HisResult<RegInfo>() {
        @Override
        public void result(List<RegInfo> list) {
            if (progressDialog == null) {
                return;
            }
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            regInfoArrayList.addAll(list);
            regNumberAdapter.notifyDataSetChanged();
            if(!scheduleIdQueue.isEmpty()){
                hisHttpHandleReg.requset(
                        SelectTimeActivity.this,
                        "regInfo",
                        HisReqXmlText.requestReginfo,
                        RegInfo.class, hisResultReg,
                        doctorSchedule.getHospitalId(),
                        scheduleIdQueue.poll(),
                        doctorSchedule.getRegType());
            }
        }

        @Override
        public void faile(int hisError) {
            try {
                if (progressDialog == null) {
                    return;
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(SelectTimeActivity.this, "从网络获取数据失败！", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timereg);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在连接服务器读取数据，请稍候.....");
        initData();
        initView(this);
    }

    private void initData() {
        Intent intent = getIntent();
        doctorSchedule = (DoctorSchedule) intent.getSerializableExtra("DoctorSchedule");
        if(doctorSchedule !=null){
            doctorList.add(doctorSchedule.getDoctorName());
            try {
                String ds[] = doctorSchedule.getScheduleId().split("#FLYPPFUNS#");
                scheduleIdQueue.addAll(Arrays.asList(ds));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void initView(Context context) {
        listViewTv = findViewById(R.id.yygh_lv01);
        regDoctorAdapter = new StringAdapter(this, doctorList);
        listViewTv.setAdapter(regDoctorAdapter);

        verticalGridView = findViewById(R.id.jzrgladd_fl01);
        verticalGridView.setNumColumns(4);
        regNumberAdapter = new RegNumberAdapter(this,regInfoArrayList);
        verticalGridView.setAdapter(regNumberAdapter);

        listViewTv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!TokenHandler.getInstance().tokenIsValid()) {
                    progressDialog.show();
                }
                if(!scheduleIdQueue.isEmpty()){
                    hisHttpHandleReg.requset(
                            SelectTimeActivity.this,
                            "regInfo",
                            HisReqXmlText.requestReginfo,
                            RegInfo.class,
                            hisResultReg,
                            doctorSchedule.getHospitalId(),
                            scheduleIdQueue.poll(),
                            doctorSchedule.getRegType());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        regNumberAdapter.setItemClickListener(new RegNumberAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, RegInfo regInfo) {
                Intent intent = new Intent(SelectTimeActivity.this, ConfirmActivity.class);
                intent.putExtra("DoctorSchedule",doctorSchedule);
                intent.putExtra("RegInfo",regInfo);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        hisHttpHandleReg.cancle();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;
        super.onDestroy();
    }
}
