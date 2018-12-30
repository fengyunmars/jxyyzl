package com.ppfuns.jxyyzl.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ppfuns.jxyyzl.utils.MyLog;

/**
 * Author:Administrator
 * Time:2017/12/30.13:14
 */

@SuppressLint("AppCompatCustomView")
public class PopEditTextCard extends EditText implements View.OnClickListener, PopupWindow.OnDismissListener {
    private FlyPopWindowCard flyPopWindow;

    public PopEditTextCard(Context context) {
        this(context, null);
    }

    public PopEditTextCard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PopEditTextCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setInputType(InputType.TYPE_NULL);
        flyPopWindow = new FlyPopWindowCard(context, this, this);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            setText(((TextView) v).getText());
            setSelection(((TextView) v).getText().length());
        }
    }

    @Override
    public void onDismiss() {
        MyLog.d("popupWindow dismiss");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MyLog.d("KeyCode=%d", keyCode);
        switch (keyCode) {
            case KeyEvent.KEYCODE_0:
            case KeyEvent.KEYCODE_1:
            case KeyEvent.KEYCODE_2:
            case KeyEvent.KEYCODE_3:
            case KeyEvent.KEYCODE_4:
            case KeyEvent.KEYCODE_5:
            case KeyEvent.KEYCODE_6:
            case KeyEvent.KEYCODE_7:
            case KeyEvent.KEYCODE_8:
            case KeyEvent.KEYCODE_9:
            case KeyEvent.KEYCODE_A:
            case KeyEvent.KEYCODE_B:
            case KeyEvent.KEYCODE_C:
            case KeyEvent.KEYCODE_D:
            case KeyEvent.KEYCODE_E:
            case KeyEvent.KEYCODE_F:
            case KeyEvent.KEYCODE_G:
            case KeyEvent.KEYCODE_H:
            case KeyEvent.KEYCODE_I:
            case KeyEvent.KEYCODE_J:
            case KeyEvent.KEYCODE_K:
            case KeyEvent.KEYCODE_L:
            case KeyEvent.KEYCODE_M:
            case KeyEvent.KEYCODE_N:
            case KeyEvent.KEYCODE_O:
            case KeyEvent.KEYCODE_P:
            case KeyEvent.KEYCODE_Q:
            case KeyEvent.KEYCODE_R:
            case KeyEvent.KEYCODE_S:
            case KeyEvent.KEYCODE_T:
            case KeyEvent.KEYCODE_U:
            case KeyEvent.KEYCODE_V:
            case KeyEvent.KEYCODE_W:
            case KeyEvent.KEYCODE_X:
            case KeyEvent.KEYCODE_Y:
            case KeyEvent.KEYCODE_Z:
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_SPACE:
                flyPopWindow.showSpPop(this);
                return true;
            default:
                return false;
        }
    }
}
