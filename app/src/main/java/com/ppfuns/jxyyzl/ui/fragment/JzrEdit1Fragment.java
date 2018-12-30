package com.ppfuns.jxyyzl.ui.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
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
import com.ppfuns.jxyyzl.module.PopleManager;
import com.ppfuns.jxyyzl.ui.JzrglEditActivity;

/**
 * Author:Administrator
 * Time:2017/12/28.1:42
 */

public class JzrEdit1Fragment extends Fragment implements View.OnClickListener {
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
            tv_sfz.setText("出生日期：" +pople.getBirthday());
            tv_sfz.setBackgroundResource(R.drawable.enablefalse);
            et_xm.setText(pople.getName());
            et_xm.setFocusable(false);
            et_xm.setBackgroundResource(R.drawable.enablefalse);
            et_sex.setText(pople.getSex());
            et_sex.setFocusable(false);
            et_sex.setBackgroundResource(R.drawable.enablefalse);
            et_sfz.setText(pople.getDenNo());
            et_sfz.setFocusable(false);
            et_sfz.setBackgroundResource(R.drawable.enablefalse);
            et_ph.setText(pople.getPhone());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cx:
                getActivity().finish();
                break;
            case R.id.bt_tj:
                if (et_ph.getText().toString().length() != 11) {
                    Toast.makeText(getActivity(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    et_ph.requestFocus();
                    return;
                }
                Pople pople = ((JzrglEditActivity) getActivity()).pople;
                if(pople==null) return;
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
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        popleManager.cancle();
    }
}
