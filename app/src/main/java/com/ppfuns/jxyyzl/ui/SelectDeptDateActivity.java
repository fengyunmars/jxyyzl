package com.ppfuns.jxyyzl.ui;

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
import com.ppfuns.jxyyzl.data.DayInfo;
import com.ppfuns.jxyyzl.data.Dept;
import com.ppfuns.jxyyzl.module.DayHandler;
import com.ppfuns.jxyyzl.module.HisHttpHandle;
import com.ppfuns.jxyyzl.module.TokenHandler;
import com.ppfuns.jxyyzl.ui.adapter.DateAdapter;
import com.ppfuns.jxyyzl.ui.adapter.DeptAdapter;
import com.ppfuns.jxyyzl.utils.MyLog;
import com.ppfuns.jxyyzl.view.ListViewTv;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Fly
 * Time: 17-12-21 上午8:26.
 * Discription: This is DataFragment
 */

public class SelectDeptDateActivity extends BaseActivity {
    HisHttpHandle<Dept> hisHttpHandleDept = new HisHttpHandle<>();
    private List<Dept> mDeptList = new ArrayList<>();
    private List<DayInfo> mDateList = new ArrayList<>();
    private ListViewTv listView;
    private VerticalGridView verticalGridView;
    private DeptAdapter deptAdapter;
    private ProgressDialog progressDialog;
    private HisHttpHandle.HisResult<Dept> hisResultDept = new HisHttpHandle.HisResult<Dept>() {
        @Override
        public void result(List<Dept> list) {
            mDeptList.clear();
            mDeptList.addAll(list);
            deptAdapter.notifyDataSetChanged();
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
                Toast.makeText(SelectDeptDateActivity.this, "从网络获取数据失败！", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private DateAdapter dateAdapter;
    private DayHandler.DoctorResult doctorResult = new DayHandler.DoctorResult() {
        @Override
        public void result(List<DayInfo> list) {
            if (progressDialog == null) {
                return;
            }
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            mDateList.clear();
            mDateList.addAll(list);
            dateAdapter.notifyDataSetChanged();
        }

        @Override
        public void faile(int error) {
            try {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(SelectDeptDateActivity.this, "从网络获取数据失败！", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private String hospitalId = "30655";
    private String hosName = "南昌市第一人民医院";
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在连接服务器读取数据，请稍候.....");
        setContentView(R.layout.activity_dept);
        initView(this);
        Intent intent = getIntent();
        if (intent != null) {
            String str1 = intent.getStringExtra("HOSPITALID");
            if (!TextUtils.isEmpty(str1)) {
                hospitalId = str1;
            }

            String str2 = intent.getStringExtra("HOSNAME");
            if (!TextUtils.isEmpty(str2)) {
                hosName = str2;
            }
        }

        hisHttpHandleDept.requset(this,
                "deptInfo",
                HisReqXmlText.requestDept,
                Dept.class,
                hisResultDept,
                hospitalId);

    }

    private void initView(Context context) {
        listView = findViewById(R.id.yygh_lv01);
        verticalGridView = findViewById(R.id.jzrgladd_fl01);

        deptAdapter = new DeptAdapter(this, mDeptList);
        listView.setAdapter(deptAdapter);

        dateAdapter = new DateAdapter(this, mDateList);
        verticalGridView.setNumColumns(4);
        verticalGridView.setAdapter(dateAdapter);

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, final long l) {
                if (!TokenHandler.getInstance().tokenIsValid()) {
                    progressDialog.show();
                }
                mHandler.removeCallbacksAndMessages(null);
                mDateList.clear();
                dateAdapter.notifyDataSetChanged();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DayHandler.getInstance().request(SelectDeptDateActivity.this,
                                hospitalId,
                                hosName,
                                mDeptList.get((int) l),
                                16,
                                doctorResult);
                    }
                }, 100);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                MyLog.d();
            }
        });

        dateAdapter.setItemClickListener(new DateAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, DayInfo deptDate) {
                Intent intent = new Intent(SelectDeptDateActivity.this, SelectDoctorActivity.class);
                intent.putExtra("HOSPITALID", hospitalId);
                intent.putExtra("DEPTDATE", deptDate);
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
        hisHttpHandleDept.cancle();
        DayHandler.getInstance().cancle();
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
