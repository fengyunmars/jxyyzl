package com.ppfuns.jxyyzl.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.utils.MyLog;

/**
 * Author:FlyZebra
 * Time:2017/12/30.10:06
 */

public class FlyPopWindowSex extends PopupWindow implements View.OnClickListener {

    private Context mContext;

    private TextView tv01;
    private TextView tv02;
    private View.OnClickListener mListener;
    private OnDismissListener mDismissListener;


    public FlyPopWindowSex(Context mContext, View.OnClickListener mListener, OnDismissListener mDismissListener) {
        super();
        this.mContext = mContext;
        this.mListener = mListener;
        this.mDismissListener = mDismissListener;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_sp_pop, null);
        setContentView(view);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        update();
        setOnDismissListener(mDismissListener);
        tv01 = view.findViewById(R.id.pop_tv01);
        tv02 = view.findViewById(R.id.pop_tv02);
        setViewOnClick(tv01, tv02);
    }

    private void setViewOnClick(View... views) {
        if (views != null) {
            for (View view : views) {
                view.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_tv01:
            case R.id.pop_tv02:
                mListener.onClick(v);
                MyLog.d("popmenu click");
                dismiss();
                break;
            default:
                dismiss();
                break;
        }
    }

    public void showSpPop(View parent) {
        MyLog.d("show popwindow");
        if (isShowing()) {
            MyLog.d("isShowing is ture so dismiss");
            dismiss();
        }
        setWidth(parent.getWidth());
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        showAsDropDown(parent, 0, 0);
    }

    @Override
    public boolean isShowing() {
        return super.isShowing();
    }
}