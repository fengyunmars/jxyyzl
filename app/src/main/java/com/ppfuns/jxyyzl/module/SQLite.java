package com.ppfuns.jxyyzl.module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ppfuns.jxyyzl.data.OrderInfo;
import com.ppfuns.jxyyzl.data.Pople;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {
    private static final String DB_NAME = "jxyyzl.db";
    private static final String TABLE_POPLE = "pople";
    private static final String CREATE_POPLE = "create table pople(" +
            "id integer primary key," +
            "userToken varchar(64)," +
            "cardNo varchar(64) default ''," +
            "cardType varchar(2) default ''," +
            "name varchar(64)," +
            "sex varchar(2)," +
            "denNo varchar(18)," +
            "guardian varchar(64) default ''," +
            "birthday varchar(64)," +
            "phone varchar(11)," +
            "defult varchar(1) default '0'," +
            "upstate varchar(1) default '0'," +
            "hospitalId varchar(64) default '0'," +
            "isguardian varchar(1) default '0');";
    private static final String TABLE_ORDERINFO = "orderinfo";
    private static final String CREATE_ORDERINFO = "create table orderinfo(" +
            "hisOrderId varchar(64) primary key," +
            "userToken varchar(64)," +
            "phone varchar(11)," +
            "hospitalId varchar(64)," +
            "hosName varchar(64)," +
            "deptId varchar(64)," +
            "deptName varchar(64)," +
            "regDate varchar(20)," +
            "createTime varchar(20)," +
            "name varchar(64)," +
            "sex varchar(2)," +
            "startendTime varchar(40)," +
            "upstate varchar(1) default '0'," +
            "denNo varchar(18));";
    private SQLiteDatabase db;

    public SQLite(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_POPLE);
        db.execSQL(CREATE_ORDERINFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean insertPople(Pople pople) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("userToken", pople.getUserToken());
        value.put("cardNo", pople.getCardNo());
        value.put("cardType", pople.getCardType());
        value.put("name", pople.getName());
        value.put("sex", pople.getSex());
        value.put("denNo", pople.getDenNo());
        value.put("guardian", pople.getGuardian());
        value.put("birthday", pople.getBirthday());
        value.put("phone", pople.getPhone());
        value.put("defult", pople.getDefult()==null?"0":pople.getDefult());
        value.put("upstate", pople.getUpstate()==null?"0":pople.getUpstate());
        value.put("isguardian", pople.getIsguardian()==null?"0":pople.getIsguardian());
        value.put("hospitalId", pople.getHospitalId()==null?"0":pople.getHospitalId());
        db.insert(TABLE_POPLE, null, value);
        db.close();
        return true;
    }

    public boolean insertOrderInfo(OrderInfo orderInfo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("hisOrderId", orderInfo.getHisOrderId());
        value.put("userToken", orderInfo.getUserToken());
        value.put("phone", orderInfo.getPhone());
        value.put("hospitalId", orderInfo.getHospitalId());
        value.put("hosName", orderInfo.getHosName());
        value.put("deptId", orderInfo.getDeptId());
        value.put("deptName", orderInfo.getDeptName());
        value.put("regDate", orderInfo.getRegDate());
        value.put("createTime", orderInfo.getCreateTime());
        value.put("name", orderInfo.getName());
        value.put("sex", orderInfo.getSex());
        value.put("startendTime", orderInfo.getStartendTime());
        value.put("upstate", orderInfo.getUpstate()==null?"0":orderInfo.getUpstate());
        value.put("denNo", orderInfo.getDenNo());
        db.insert(TABLE_ORDERINFO, null, value);
        db.close();
        return true;
    }

    public List<Pople> getAllPople(String userToken) {
        List<Pople> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from pople where userToken=?";
        Cursor c = db.rawQuery(sql, new String[]{userToken});
        while (c.moveToNext()) {
            Pople pople = new Pople();
            pople.setId(c.getInt(c.getColumnIndex("id")));
            pople.setUserToken(c.getString(c.getColumnIndex("userToken")));
            pople.setCardNo(c.getString(c.getColumnIndex("cardNo")));
            pople.setCardType(c.getString(c.getColumnIndex("cardType")));
            pople.setName(c.getString(c.getColumnIndex("name")));
            pople.setSex(c.getString(c.getColumnIndex("sex")));
            pople.setDenNo(c.getString(c.getColumnIndex("denNo")));
            pople.setGuardian(c.getString(c.getColumnIndex("guardian")));
            pople.setBirthday(c.getString(c.getColumnIndex("birthday")));
            pople.setPhone(c.getString(c.getColumnIndex("phone")));
            pople.setDefult(c.getString(c.getColumnIndex("defult")));
            pople.setUpstate(c.getString(c.getColumnIndex("upstate")));
            pople.setIsguardian(c.getString(c.getColumnIndex("isguardian")));
            pople.setHospitalId(c.getString(c.getColumnIndex("hospitalId")));
            list.add(pople);
        }
        c.close();
        db.close();
        return list;
    }


    public Pople getDefultPople(String userToken) {
        SQLiteDatabase db = getWritableDatabase();
        Pople pople = null;
        String sql = "select * from pople where userToken=? and defult=1";
        Cursor c = db.rawQuery(sql, new String[]{userToken});
        if (c.moveToNext()) {
            pople = new Pople();
            pople.setUserToken(c.getString(c.getColumnIndex("userToken")));
            pople.setCardNo(c.getString(c.getColumnIndex("cardNo")));
            pople.setCardType(c.getString(c.getColumnIndex("cardType")));
            pople.setName(c.getString(c.getColumnIndex("name")));
            pople.setSex(c.getString(c.getColumnIndex("sex")));
            pople.setDenNo(c.getString(c.getColumnIndex("denNo")));
            pople.setGuardian(c.getString(c.getColumnIndex("guardian")));
            pople.setBirthday(c.getString(c.getColumnIndex("birthday")));
            pople.setPhone(c.getString(c.getColumnIndex("phone")));
            pople.setDefult(c.getString(c.getColumnIndex("defult")));
            pople.setUpstate(c.getString(c.getColumnIndex("upstate")));
            pople.setIsguardian(c.getString(c.getColumnIndex("isguardian")));
            pople.setHospitalId(c.getString(c.getColumnIndex("hospitalId")));
        }
        c.close();
        db.close();
        return pople;
    }


    public List<OrderInfo> getAllOrderinfo(String userToken) {
        List<OrderInfo> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(TABLE_ORDERINFO, null, "userToken=?", new String[]{userToken}, null,
                null, null);
        while (c.moveToNext()) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setHisOrderId(c.getString(c.getColumnIndex("hisOrderId")));
            orderInfo.setUserToken(c.getString(c.getColumnIndex("userToken")));
            orderInfo.setPhone(c.getString(c.getColumnIndex("phone")));
            orderInfo.setHospitalId(c.getString(c.getColumnIndex("hospitalId")));
            orderInfo.setHosName(c.getString(c.getColumnIndex("hosName")));
            orderInfo.setDeptId(c.getString(c.getColumnIndex("deptId")));
            orderInfo.setDeptName(c.getString(c.getColumnIndex("deptName")));
            orderInfo.setRegDate(c.getString(c.getColumnIndex("regDate")));
            orderInfo.setCreateTime(c.getString(c.getColumnIndex("createTime")));
            orderInfo.setName(c.getString(c.getColumnIndex("name")));
            orderInfo.setSex(c.getString(c.getColumnIndex("sex")));
            orderInfo.setStartendTime(c.getString(c.getColumnIndex("startendTime")));
            orderInfo.setUpstate(c.getString(c.getColumnIndex("upstate")));
            orderInfo.setDenNo(c.getString(c.getColumnIndex("denNo")));
            list.add(orderInfo);
        }
        c.close();
        db.close();
        return list;
    }

    public int findPople(Pople pople) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(TABLE_POPLE,
                null,
                "userToken=? and cardNo=? and cardType=? and name=? and denNo=?",
                new String[]{pople.getUserToken(),pople.getCardNo(),pople.getCardType(),pople.getName(),pople.getDenNo()},
                null,
                null, null);
        if (c.moveToNext()) {
            return c.getCount();
        }else {
            return 0;
        }
    }

    public int findOrderinfo(OrderInfo orderinfo) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(TABLE_ORDERINFO,
                null,
                "hisOrderId=?",
                new String[]{orderinfo.getHisOrderId()},
                null,
                null, null);
        if (c.moveToNext()) {
            return c.getCount();
        }else {
            return 0;
        }
    }

    public int delAllUserTokenPople(Pople pople) {
        SQLiteDatabase db = getWritableDatabase();
        int res = db.delete(TABLE_POPLE, "id=?", new String[]{String.valueOf(pople.getId())});
        db.close();
        return res;
    }

    public int delAllUserTokenPople(String userToken) {
        SQLiteDatabase db = getWritableDatabase();
        int res = db.delete(TABLE_POPLE, "userToken=?", new String[]{String.valueOf(userToken)});
        db.close();
        return res;
    }


    public int updatePople(Pople pople, String Clause, String[] s) {
        ContentValues value = new ContentValues();
        value.put("guardian",pople.getGuardian());
        value.put("birthday", pople.getBirthday());
        value.put("phone", pople.getPhone());
        value.put("upstate", pople.getUpstate());
        value.put("defult",pople.getDefult());
        SQLiteDatabase db = getWritableDatabase();
        int res = db.update(TABLE_POPLE, value, Clause, s);
        db.close();
        return res;
    }

    public int updatePople(ContentValues value, String Clause, String[] s) {
        SQLiteDatabase db = getWritableDatabase();
        int res = db.update(TABLE_POPLE, value, Clause, s);
        db.close();
        return res;
    }

    public int updateOrderinfo(OrderInfo orderInfo, String Clause, String[] s) {
        ContentValues value = new ContentValues();
        value.put("upstate", orderInfo.getUpstate());
        SQLiteDatabase db = getWritableDatabase();
        int res = db.update(TABLE_ORDERINFO, value, Clause, s);
        db.close();
        return res;
    }


    public void close() {
        if (db != null) {
            db.close();
        }
    }


}
