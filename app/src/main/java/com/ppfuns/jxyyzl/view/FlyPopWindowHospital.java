package com.ppfuns.jxyyzl.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.utils.MyLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author:FlyZebra
 * Time:2017/12/30.10:06
 */

public class FlyPopWindowHospital extends PopupWindow{

    private Context mContext;
    private List<Map<String,String>> list = new ArrayList<>();
    private onItemSelect mItemSelectListener;
    private OnDismissListener mDismissListener;
    private SimpleAdapter simpleAdapter;

    public FlyPopWindowHospital(Context mContext, onItemSelect mListener, OnDismissListener mDismissListener) {
        super();
        this.mContext = mContext;
        this.mItemSelectListener = mListener;
        this.mDismissListener = mDismissListener;
        initView();
    }

    public void setTextList(List<Map<String,String>> textList){
        if(textList!=null){
            list.clear();
            list.addAll(textList);
            if(simpleAdapter!=null){
                simpleAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initView() {
        ListViewTv listView = new ListViewTv(mContext);
        listView.setBackgroundColor(0xffffffff);
        simpleAdapter = new SimpleAdapter(mContext,
                list,
                R.layout.item_list_hostpital,
                new String[]{"hosName"},
                new int[]{R.id.text1});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mItemSelectListener.onItemSelect(view,list.get((int) id));
                dismiss();
            }
        });
        setContentView(listView);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        update();
        setOnDismissListener(mDismissListener);
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

    public interface onItemSelect {
        void onItemSelect(View view, Map<String, String> map);
    }
}