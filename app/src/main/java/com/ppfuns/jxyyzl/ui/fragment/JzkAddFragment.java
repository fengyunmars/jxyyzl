package com.ppfuns.jxyyzl.ui.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.constant.HisReqXmlText;
import com.ppfuns.jxyyzl.constant.SystemProp;
import com.ppfuns.jxyyzl.data.CheckCardRet;
import com.ppfuns.jxyyzl.data.Pople;
import com.ppfuns.jxyyzl.module.HealthAppTools;
import com.ppfuns.jxyyzl.module.HisHttpHandle;
import com.ppfuns.jxyyzl.module.PopleManager;
import com.ppfuns.jxyyzl.ui.JzrglAddjzkActivity;
import com.ppfuns.jxyyzl.utils.IDCard;
import com.ppfuns.jxyyzl.utils.MyLog;
import com.ppfuns.jxyyzl.utils.SystemPropertiesProxy;
import com.ppfuns.jxyyzl.view.SelectDateView;

import java.util.List;

/**
 * Author:Administrator
 * Time:2017/12/28.1:42
 */

public class JzkAddFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout fl01, fl02;
    private Button bt_cx1, bt_cx2, bt_next, bt_tj, bt_sr;
    private EditText et_xm1, et_kh, et_ktype, et_hs, et_xm2, et_sfz, et_ph, et_sex;
    private CheckBox ck01;
    private TextView tv_xm;
    private ProgressDialog progressDialog;
    private PopleManager popleManager;
    private String cardType = "";
    private String hospitalId = "30655";
    private HisHttpHandle<CheckCardRet> hisHttpHandle = new HisHttpHandle<>();
    private HisHttpHandle.HisResult<CheckCardRet> hisResult = new HisHttpHandle.HisResult<CheckCardRet>() {
        @Override
        public void result(List<CheckCardRet> list) {
            MyLog.d("check card result" + list);
            if (list != null && !list.isEmpty()) {
                if (list.get(0).getIsValid().equals("true")) {
                    upCard();
                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getActivity(), "就诊卡校验失败!", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getActivity(), "就诊卡校验失败!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void faile(int hisError) {
            MyLog.d("check card error %d", hisError);
            try {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "发送网络请求失败!", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

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

        et_sfz.setKeyListener(new NumberKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_CLASS_TEXT;
            }

            @NonNull
            @Override
            protected char[] getAcceptedChars() {
                return new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'X'};
            }
        });

        upCheckViewStatus(false);
        ck01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                upCheckViewStatus(isChecked);
            }
        });

        et_sfz.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String sfz = et_sfz.getText().toString();
                if (!hasFocus && !(ck01.isChecked())) {
                    if (!IDCard.IDCardValidate(sfz)) {
                        Toast.makeText(getActivity(), "无效的身份证号码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (sfz.length() == 18) {
                        et_sex.setText(Integer.valueOf(sfz.substring(16, 17)) % 2 == 1 ? "男" : "女");
                        String birthday = sfz.substring(6, 10) + "年" + sfz.substring(10, 12) + "月" + sfz.substring(12, 14) + "日";
                        bt_sr.setText(birthday);
                    } else if (sfz.length() == 15) {
                        et_sex.setText(Integer.valueOf(sfz.substring(16, 17)) % 2 == 1 ? "男" : "女");
                        String birthday = "19" + sfz.substring(6, 8) + "年" + sfz.substring(8, 10) + "月" + sfz.substring(10, 12) + "日";
                        bt_sr.setText(birthday);
                    }
                }
            }
        });

        et_xm1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv_xm.setText(et_xm1.getText());
            }
        });

//        et_hs.setFocusable(false);
//        et_hs.setHintTextColor(0xFFFFFFFF);
//        et_hs.setBackgroundResource(R.drawable.enablefalse);
        et_hs.setHintTextColor(0xFF000000);
        et_ktype.setFocusable(false);
        et_ktype.setText("医院就诊卡");
        et_ktype.setHintTextColor(0xFF000000);
        et_ktype.setBackgroundResource(R.drawable.enablefalse);

        et_ktype.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("医院就诊卡".equals(et_ktype.getText().toString())) {
                    et_hs.setFocusable(true);
                    et_hs.setHintTextColor(0xFF000000);
                    et_hs.setBackgroundResource(R.drawable.edit_focus);
                    et_hs.requestFocus();
                } else {
                    et_hs.setText("");
                    et_hs.setFocusable(false);
                    et_hs.setHintTextColor(0xFFFFFFFF);
                    et_hs.setBackgroundResource(R.drawable.enablefalse);
                    et_ph.requestFocus();
                }
            }
        });

        bt_cx1.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        bt_cx2.setOnClickListener(this);
        bt_tj.setOnClickListener(this);
        bt_sr.setOnClickListener(this);
    }

    private void upCheckViewStatus(Boolean isChecked) {
        if (isChecked) {
            et_xm2.setFocusable(true);
            et_xm2.setHintTextColor(0xFF000000);
            et_xm2.setBackgroundResource(R.drawable.edit_focus);

            et_sex.setFocusable(true);
            et_sex.setHintTextColor(0xFF000000);
            et_sex.setBackgroundResource(R.drawable.edit_focus);
            et_sex.setHint("选择儿童性别");

            bt_sr.setFocusable(true);
            bt_sr.setHintTextColor(0xFF000000);
            bt_sr.setBackgroundResource(R.drawable.edit_focus);
            bt_sr.setHint("选择儿童生日");

            et_sfz.setHint("输入监护人身份证号");
            et_ph.setHint("输入监护人手机号码");
        } else {
            et_xm2.setFocusable(false);
            et_xm2.setHintTextColor(0xFFFFFFFF);
            et_xm2.setBackgroundResource(R.drawable.enablefalse);

            et_sex.setFocusable(false);
            et_sex.setHintTextColor(0xFFFFFFFF);
            et_sex.setBackgroundResource(R.drawable.enablefalse);
            et_sex.setHint("选择性别");

            bt_sr.setFocusable(false);
            bt_sr.setHintTextColor(0xFFFFFFFF);
            bt_sr.setBackgroundResource(R.drawable.enablefalse);
            bt_sr.setHint("出年日期");

            et_sfz.setHint("输入预约人身份证号");
            et_ph.setHint("输入预约人手机号码");
        }

        et_sex.setText("");
        bt_sr.setText("");
    }

    @Override
    public void onStart() {
        super.onStart();
        fl01.setVisibility(View.VISIBLE);
        fl02.setVisibility(View.GONE);
        try {
            ((JzrglAddjzkActivity) getActivity()).tv01.setTextColor(0xFF056B8D);
            ((JzrglAddjzkActivity) getActivity()).tv02.setTextColor(0xFFEEEEEE);
            ((JzrglAddjzkActivity) getActivity()).iv01.setImageResource(R.drawable.num1y);
            ((JzrglAddjzkActivity) getActivity()).iv02.setImageResource(R.drawable.num2n);
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

                hospitalId = et_hs.getTag() == null ? "" : (String) et_hs.getTag();

                if (TextUtils.isEmpty(et_kh.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入卡号", Toast.LENGTH_SHORT).show();
                    et_kh.requestFocus();
                    return;
                }
                fl01.setVisibility(View.GONE);
                fl02.setVisibility(View.VISIBLE);
                try {
                    ((JzrglAddjzkActivity) getActivity()).tv01.setTextColor(0xFFEEEEEE);
                    ((JzrglAddjzkActivity) getActivity()).tv02.setTextColor(0xFF056B8D);
                    ((JzrglAddjzkActivity) getActivity()).iv01.setImageResource(R.drawable.num1n);
                    ((JzrglAddjzkActivity) getActivity()).iv02.setImageResource(R.drawable.num2y);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ck01.requestFocus();
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
                gotoCheckCard();
                break;
        }
    }

    /**
     * 发送网络请求校验就诊卡是否有效
     */
    private void gotoCheckCard() {
        String check = SystemPropertiesProxy.get(getActivity(), SystemProp.SYS_CHECK_CARD, "1");
        if (check.equals("0")) {
            upCard();
        } else {
            progressDialog.setMessage("正在校验就诊卡......");
            progressDialog.show();
            hisHttpHandle.requset(getActivity(),
                    "CheckResult",
                    HisReqXmlText.requestCard,
                    CheckCardRet.class,
                    hisResult,
                    hospitalId,
                    et_kh.getText().toString(),
                    cardType,
                    et_xm1.getText().toString()
            );
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        popleManager.cancle();
        hisHttpHandle.cancle();
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
        String userToken = HealthAppTools.getUserToken(getActivity());
        if (TextUtils.isEmpty(userToken)) return;
        Pople pople = new Pople();
        pople.setUserToken(userToken);
        pople.setName(et_xm1.getText().toString());
        if (et_ktype.getText().toString().equals("医院就诊卡")) {
            pople.setCardType("0");
        } else {
            pople.setCardType("1");
        }
        pople.setHospitalId(et_hs.getTag() == null ? "" : (String) et_hs.getTag());
        pople.setCardNo(et_kh.getText().toString());
        pople.setIsguardian(ck01.isChecked() ? "1" : "0");
        pople.setGuardian(et_xm2.getText().toString());
        pople.setSex(et_sex.getText().toString());
        pople.setDenNo(et_sfz.getText().toString());
        String birthday = bt_sr.getText().toString().replaceAll("年", "-").replace("月", "-").replace("日", "");
        pople.setBirthday(birthday);
        pople.setPhone(et_ph.getText().toString());
        progressDialog.setMessage("正在保存数据......");
        progressDialog.show();
        popleManager.savePople(pople, new PopleManager.Result() {
            @Override
            public void result(boolean flag) {
                progressDialog.dismiss();
                if (flag) {
                    Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                    et_xm1.setText("");
                    et_xm2.setText("");
                    et_ktype.setText("医院就诊卡");
                    et_hs.setText("");
                    et_kh.setText("");
                    tv_xm.setText("");
                    ck01.setChecked(false);
                    et_sex.setText("");
                    bt_sr.setText("");
                    et_sfz.setText("");
                    et_ph.setText("");
                    getActivity().finish();
                }
            }
        });
    }
}
