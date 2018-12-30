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
import android.widget.EditText;
import android.widget.Toast;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.Pople;
import com.ppfuns.jxyyzl.module.PopleManager;
import com.ppfuns.jxyyzl.ui.JzrglEditActivity;
import com.ppfuns.jxyyzl.view.SelectDateView;

/**
 * Author:Administrator
 * Time:2017/12/28.1:42
 */

public class JzrEdit2Fragment extends Fragment implements View.OnClickListener {
    private Button bt_sr, bt_cx, bt_tj;
    private EditText et_xm1, et_xm2, et_sex, et_sfz, et_ph;
    private ProgressDialog progressDialog;
    private PopleManager popleManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jzr2add, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity());
        popleManager = new PopleManager(getActivity());
        bt_sr = view.findViewById(R.id.bt_sr);
        bt_cx = view.findViewById(R.id.bt_cx);
        bt_tj = view.findViewById(R.id.bt_tj);
        et_xm1 = view.findViewById(R.id.et_xm1);
        et_xm2 = view.findViewById(R.id.et_xm2);
        et_sex = view.findViewById(R.id.et_sex);
        et_sfz = view.findViewById(R.id.et_sfz);
        et_ph = view.findViewById(R.id.et_ph);
        bt_sr.setOnClickListener(this);
        bt_cx.setOnClickListener(this);
        bt_tj.setOnClickListener(this);

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

        Pople pople = ((JzrglEditActivity) getActivity()).pople;
        if (pople != null) {
            et_xm1.setText(pople.getName());
            et_xm1.setFocusable(false);
            et_xm1.setBackgroundResource(R.drawable.enablefalse);
            et_xm2.setText(pople.getGuardian());
            et_sex.setText(pople.getSex());
            et_sex.setFocusable(false);
            et_sex.setBackgroundResource(R.drawable.enablefalse);
            et_sfz.setText(pople.getDenNo());
            et_sfz.setFocusable(false);
            et_sfz.setBackgroundResource(R.drawable.enablefalse);
            bt_sr.setText(pople.getBirthday());
            et_ph.setText(pople.getPhone());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_sr:
                selectBirthday();
                break;
            case R.id.bt_cx:
                getActivity().finish();
                break;
            case R.id.bt_tj:

                if (TextUtils.isEmpty(et_xm2.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入监护人姓名", Toast.LENGTH_SHORT).show();
                    et_xm2.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(bt_sr.getText().toString())) {
                    Toast.makeText(getActivity(), "请选择儿童生日", Toast.LENGTH_SHORT).show();
                    bt_sr.requestFocus();
                    selectBirthday();
                    return;
                }

                if (et_ph.getText().toString().length() != 11) {
                    Toast.makeText(getActivity(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    et_ph.requestFocus();
                    return;
                }
                Pople pople = ((JzrglEditActivity) getActivity()).pople;
                if(pople==null) return;
                pople.setPhone(et_ph.getText().toString());
                String birthday = bt_sr.getText().toString().replaceAll("年","-").replace("月","-").replace("日","");
                pople.setBirthday(birthday);
                pople.setGuardian(et_xm2.getText().toString());
                progressDialog.setMessage("正在保存数据");
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
                break;
        }
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

    @Override
    public void onStop() {
        super.onStop();
        popleManager.cancle();

    }
}
