package com.ppfuns.jxyyzl.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ppfuns.jxyyzl.R;

/**
 * Author: Fly
 * Time: 2017/12/12 上午10:56.
 * Discription: This is ${NAME}
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private View yygh;
    private View yyjl;
    private View cjwt;
    private View jzrgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yygh = findViewById(R.id.main_yygh);
        yygh.setOnClickListener(this);
        yyjl = findViewById(R.id.main_yyjl);
        yyjl.setOnClickListener(this);
        cjwt = findViewById(R.id.main_cjwt);
        cjwt.setOnClickListener(this);
        jzrgl = findViewById(R.id.main_jzrgl);
        jzrgl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_yygh:
                startActivity(SelectHospitalActivity.class);
                break;
            case R.id.main_yyjl:
                startActivity(AppointInfoActivity.class);
                break;
            case R.id.main_jzrgl:
                startActivity(JzrglManagerActivity.class);
                break;
            case R.id.main_cjwt:
                startActivity(CjwtActivity.class);
                break;
            default:
                break;
        }
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}
