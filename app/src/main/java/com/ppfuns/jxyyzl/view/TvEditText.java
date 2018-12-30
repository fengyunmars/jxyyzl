package com.ppfuns.jxyyzl.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

import com.ppfuns.jxyyzl.utils.KeyUtil;
import com.ppfuns.jxyyzl.utils.MyLog;

/**
 * Author:Administrator
 * Time:2018/1/2.12:02
 */

@SuppressLint("AppCompatCustomView")
public class TvEditText extends EditText {
    public TvEditText(Context context) {
        this(context,null);
    }

    public TvEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TvEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setSingleLine(true);
        setHintTextColor(0xFF000000);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MyLog.d("event %d----%s", keyCode, event.toString());
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            String text = getText().toString();
            if (text.length() > 0) {
//                String newText = text.substring(0, text.length() - 1);
//                setText(newText);
//                setSelection(newText.length());
                if(getSelectionStart()!=0){
                    KeyUtil.SendKey(KeyEvent.KEYCODE_DEL);
                }else{
//                    KeyUtil.SendKey(KeyEvent.KEYCODE_FORWARD_DEL);
                    return super.onKeyDown(keyCode, event);
                }
                return true;
            }else{
                return super.onKeyDown(keyCode,event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
