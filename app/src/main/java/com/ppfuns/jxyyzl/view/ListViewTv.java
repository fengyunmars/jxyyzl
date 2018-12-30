package com.ppfuns.jxyyzl.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ListView;


/**
 * Created by Administrator on 2016/7/3.
 */
public class ListViewTv extends ListView {
    private DataChangedListener dataChangedListener;

    public ListViewTv(Context context) {
        this(context, null);
    }

    public ListViewTv(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListViewTv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        int lastSelectItem = getSelectedItemPosition();
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) {
            setSelectionFromTop(lastSelectItem, 190);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean flag = super.onKeyDown(keyCode, event);
        int lastSelectItem = getSelectedItemPosition();
        setSelectionFromTop(lastSelectItem, 190);
        return flag;
    }

    @Override
    protected void handleDataChanged() {
        super.handleDataChanged();
        if(dataChangedListener!=null){
            dataChangedListener.onSuccess();
        }
    }

    public void setDataChangedListener(DataChangedListener dataChangedListener) {
        this.dataChangedListener = dataChangedListener;
    }

    public interface DataChangedListener{
        void onSuccess();
    }
}
