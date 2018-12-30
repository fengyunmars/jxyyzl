package com.ppfuns.jxyyzl.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.DoctorSchedule;
import com.ppfuns.jxyyzl.data.HisOrderResp;
import com.ppfuns.jxyyzl.data.RegInfo;

/**
 * Author:Administrator
 * Time:2017/12/28.11:40
 */

public class OrderResultActivity extends BaseActivity{
    private TextView tv01,tv02;
    private DoctorSchedule doctorSchedule;
    private RegInfo regInfo;
    private HisOrderResp hisOrder;
    private String error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderresult);
        tv01 = findViewById(R.id.od_tv01);
        tv02 = findViewById(R.id.od_tv02);
        Intent intent = getIntent();
        doctorSchedule = (DoctorSchedule) intent.getSerializableExtra("DoctorSchedule");
        regInfo = (RegInfo) intent.getSerializableExtra("RegInfo");
        hisOrder = (HisOrderResp) intent.getSerializableExtra("HisOrder");
        error = intent.getStringExtra("error");
        error= error==null?"":error;
        try {
            String type = (doctorSchedule.getRegType()!=null&&doctorSchedule.getRegType().contains("普通"))?"普通门诊":"专家门诊";
            String stringBuffer = "订单号：" + hisOrder.getHisOrderId() + "\n\n" +
                    "挂号类型：" + type + "\n" +
                    "就诊医院：" + doctorSchedule.getHosName() + "\n" +
                    "就诊科室：" + doctorSchedule.getDeptName() + "\n" +
                    "就诊医生：" + doctorSchedule.getDoctorName() + "\n\n" +
                    "预约时间：" + regInfo.getRegDate() + " "+regInfo.getStartTime() + "-" + regInfo.getEndTime()+"\n" +
                    "诊察费用：￥" + regInfo.getRegFee() + "元\n";

            tv01.setText(stringBuffer);
            tv02.setText(error);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderResultActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
