package com.ppfuns.jxyyzl.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v17.leanback.widget.VerticalGridView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.DayInfo;
import com.ppfuns.jxyyzl.data.DoctorSchedule;
import com.ppfuns.jxyyzl.ui.adapter.DoctorInfoAdapter;
import com.ppfuns.jxyyzl.ui.adapter.StringAdapter;
import com.ppfuns.jxyyzl.utils.MyLog;
import com.ppfuns.jxyyzl.view.ListViewTv;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author:Administrator
 * Time:2017/12/24.8:40
 */

public class SelectDoctorActivity extends BaseActivity{
    private DayInfo dayInfo;
    private ListViewTv listViewTv;
    private VerticalGridView verticalGridView;

    private List<String> doctorTypeList = new ArrayList<>();
    private List<DoctorSchedule> allDoctorScheduleList = new ArrayList<>();
    private List<DoctorSchedule> doctorScheduleList = new ArrayList<>();
    private StringAdapter doctorTypeAdapter;
    private DoctorInfoAdapter doctorInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        initData();
        initView(this);
    }

    private void initData() {
        Intent intent = getIntent();
        String hospitalId = intent.getStringExtra("HOSPITALID");
        dayInfo = (DayInfo) intent.getSerializableExtra("DEPTDATE");
        MyLog.d("hospitalid=%s,deptData=%s",hospitalId, dayInfo.toString());
        if(dayInfo!=null&&dayInfo.getDoctorScheduleList()!=null){
            allDoctorScheduleList = dayInfo.getDoctorScheduleList();
            doctorTypeList.clear();
            Set<String> set = new HashSet<>();
            for(DoctorSchedule doctorSchedule: allDoctorScheduleList){
                if(doctorSchedule.getRegType().contains("普通")){
                    set.add("普通门诊");
                }else{
                    set.add("专家门诊");
                }
            }

            doctorTypeList.addAll(set);
        }
    }

    private void initView(Context context) {
        listViewTv = findViewById(R.id.yygh_lv01);
        doctorTypeAdapter = new StringAdapter(this,doctorTypeList);
        listViewTv.setAdapter(doctorTypeAdapter);

        verticalGridView = findViewById(R.id.jzrgladd_fl01);
        verticalGridView.setNumColumns(2);
        doctorInfoAdapter = new DoctorInfoAdapter(this,doctorScheduleList);
        verticalGridView.setAdapter(doctorInfoAdapter);

        listViewTv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doctorScheduleList.clear();
                List<DoctorSchedule> list = new ArrayList<>();
                if(allDoctorScheduleList!=null){
                    for(DoctorSchedule doctorSchedule:allDoctorScheduleList){
                        if(doctorTypeList.get((int) id).equals("普通门诊")){
                            if((!TextUtils.isEmpty(doctorSchedule.getRegType()))&&(doctorSchedule.getRegType().contains("普通"))) {
                                list.add(doctorSchedule);
                            }
                        }else{
                            if((TextUtils.isEmpty(doctorSchedule.getRegType()))||(!doctorSchedule.getRegType().contains("普通"))) {
                                list.add(doctorSchedule);
                            }
                        }
                    }
                }
                for(DoctorSchedule ds1:list){
                    boolean flag = false;
                    for(DoctorSchedule ds2:doctorScheduleList){
                        if(ds1.getDoctorId().equals(ds2.getDoctorId())){
                            ds2.setScheduleId(ds2.getScheduleId() + "#FLYPPFUNS#" + ds1.getScheduleId());
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        doctorScheduleList.add(ds1);
                    }
                }
                doctorInfoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        doctorInfoAdapter.setItemClickListener(new DoctorInfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, DoctorSchedule doctorSchedule) {
                Intent intent = new Intent(SelectDoctorActivity.this, SelectTimeActivity.class);
                intent.putExtra("DoctorSchedule", doctorSchedule);
                startActivity(intent);
            }
        });
    }
}
