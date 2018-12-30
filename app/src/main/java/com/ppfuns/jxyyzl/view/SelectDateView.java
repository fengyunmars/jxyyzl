package com.ppfuns.jxyyzl.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.ppfuns.jxyyzl.utils.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Author:Administrator
 * Time:2017/12/31.10:52
 */

public class SelectDateView extends LinearLayout implements NumberPicker.Formatter, NumberPicker.OnValueChangeListener {
    private NumberPicker yearView, monthView, dayView;
    private int year = 2010;
    private int month = 1;
    private int day = 1;

    public SelectDateView(Context context) {
        this(context, null);
    }

    public SelectDateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectDateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        yearView = new NumberPicker(context);
        yearView.setTag(0);
        yearView.setMinValue(1998);
        yearView.setMaxValue(Integer.valueOf(DateUtils.getCurrentDateString("yyyy")));
        yearView.setValue(2010);
        yearView.setOnValueChangedListener(this);
        addView(yearView);
        monthView = new NumberPicker(context);
        monthView.setTag(1);
        monthView.setMinValue(1);
        monthView.setMaxValue(12);
        monthView.setValue(1);
        monthView.setFormatter(this);
        monthView.setOnValueChangedListener(this);
        addView(monthView);
        dayView = new NumberPicker(context);
        dayView.setTag(2);
        dayView.setMinValue(1);
        dayView.setMaxValue(31);
        dayView.setValue(1);
        dayView.setFormatter(this);
        dayView.setOnValueChangedListener(this);
        addView(dayView);
    }

    @Override
    public String format(int value) {
        String tmpStr = String.valueOf(value);
        if (value < 10) {
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        int tag = (int) picker.getTag();
        switch (tag) {
            case 0:
                year = newVal;
                setMaxDay();
                break;
            case 1:
                month = newVal;
                setMaxDay();
                break;
            case 2:
                day = newVal;
                break;
        }
    }

    private void setMaxDay() {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        dayView.setMaxValue(dayOfMonth);
    }

    public String getStringDate() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        c.set(year, month - 1, day);
        return DateUtils.date2string(c.getTime(), "yyyy年MM月dd日");
    }

    public void setStringDate(String stringDate) {
        try {
            if (TextUtils.isEmpty(stringDate)) return;
            Date date = DateUtils.string2date(stringDate, "yyyy年MM月dd日");
            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            c.setTime(date);
            year = c.get(Calendar.YEAR);//获取年份
            month = c.get(Calendar.MONTH) + 1;//获取月份
            day = c.get(Calendar.DATE);//获取日
            yearView.setValue(year);
            monthView.setValue(month);
            dayView.setValue(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
