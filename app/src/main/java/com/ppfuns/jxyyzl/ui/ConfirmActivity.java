package com.ppfuns.jxyyzl.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ppfuns.jxyyzl.MyApp;
import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.constant.HisReqXmlText;
import com.ppfuns.jxyyzl.data.DoctorSchedule;
import com.ppfuns.jxyyzl.data.HisOrderResp;
import com.ppfuns.jxyyzl.data.OrderInfo;
import com.ppfuns.jxyyzl.data.Pople;
import com.ppfuns.jxyyzl.data.RegInfo;
import com.ppfuns.jxyyzl.module.HisHttpHandle;
import com.ppfuns.jxyyzl.module.OrdelManager;
import com.ppfuns.jxyyzl.module.PopleManager;
import com.ppfuns.jxyyzl.utils.DateUtils;

import java.util.List;

/**
 * Author:Administrator
 * Time:2017/12/28.11:40
 */

public class ConfirmActivity extends BaseActivity implements View.OnClickListener {
    HisHttpHandle<HisOrderResp> hisHttpHandleOrder = new HisHttpHandle<>();
    private TextView tv01, tv02;
    private Button bt01, bt02, bt03;
    private DoctorSchedule doctorSchedule;
    private RegInfo regInfo;
    private PopleManager popleManager;
    private OrdelManager ordelManager;
    private ProgressDialog progressDialog;
    private Pople confirmPople;
    HisHttpHandle.HisResult<HisOrderResp> hisResultOrder = new HisHttpHandle.HisResult<HisOrderResp>() {
        @Override
        public void result(List<HisOrderResp> list) {
            progressDialog.dismiss();
            if (list != null && !list.isEmpty()) {
                final HisOrderResp hisOrder = list.get(0);
                if (!TextUtils.isEmpty(hisOrder.getHisOrderId())) {
                    OrderInfo orderInfo = new OrderInfo();
                    orderInfo.setUserToken(confirmPople.getUserToken());
                    orderInfo.setPhone(confirmPople.getPhone());
                    orderInfo.setHisOrderId(hisOrder.getHisOrderId());
                    orderInfo.setHospitalId(doctorSchedule.getHospitalId());
                    orderInfo.setHosName(doctorSchedule.getHosName());
                    orderInfo.setDeptId(doctorSchedule.getDeptId());
                    orderInfo.setDeptName(doctorSchedule.getDeptName());
                    orderInfo.setRegDate(regInfo.getRegDate());
                    orderInfo.setCreateTime(DateUtils.getCurrentDateString("yyyy-MM-dd HH:mm"));
                    orderInfo.setName(confirmPople.getName());
                    orderInfo.setSex(confirmPople.getSex());
                    orderInfo.setDenNo(confirmPople.getDenNo());
                    orderInfo.setStartendTime(regInfo.getStartTime() + "-" + regInfo.getEndTime());
                    ordelManager.saveOrderInfo(orderInfo, new OrdelManager.Result() {
                        @Override
                        public void result(boolean flag) {
                            Intent intent = new Intent(ConfirmActivity.this, OrderResultActivity.class);
                            intent.putExtra("DoctorSchedule", doctorSchedule);
                            intent.putExtra("RegInfo", regInfo);
                            intent.putExtra("HisOrder", hisOrder);
                            if (flag) {
                                //预约成功，显示结果
                                startActivity(intent);
                            } else {
                                intent.putExtra("error", "同步上传订单信息失败！只能本机查询订单信息！");
                                startActivity(intent);
                            }
                        }
                    });
                } else {
                    Toast.makeText(ConfirmActivity.this, "预约失败!" + hisOrder.getM(), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(ConfirmActivity.this, "预约失败!请稍候尝试!", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void faile(int hisError) {
            Toast.makeText(ConfirmActivity.this, "预约失败!请稍候尝试!", Toast.LENGTH_LONG).show();
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在提交预约申请......");

        popleManager = new PopleManager(this);
        ordelManager = new OrdelManager(this);

        tv01 = findViewById(R.id.cf_tv01);
        tv02 = findViewById(R.id.cf_tv02);
        bt01 = findViewById(R.id.cf_bt01);
        bt02 = findViewById(R.id.cf_bt02);
        bt03 = findViewById(R.id.cf_bt03);
        bt01.setOnClickListener(this);
        bt02.setOnClickListener(this);
        bt03.setOnClickListener(this);
        Intent intent1 = getIntent();
        doctorSchedule = (DoctorSchedule) intent1.getSerializableExtra("DoctorSchedule");
        regInfo = (RegInfo) intent1.getSerializableExtra("RegInfo");
        if (((MyApp) getApplication()).getPople() == null) {
            ((MyApp) getApplication()).setPople(popleManager.getDefultPople());
        }
        //如果没有就诊人，跳到就诊人选择页面
        if (((MyApp) getApplication()).getPople() == null || TextUtils.isEmpty(((MyApp) getApplication()).getPople().getName())) {
            Intent intent2 = new Intent(ConfirmActivity.this, SelectPopleActivity.class);
            startActivity(intent2);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        //更新预约界面
        upView();
    }


    private void upView() {
        bt03.requestFocus();
        if (doctorSchedule != null && regInfo != null) {
            String type = (doctorSchedule.getRegType() != null && doctorSchedule.getRegType().contains("普通")) ? "普通门诊" : "专家门诊";
            String text2 = "就诊医院：" + doctorSchedule.getHosName() +
                    "\n就诊科室：" + doctorSchedule.getDeptName() +
                    "\n就诊医生：" + doctorSchedule.getDoctorName() +
                    "\n预约时间：" + regInfo.getRegDate() +
                    "\n就诊类型：" + type +
                    "\n诊察费用：￥" + doctorSchedule.getRegFee() + "元";
            tv02.setText(text2);
        }
        if (((MyApp) getApplication()).getPople() != null) {
            String text1 = "姓名：" + (((MyApp) getApplication()).getPople().getName() == null ? "" : ((MyApp) getApplication()).getPople().getName()) +
                    "\n身份证号：" + (((MyApp) getApplication()).getPople().getDenNo() == null ? "" : ((MyApp) getApplication()).getPople().getDenNo());
            tv01.setText(text1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cf_bt01:
                Intent intent = new Intent(ConfirmActivity.this, SelectPopleActivity.class);
                startActivity(intent);
                break;
            case R.id.cf_bt02:
                finish();
                break;
            case R.id.cf_bt03:
                if (((MyApp) getApplication()).getPople() == null || TextUtils.isEmpty(((MyApp) getApplication()).getPople().getName())) {
                    Toast.makeText(ConfirmActivity.this, "请选择就诊人!", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(ConfirmActivity.this, SelectPopleActivity.class);
                    startActivity(intent1);
                    return;
                }
                String bizcode = doctorSchedule.getHosName() +
                        "|" + ((MyApp) getApplication()).getPople().getName() +
                        "|" + regInfo.getStartTime() + "-" + regInfo.getEndTime() +
                        "|" + doctorSchedule.getHospitalId();
                confirmPople = ((MyApp) getApplication()).getPople();
                hisHttpHandleOrder.requset(
                        this,
                        "Resp",
                        HisReqXmlText.requestOrder,
                        HisOrderResp.class,
                        hisResultOrder,
                        doctorSchedule.getHospitalId(),
                        confirmPople.getCardNo(),
                        confirmPople.getCardType(),
                        regInfo.getCode(),
                        confirmPople.getDenNo(),
                        confirmPople.getName(),
                        confirmPople.getSex(),
                        confirmPople.getBirthday(),
                        confirmPople.getPhone(),
                        confirmPople.getGuardian(),
                        confirmPople.getIsguardian(),
                        bizcode);
                progressDialog.show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        popleManager.cancle();
        ordelManager.cancle();
        hisHttpHandleOrder.cancle();
        super.onDestroy();
    }
}