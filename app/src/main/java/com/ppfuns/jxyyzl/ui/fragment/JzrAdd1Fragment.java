package com.ppfuns.jxyyzl.ui.fragment;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.Pople;
import com.ppfuns.jxyyzl.module.HealthAppTools;
import com.ppfuns.jxyyzl.module.PopleManager;
import com.ppfuns.jxyyzl.utils.IDCard;

/**
 * Author:Administrator
 * Time:2017/12/28.1:42
 */

public class JzrAdd1Fragment extends Fragment implements View.OnClickListener {
    private TextView tv_sfz;
    private EditText et_xm, et_sfz, et_ph, et_sex;
    private Button bt_cx, bt_qd;
    private ProgressDialog progressDialog;
    private PopleManager popleManager;
    private String birthday = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jzr1add, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity());
        popleManager = new PopleManager(getActivity());
        tv_sfz = view.findViewById(R.id.tv_sfz);
        et_xm = view.findViewById(R.id.et_xm);
        et_sex = view.findViewById(R.id.et_sex);
        et_sfz = view.findViewById(R.id.et_sfz);
        et_ph = view.findViewById(R.id.et_ph);
        bt_cx = view.findViewById(R.id.bt_cx);
        bt_qd = view.findViewById(R.id.bt_tj);

        bt_cx.setOnClickListener(this);
        bt_qd.setOnClickListener(this);

        et_ph.setKeyListener(new NumberKeyListener() {
            @Override
            public int getInputType() {
                return android.text.InputType.TYPE_CLASS_PHONE;
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
                return new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'X', 'x'};
            }
        });
        et_sfz.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String sfz = et_sfz.getText().toString();
                    if (!IDCard.IDCardValidate(sfz)) {
                        Toast.makeText(getActivity(), "无效的身份证号码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (sfz.length() == 18) {
//                        int i = Integer.valueOf(sfz.substring(16, 17));
//                        et_sex.setText(Integer.valueOf(sfz.substring(16, 17)) % 2 == 1 ? "男" : "女");
                        birthday = sfz.substring(6, 10) + "年" + sfz.substring(10, 12) + "月" + sfz.substring(12, 14) + "日";
                        tv_sfz.setText("出生日期：" + birthday);
                    } else if (sfz.length() == 15) {
//                        int i = Integer.valueOf(sfz.substring(14, 15));
//                        et_sex.setText(i % 2 == 1 ? "男" : "女");
                        String lstr = sfz.substring(0, 5) + "19" + sfz.substring(6, 14) + "X";
                        birthday = "19" + sfz.substring(6, 10) + "年" + sfz.substring(10, 12) + "月" + sfz.substring(12, 14) + "日";
                        tv_sfz.setText("出生日期：" + birthday);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cx:
                getActivity().finish();
                break;
            case R.id.bt_tj:
                String name = et_xm.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getActivity(), "请输入姓名", Toast.LENGTH_SHORT).show();
                    et_xm.requestFocus();
                    return;
                }

                String sex = et_sex.getText().toString();
                if (TextUtils.isEmpty(sex)) {
                    Toast.makeText(getActivity(), "请选择性别", Toast.LENGTH_SHORT).show();
                    et_sex.requestFocus();
                    return;
                }

                String sfz = et_sfz.getText().toString();
                if (!IDCard.IDCardValidate(sfz)) {
                    Toast.makeText(getActivity(), "请输入正确的身份证号码", Toast.LENGTH_SHORT).show();
                    et_sfz.requestFocus();
                    return;
                }
                String ph = et_ph.getText().toString();
                if (ph.length() != 11) {
                    Toast.makeText(getActivity(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    et_ph.requestFocus();
                    return;
                }
                String userToken = HealthAppTools.getUserToken(getActivity());
                if (TextUtils.isEmpty(userToken)) return;
                Pople pople = new Pople();
                pople.setUserToken(userToken);
                pople.setName(et_xm.getText().toString());
                pople.setSex(et_sex.getText().toString());
                pople.setDenNo(et_sfz.getText().toString());
                pople.setPhone(et_ph.getText().toString());
                pople.setBirthday(birthday.replaceAll("年","-").replace("月","-").replace("日",""));
                progressDialog.setMessage("正在保存数据......");
                progressDialog.show();
                popleManager.savePople(pople, new PopleManager.Result() {
                    @Override
                    public void result(boolean flag) {
                        progressDialog.dismiss();
                        if (flag) {
                            Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                            tv_sfz.setText("出生日期:（系统自动识别）");
                            et_sfz.setText("");
                            et_sex.setText("");
                            et_xm.setText("");
                            et_ph.setText("");
                            getActivity().finish();
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        popleManager.cancle();
    }
}
