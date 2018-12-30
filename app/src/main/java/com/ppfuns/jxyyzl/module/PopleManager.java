package com.ppfuns.jxyyzl.module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.ppfuns.jxyyzl.constant.HttpApi;
import com.ppfuns.jxyyzl.data.Pople;
import com.ppfuns.jxyyzl.data.RetMsg;
import com.ppfuns.jxyyzl.http.IHttp;
import com.ppfuns.jxyyzl.http.MyHttp;
import com.ppfuns.jxyyzl.utils.GsonUtils;
import com.ppfuns.jxyyzl.utils.MyLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: Fly
 * Time: 17-12-28 下午11:26.
 * Discription: This is PopleUp
 */

public class PopleManager {
    private static final String TAG = "PopleManager";
    private Context context;
    private SQLite sqLite;
    private IHttp iHttp = MyHttp.getInstance();

    public PopleManager(Context context) {
        this.context = context;
        sqLite = new SQLite(context);
    }

    public Pople getDefultPople() {
        try {
            return sqLite.getDefultPople(HealthAppTools.getUserToken(context));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Pople> getAllLocalPople(String userToken) {
        try {
            return sqLite.getAllPople(userToken);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getAllPople(final String userToken, @NonNull final ResultAllPople resultAllPople) {
        if (userToken == null) {
            resultAllPople.result(false, null);
        }
        iHttp.getString(HttpApi.getJxresUrl(context) + "/Api/pople?userToken=" + userToken, TAG + "1", new IHttp.HttpResult() {
            @Override
            public void succeed(Object object) {
                try {
                    String msg = object.toString();
                    RetMsg retMsg = GsonUtils.json2Object(msg, RetMsg.class);
                    if (retMsg != null && retMsg.getRet() == 0) {
                        sqLite.delAllUserTokenPople(userToken);
                        if (retMsg.getData() != null) {
                            List<Pople> list = GsonUtils.json2ListObj(GsonUtils.object2Json(retMsg.getData()), Pople.class);
                            if (list != null) {
                                for (Pople pople : list) {
                                    pople.setUserToken(userToken);
                                    if (sqLite.findPople(pople) < 1) {
                                        sqLite.insertPople(pople);
                                    }
                                }
                            } else {
                                list = new ArrayList<>();
                            }
                            resultAllPople.result(true, list);
                        } else {
                            resultAllPople.result(false, null);
                        }
                    } else {
                        resultAllPople.result(false, null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    resultAllPople.result(false, null);
                }
            }

            @Override
            public void failed(Object object) {
                resultAllPople.result(false, null);
            }
        });
    }

    public void savePople(final Pople pople, final Result result) {
        if (pople.getCardNo() == null) {
            pople.setCardNo("");
        }
        if (pople.getCardType() == null) {
            pople.setCardType("");
        }
        if (pople.getGuardian() == null) {
            pople.setGuardian("");
        }
        if (pople.getDefult() == null) {
            pople.setDefult("0");
        }
        if (pople.getIsguardian() == null) {
            pople.setIsguardian("0");
        }
        if (pople.getHospitalId() == null) {
            pople.setHospitalId("");
        }
        Map<String, String> map = pople.toMap();
        if (sqLite.findPople(pople) > 0) {
            Toast.makeText(context, "姓名证件等信息重复，不能重复添加!", Toast.LENGTH_SHORT).show();
            result.result(false);
            return;
        }
        iHttp.postString(HttpApi.getJxresUrl(context) + "/Api/pople/add", map, TAG + "2", new IHttp.HttpResult() {
            @Override
            public void succeed(Object object) {
                RetMsg msg = GsonUtils.json2Object(object.toString(), RetMsg.class);
                if (msg != null) {
                    if (msg.getRet() == 0) {
                        result.result(true);
                        pople.setUpstate("1");
                        sqLite.insertPople(pople);
                    } else {
                        Toast.makeText(context, "" + msg.getMsg(), Toast.LENGTH_SHORT).show();
                        result.result(false);
                    }
                } else {
                    Toast.makeText(context, "服务器返回未知错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failed(Object object) {
                Toast.makeText(context, "同步服务器数据失败", Toast.LENGTH_SHORT).show();
                result.result(false);
            }
        });

    }

    public void editPople(final Pople pople, final Result result) {
        if (pople.getCardNo() == null) {
            pople.setCardNo("");
        }
        if (pople.getCardType() == null) {
            pople.setCardType("");
        }
        if (pople.getGuardian() == null) {
            pople.setGuardian("");
        }
        if (pople.getDefult() == null) {
            pople.setDefult("0");
        }
        if (pople.getIsguardian() == null) {
            pople.setIsguardian("0");
        }
        if (pople.getHospitalId() == null) {
            pople.setHospitalId("");
        }
        Map<String, String> map = pople.toMap();
        if (sqLite.findPople(pople) < 0) {
            Toast.makeText(context, "不能修改关键字段信息", Toast.LENGTH_SHORT).show();
            result.result(false);
            return;
        }
        iHttp.postString(HttpApi.getJxresUrl(context) + "/Api/pople/edit", map, TAG + "3", new IHttp.HttpResult() {
            @Override
            public void succeed(Object object) {
                RetMsg msg = GsonUtils.json2Object(object.toString(), RetMsg.class);
                if (msg != null) {
                    if (msg.getRet() == 0) {
                        result.result(true);
                        int ret = sqLite.updatePople(pople, "id=?", new String[]{String.valueOf(pople.getId())});
                        MyLog.d("" + ret);
                    } else {
                        Toast.makeText(context, "" + msg.getMsg(), Toast.LENGTH_SHORT).show();
                        result.result(false);
                    }
                } else {
                    Toast.makeText(context, "服务器返回未知错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failed(Object object) {
                Toast.makeText(context, "同步服务器数据失败", Toast.LENGTH_SHORT).show();
                result.result(false);
            }
        });
    }

    public void cancle() {
        iHttp.cancelAll(TAG + "1");
        iHttp.cancelAll(TAG + "2");
        iHttp.cancelAll(TAG + "3");
        iHttp.cancelAll(TAG + "4");
        iHttp.cancelAll(TAG + "5");
    }

    public void setDefault(final Pople pople, final Result result) {
        iHttp.postString(HttpApi.getJxresUrl(context) + "/Api/pople/def", pople.toMap(), TAG + "4", new IHttp.HttpResult() {
            @Override
            public void succeed(Object object) {
                RetMsg msg = GsonUtils.json2Object(object.toString(), RetMsg.class);
                if (msg != null) {
                    if (msg.getRet() == 0) {
                        Pople defultPople = getDefultPople();
                        if (defultPople != null) {
                            defultPople.setDefult("0");
                            sqLite.updatePople(defultPople, "defult=?", new String[]{"1"});
                        }
                        pople.setDefult("1");
                        sqLite.updatePople(pople, "id=?", new String[]{String.valueOf(pople.getId())});
                        result.result(true);
                    } else {
                        Toast.makeText(context, "" + msg.getMsg(), Toast.LENGTH_SHORT).show();
                        result.result(false);
                    }
                } else {
                    Toast.makeText(context, "服务器返回未知错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failed(Object object) {
                Toast.makeText(context, "同步服务器数据失败", Toast.LENGTH_SHORT).show();
                result.result(false);
            }
        });
    }

    public void del(final Pople pople, final Result result) {
        Map<String, String> map = pople.toMap();
        iHttp.postString(HttpApi.getJxresUrl(context) + "/Api/pople/del", map, TAG + "5", new IHttp.HttpResult() {
            @Override
            public void succeed(Object object) {
                sqLite.delAllUserTokenPople(pople);
                result.result(true);
            }

            @Override
            public void failed(Object object) {
                result.result(false);
            }
        });
    }

    public interface Result {
        void result(boolean flag);
    }

    public interface ResultAllPople {
        void result(Boolean flag, List<Pople> list);
    }

}
