package com.ppfuns.jxyyzl.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.ppfuns.jxyyzl.constant.HospitalList;
import com.ppfuns.jxyyzl.constant.HttpApi;
import com.ppfuns.jxyyzl.data.RetMsg;
import com.ppfuns.jxyyzl.data.SingleHosPital;
import com.ppfuns.jxyyzl.http.IHttp;
import com.ppfuns.jxyyzl.http.MyHttp;
import com.ppfuns.jxyyzl.utils.GsonUtils;
import com.ppfuns.jxyyzl.utils.MyLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:Administrator
 * Time:2017/12/30.13:14
 */

@SuppressLint("AppCompatCustomView")
public class PopEditHostpital extends EditText implements FlyPopWindowHospital.onItemSelect,PopupWindow.OnDismissListener,IHttp.HttpResult{
    private FlyPopWindowHospital flyPopWindow;
    private IHttp iHttp = MyHttp.getInstance();
    private String TAG = "PopEditHostpital";
    public PopEditHostpital(Context context) {
        this(context, null);
    }

    public PopEditHostpital(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PopEditHostpital(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setInputType(InputType.TYPE_NULL);
        flyPopWindow = new FlyPopWindowHospital(context, this, this);
        List<Map<String, String>> list = new ArrayList<>();
        for (SingleHosPital singelHosPital : HospitalList.getHospitalList()) {
            Map<String,String> map = new HashMap<>();
            map.put("hospitalId",singelHosPital.getHospitalId());
            map.put("hosName",singelHosPital.getHosName());
            list.add(map);
        }
        flyPopWindow.setTextList(list);
    }

    @Override
    public void onItemSelect(View view, Map<String, String> map) {
        setText(map.get("hosName"));
        setTag(map.get("hospitalId"));
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

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        iHttp.getString(HttpApi.getJxresUrl(getContext()) + "/Api/hospital", TAG, this);
    }

    @Override
    protected void onDetachedFromWindow() {
        iHttp.cancelAll(TAG);
        super.onDetachedFromWindow();
    }

    @Override
    public void succeed(Object object) {
        String msg = object.toString();
        RetMsg retMsg = GsonUtils.json2Object(msg, RetMsg.class);
        if (retMsg != null && retMsg.getRet() == 0) {
            if (retMsg.getData() != null) {
                List<SingleHosPital> retList = GsonUtils.json2ListObj(GsonUtils.object2Json(retMsg.getData()), SingleHosPital.class);
                if(retList!=null) {
                    MyLog.d(retList.toString());
                    List<Map<String, String>> list = new ArrayList<>();
                    for (SingleHosPital singelHosPital : retList) {
                        Map<String, String> map = new HashMap<>();
                        map.put("hospitalId", singelHosPital.getHospitalId());
                        map.put("hosName", singelHosPital.getHosName());
                        list.add(map);
                    }
                    flyPopWindow.setTextList(list);
                }
            }
        }
    }

    @Override
    public void failed(Object object) {

    }
}
