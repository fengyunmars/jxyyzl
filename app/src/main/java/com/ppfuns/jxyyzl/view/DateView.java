package com.ppfuns.jxyyzl.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 湖南项目主界面右上角显示日期控件
 * Created by flyzebra on 17-4-19.
 */
@SuppressLint("AppCompatCustomView")
public class DateView extends TextView {
    private final String TIME_FORMAT = "HH:mm\nyyyy-MM-dd";
    private String[] weeks = new String[7];
    private String time = "00:00\n2017.01.01";
    private String week = "星期日";
    private Handler mHander = new Handler(Looper.getMainLooper());
    private long mDelayMillis = 60 * 1000;
    private Runnable task = new Runnable(){
        @Override
        public void run() {
            upView();
        }};

    public DateView(Context context) {
        this(context,null);
    }

    public DateView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        weeks = new String[]{ context.getString(R.string.tv_str_sunday),
                context.getString(R.string.tv_str_monday),
                context.getString(R.string.tv_str_tuesday),
                context.getString(R.string.tv_str_wednesday),
                context.getString(R.string.tv_str_thursday),
                context.getString(R.string.tv_str_friday),
                context.getString(R.string.tv_str_saturday)};
    }

    @Override
    protected void onAttachedToWindow() {
        upView();
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        mHander.removeCallbacks(task);
        super.onDetachedFromWindow();
    }

    private void upView() {
        String newTime = DateUtils.getCurrentDateString(TIME_FORMAT);
        String newWeek = getCurrentWeek();

        if (!(newTime.equals(time) && newWeek.equals(week))) {
            time = newTime;
            week = newWeek;
            String text = time + " " + week;
            setText(text);
        }
        mHander.removeCallbacks(task);
        mHander.postDelayed(task,mDelayMillis);
    }

    private String getCurrentWeek() {
        Date date = new Date(System.currentTimeMillis());
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
        return weeks[mCalendar.get(Calendar.DAY_OF_WEEK)-1];
    }
}
