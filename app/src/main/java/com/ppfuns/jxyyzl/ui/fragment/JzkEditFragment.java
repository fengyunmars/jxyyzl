package com.ppfuns.jxyyzl.ui.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.constant.HospitalList;
import com.ppfuns.jxyyzl.data.Pople;
import com.ppfuns.jxyyzl.module.PopleManager;
import com.ppfuns.jxyyzl.ui.JzrglEditActivity;
import com.ppfuns.jxyyzl.utils.IDCard;
import com.ppfuns.jxyyzl.view.SelectDateView;

/**
 * Author:Administrator
 * Time:2017/12/28.1:42
 */

public class JzkEditFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout fl01, fl02;
    private Button bt_cx1, bt_cx2, bt_next, bt_tj, bt_sr;
    private EditText et_xm1, et_kh, et_ktype, et_hs, et_xm2, et_sfz, et_ph, et_sex;
    private CheckBox ck01;
    private TextView tv_xm;
    private ProgressDialog progressDialog;
    private PopleManager popleManager;
    private String cardType = "";
    private String hospitalId = "30655";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jzkadd, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
        popleManager = new PopleManager(getActivity());
        fl01 = view.findViewById(R.id.jzk1_fl01);
        fl02 = view.findViewById(R.id.jzk2_fl02);
        et_xm1 = view.findViewById(R.id.jzk1_et_xm);
        et_kh = view.findViewById(R.id.jzk1_et_kh);
        et_ktype = view.findViewById(R.id.jzk1_et_ktype);
        et_hs = view.findViewById(R.id.jzk1_et_hs);

        et_xm2 = view.findViewById(R.id.jzk2_et_xm2);
        et_sfz = view.findViewById(R.id.jzk2_et_sfz);
        et_ph = view.findViewById(R.id.jzk2_et_ph);
        et_sex = view.findViewById(R.id.jzk2_et_sex);

        ck01 = view.findViewById(R.id.jzk2_ck01);
        tv_xm = view.findViewById(R.id.jzk2_tv_xm);

        bt_cx1 = view.findViewById(R.id.jzk1_bt_cx);
        bt_next = view.findViewById(R.id.jzk1_bt_next);
        bt_cx2 = view.findViewById(R.id.jzk2_bt_cx2);
        bt_tj = view.findViewById(R.id.jzk2_bt_tj);
        bt_sr = view.findViewById(R.id.jzk2_bt_sr);

        et_ph.setKeyListener(new NumberKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_CLASS_PHONE;
            }

            @NonNull
            @Override
            protected char[] getAcceptedChars() {
                return new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
            }
        });

        bt_cx1.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        bt_cx2.setOnClickListener(this);
        bt_tj.setOnClickListener(this);
        bt_sr.setOnClickListener(this);

        Pople pople = ((JzrglEditActivity) getActivity()).pople;

        if (pople != null) {

            et_xm1.setText(pople.getName());
            et_xm1.setFocusable(false);
            et_xm1.setBackgroundResource(R.drawable.enablefalse);

            et_kh.setText(pople.getCardNo());
            et_kh.setFocusable(false);
            et_kh.setBackgroundResource(R.drawable.enablefalse);

            et_ktype.setText(pople.getCardType().equals("0")?"医院就诊卡":"居民健康卡");
            et_ktype.setFocusable(false);
            et_ktype.setBackgroundResource(R.drawable.enablefalse);


            et_hs.setText(HospitalList.getHosName(pople.getHospitalId()));
            et_hs.setFocusable(false);
            et_hs.setBackgroundResource(R.drawable.enablefalse);


            et_sfz.setText(pople.getDenNo());
            et_sfz.setFocusable(false);
            et_sfz.setBackgroundResource(R.drawable.enablefalse);

            et_ph.setText(pople.getPhone());

            et_sex.setText(pople.getSex());
            et_sex.setFocusable(false);
            et_sex.setBackgroundResource(R.drawable.enablefalse);


            if("1".equals(pople.getIsguardian())){
                ck01.setChecked(true);
                bt_sr.setFocusable(true);
                bt_sr.setBackgroundResource(R.drawable.edit_focus);

                et_xm2.setFocusable(true);
                et_xm2.setHintTextColor(0xFF000000);
                et_xm2.setBackgroundResource(R.drawable.edit_focus);

                bt_sr.setFocusable(true);
                bt_sr.setHintTextColor(0xFF000000);
                bt_sr.setBackgroundResource(R.drawable.edit_focus);
                bt_sr.setHint("选择儿童生日");

                et_sfz.setHint("输入监护人身份证号");
                et_ph.setHint("输入监护人手机号码");

            }else{
                ck01.setChecked(false);
                et_xm2.setFocusable(false);
                et_xm2.setHintTextColor(0xFFFFFFFF);
                et_xm2.setBackgroundResource(R.drawable.enablefalse);

                bt_sr.setFocusable(false);
                bt_sr.setHintTextColor(0xFFFFFFFF);
                bt_sr.setBackgroundResource(R.drawable.enablefalse);
                bt_sr.setHint("出年日期");

                et_sfz.setHint("输入预约人身份证号");
                et_ph.setHint("输入预约人手机号码");
            }
            ck01.setFocusable(false);
            ck01.setBackgroundResource(R.drawable.enablefalse);
            tv_xm.setText(pople.getName());
            et_xm2.setText(pople.getGuardian());
            bt_sr.setText(pople.getBirthday());
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        fl01.setVisibility(View.VISIBLE);
        fl02.setVisibility(View.GONE);
        try {
            ((JzrglEditActivity) getActivity()).tv01.setTextColor(0xFF056B8D);
            ((JzrglEditActivity) getActivity()).tv02.setTextColor(0xFFEEEEEE);
            ((JzrglEditActivity) getActivity()).iv01.setImageResource(R.drawable.num1y);
            ((JzrglEditActivity) getActivity()).iv02.setImageResource(R.drawable.num2n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jzk2_bt_sr:
                selectBirthday();
                break;
            case R.id.jzk1_bt_cx:
            case R.id.jzk2_bt_cx2:
                getActivity().finish();
                break;
            case R.id.jzk1_bt_next:
                if (TextUtils.isEmpty(et_xm1.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入姓名", Toast.LENGTH_SHORT).show();
                    et_xm1.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(et_ktype.getText().toString())) {
                    Toast.makeText(getActivity(), "请选择卡号类型", Toast.LENGTH_SHORT).show();
                    et_ktype.requestFocus();
                    return;
                }

                if (et_ktype.getText().toString().equals("医院就诊卡")) {
                    cardType = "0";
                } else {
                    cardType = "1";
                }

                if (et_ktype.getText().toString().equals("医院就诊卡")) {
                    if (TextUtils.isEmpty(et_hs.getText().toString())) {
                        Toast.makeText(getActivity(), "请输入医院", Toast.LENGTH_SHORT).show();
                        et_hs.requestFocus();
                        return;
                    }
                }

                if (TextUtils.isEmpty(et_kh.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入卡号", Toast.LENGTH_SHORT).show();
                    et_kh.requestFocus();
                    return;
                }
                fl01.setVisibility(View.GONE);
                fl02.setVisibility(View.VISIBLE);
                try {
                    ((JzrglEditActivity) getActivity()).tv01.setTextColor(0xFFEEEEEE);
                    ((JzrglEditActivity) getActivity()).tv02.setTextColor(0xFF056B8D);
                    ((JzrglEditActivity) getActivity()).iv01.setImageResource(R.drawable.num1n);
                    ((JzrglEditActivity) getActivity()).iv02.setImageResource(R.drawable.num2y);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                et_ph.requestFocus();
                break;
            case R.id.jzk2_bt_tj:
                if (ck01.isChecked()) {
                    if (TextUtils.isEmpty(et_xm2.getText().toString())) {
                        Toast.makeText(getActivity(), "请输入监护人姓名", Toast.LENGTH_SHORT).show();
                        et_xm2.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(et_sex.getText().toString())) {
                        Toast.makeText(getActivity(), "请选择性别", Toast.LENGTH_SHORT).show();
                        et_sex.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(bt_sr.getText().toString())) {
                        Toast.makeText(getActivity(), "请选择儿童生日", Toast.LENGTH_SHORT).show();
                        bt_sr.requestFocus();
                        selectBirthday();
                        return;
                    }
                }

                if (!IDCard.IDCardValidate(et_sfz.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入正确的身份证号码", Toast.LENGTH_SHORT).show();
                    et_sfz.requestFocus();
                    return;
                }

                if (et_ph.getText().toString().length() != 11) {
                    Toast.makeText(getActivity(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    et_ph.requestFocus();
                    return;
                }
                upCard();
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        popleManager.cancle();
    }

    private void selectBirthday() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.select_date, null);
        final SelectDateView sd01 = view.findViewById(R.id.sd01);
        final Button bt01 = view.findViewById(R.id.bt01);
        final Button bt02 = view.findViewById(R.id.bt02);
        final AlertDialog dialog = new AlertDialog.Builder(getActivity(), R.style.Theme_Transparent)
                .setView(view)
                .create();
        sd01.setStringDate(bt_sr.getText().toString());
        bt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_sr.setText(sd01.getStringDate());
                dialog.dismiss();
            }
        });

        bt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void upCard() {
        Pople pople = ((JzrglEditActivity) getActivity()).pople;
        if(pople==null) return;
        pople.setGuardian(et_xm2.getText().toString());
        pople.setSex(et_sex.getText().toString());
        pople.setDenNo(et_sfz.getText().toString());
        String birthday = bt_sr.getText().toString().replaceAll("年", "-").replace("月", "-").replace("日", "");
        pople.setBirthday(birthday);
        pople.setPhone(et_ph.getText().toString());
        progressDialog.setMessage("正在保存数据......");
        progressDialog.show();
        popleManager.editPople(pople, new PopleManager.Result() {
            @Override
            public void result(boolean flag) {
                progressDialog.dismiss();
                if (flag) {
                    Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            }
        });


    }
}
