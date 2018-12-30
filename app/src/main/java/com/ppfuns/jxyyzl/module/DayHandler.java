package com.ppfuns.jxyyzl.module;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.ppfuns.jxyyzl.constant.HisReqXmlText;
import com.ppfuns.jxyyzl.constant.HttpApi;
import com.ppfuns.jxyyzl.data.DayInfo;
import com.ppfuns.jxyyzl.data.Dept;
import com.ppfuns.jxyyzl.data.Doctor;
import com.ppfuns.jxyyzl.data.DoctorSchedule;
import com.ppfuns.jxyyzl.data.Schedule;
import com.ppfuns.jxyyzl.data.Token;
import com.ppfuns.jxyyzl.http.IHttp;
import com.ppfuns.jxyyzl.http.MyHttp;
import com.ppfuns.jxyyzl.utils.DateUtils;
import com.ppfuns.jxyyzl.utils.MyLog;
import com.ppfuns.jxyyzl.utils.XmlHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Author:Administrator
 * Time:2017/12/20.16:31
 */

public class DayHandler {
    private static final String HTTPTAG = "DoctorHandler";
    private final static ExecutorService executors = Executors.newFixedThreadPool(1);
    private DoctorResult result;
    private String[] weeks = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六",};
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private IHttp iHttp = MyHttp.getInstance();
    private Dept currentDept;
    private AtomicBoolean isCancle = new AtomicBoolean(false);

    private DayHandler() {
    }

    public static DayHandler getInstance() {
        return DoctorHandlerHolder.sInstance;
    }

    public void request(final Context context, final String hospitalId, final String hosName, final Dept dept, final int sum, final DoctorResult DoctorResult) {
        this.currentDept = dept;
        isCancle.set(false);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                final List<DayInfo> dayInfoList = new ArrayList<>();
                long time = System.currentTimeMillis() + 1000 * 60 * 60 * 24;
                Token token = TokenHandler.getInstance().getToken(context);
                if (token == null) {
                    result.faile(HisError.GetTokeyFailed);
                    return;
                }
                String rs = HisReqXmlText.requstDoctor.replace("<K></K>", "<K>" + token.getToken() + "</K>");
                String postStr = String.format(rs, hospitalId, dept.getDeptId());
                Map<String, String> map = new HashMap<>();
                map.put("reqXml", postStr);
                String str = iHttp.postStrAndResp(HttpApi.getHisApiUrl(context), map, HTTPTAG);
                if (!dept.equals(currentDept) || isCancle.get()) {
                    return;
                }
                str = str.replaceAll("&lt;", "<");
                str = str.replaceAll("&gt;", ">");
                final List<Doctor> doctorList = XmlHelper.getInstance().getList(Doctor.class, str, "DoctorInfo");

                for (int i = 0; i < sum; i++) {
                    if (!dept.equals(currentDept) || isCancle.get()) {
                        return;
                    }
                    DayInfo dayInfo = new DayInfo();
                    String requestDay = DateUtils.time2string(time + i * 1000 * 60 * 60 * 24, "yyyy-MM-dd");
                    String rs1 = HisReqXmlText.requestSchedule.replace(">null<", "><");
                    rs1 = rs1.replace("<K></K>", "<K>" + token.getToken() + "</K>");
                    String postStr1 = String.format(rs1, hospitalId, dept.getDeptId(), requestDay);
                    Map<String, String> map1 = new HashMap<>();
                    map1.put("reqXml", postStr1);
                    String str1 = iHttp.postStrAndResp(HttpApi.getHisApiUrl(context), map1, HTTPTAG);
                    if (!dept.equals(currentDept) || isCancle.get()) {
                        return;
                    }
                    str1 = str1.replaceAll("&lt;", "<");
                    str1 = str1.replaceAll("&gt;", ">");
                    List<Schedule> scheduleList = XmlHelper.getInstance().getList(Schedule.class, str1, "scheduleInfo");
                    MyLog.d("scheduleList=" + scheduleList);
                    List<DoctorSchedule> doctorScheduleList = new ArrayList<>();
                    if (scheduleList != null) {
                        for (int j = 0; j < scheduleList.size(); j++) {
                            DoctorSchedule doctorSchedule = new DoctorSchedule();
                            doctorSchedule.setHospitalId(hospitalId);
                            doctorSchedule.setDeptId(dept.getDeptId());
                            doctorSchedule.setDeptName(dept.getDeptName());
                            doctorSchedule.setDoctorId(scheduleList.get(j).getDoctorId());
                            Doctor doctor = null;
                            for (Doctor dc : doctorList) {
                                if (dc.getDoctorId().equals(doctorSchedule.getDoctorId())) {
                                    doctor = dc;
                                }
                            }
                            if (doctor != null) {
                                doctorSchedule.setDoctorName(doctor.getDoctorName());
                                doctorSchedule.setTitle(doctor.getTitle());
                            }
                            doctorSchedule.setHosName(hosName);
                            doctorSchedule.setRegType(scheduleList.get(j).getRegType());
                            doctorSchedule.setScheduleId(scheduleList.get(j).getScheduleId());
                            doctorSchedule.setRegFee(scheduleList.get(j).getRegFee());
                            doctorScheduleList.add(doctorSchedule);
                        }
                    }
                    dayInfo.setDoctorScheduleList(doctorScheduleList);
                    dayInfo.setStrDay(DateUtils.time2string(time + i * 1000 * 60 * 60 * 24, "MM.dd"));
                    dayInfo.setRequstDay(requestDay);
                    dayInfo.setWeek(getWeek(time + i * 1000 * 60 * 60 * 24));
                    dayInfoList.add(dayInfo);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (dept.equals(currentDept) || isCancle.get()) {
                                DoctorResult.result(dayInfoList);
                            }
                        }
                    });
                }
            }
        });
    }

    private String getWeek(long time) {
        Date date = new Date(time);
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
        return weeks[mCalendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public void cancle() {
        mHandler.removeCallbacksAndMessages(null);
        isCancle.set(true);
        iHttp.cancelAll(HTTPTAG);
    }

    public interface DoctorResult {
        void result(List<DayInfo> list);

        void faile(int error);
    }

    private static class DoctorHandlerHolder {
        static final DayHandler sInstance = new DayHandler();
    }

}
