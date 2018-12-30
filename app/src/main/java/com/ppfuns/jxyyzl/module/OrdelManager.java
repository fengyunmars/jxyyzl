package com.ppfuns.jxyyzl.module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ppfuns.jxyyzl.constant.HttpApi;
import com.ppfuns.jxyyzl.data.OrderInfo;
import com.ppfuns.jxyyzl.data.RetMsg;
import com.ppfuns.jxyyzl.http.IHttp;
import com.ppfuns.jxyyzl.http.MyHttp;
import com.ppfuns.jxyyzl.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: Fly
 * Time: 17-12-28 下午11:26.
 * Discription: This is PopleUp
 */

public class OrdelManager {
    private static final String TAG = "OrdelManager";
    private Context context;
    private SQLite sqLite;
    private IHttp iHttp = MyHttp.getInstance();

    public OrdelManager(Context context) {
        this.context = context;
        sqLite = new SQLite(context);
    }

    public void saveOrderInfo(final OrderInfo orderInfo, final Result result) {
        Map<String, String> map = orderInfo.toMap();
        iHttp.postString(HttpApi.getJxresUrl(context) + "/Api/orderinfo/add", map, TAG + "0", new IHttp.HttpResult() {
            @Override
            public void succeed(Object object) {
                try {
                    RetMsg msg = GsonUtils.json2Object(object.toString(), RetMsg.class);
                    if (msg != null && msg.getRet() == 0) {
                        orderInfo.setUpstate("1");
                        sqLite.insertOrderInfo(orderInfo);
                        result.result(true);
                    } else {
                        sqLite.insertOrderInfo(orderInfo);
                        result.result(false);
                    }
                } catch (Exception e) {
                    sqLite.insertOrderInfo(orderInfo);
                    result.result(false);
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Object object) {
                sqLite.insertOrderInfo(orderInfo);
                result.result(false);
            }
        });
    }

    public void getAllOrderInfo(final String userToken, @NonNull final ResultAllOrderinfo resultAllOrderinfo) {
        if (TextUtils.isEmpty(userToken)) {
            resultAllOrderinfo.result(false, null);
        }
        final List<OrderInfo> list = sqLite.getAllOrderinfo(userToken);
        //排序
        iHttp.getString(HttpApi.getJxresUrl(context) + "/Api/orderinfo?userToken=" + userToken, TAG + "1", new IHttp.HttpResult() {
            @Override
            public void succeed(Object object) {
                try {
                    String msg = object.toString();
                    RetMsg retMsg = GsonUtils.json2Object(msg, RetMsg.class);
                    if (retMsg != null && retMsg.getRet() == 0) {
                        if (retMsg.getData() != null) {
                            List<OrderInfo> wwwList = GsonUtils.json2ListObj(GsonUtils.object2Json(retMsg.getData()), OrderInfo.class);
                            if (wwwList != null) {
                                for (OrderInfo orderinfo : wwwList) {
                                    orderinfo.setUserToken(userToken);
                                    if (sqLite.findOrderinfo(orderinfo) < 1) {
                                        sqLite.insertOrderInfo(orderinfo);
                                    }
                                }
                                for (int i = 0; i < list.size(); i++) {
                                    boolean flag = false;
                                    for (int j = 0; j < wwwList.size(); j++) {
                                        if (list.get(i).getHisOrderId().equals(wwwList.get(j).getHisOrderId())) {
                                            flag = true;
                                            break;
                                        }
                                    }
                                    if (!flag) {
                                        wwwList.add(list.get(i));
                                        if (!("1".equals(list.get(i).getUpstate()))) {
                                            upOrderInfo(list.get(i));
                                        }
                                    }
                                }
                            } else {
                                wwwList = new ArrayList<>();
                            }
                            resultAllOrderinfo.result(true, wwwList);
                        } else {
                            resultAllOrderinfo.result(true, list);
                        }
                    } else {
                        resultAllOrderinfo.result(true, list);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    resultAllOrderinfo.result(true, list);
                }
            }

            @Override
            public void failed(Object object) {
                resultAllOrderinfo.result(false, list);
            }
        });
    }

    private void upOrderInfo(final OrderInfo orderInfo) {
        Map<String, String> map = orderInfo.toMap();
        iHttp.postString(HttpApi.getJxresUrl(context) + "/Api/orderinfo/add", map, TAG + "2", new IHttp.HttpResult() {
            @Override
            public void succeed(Object object) {
                try {
                    RetMsg msg = GsonUtils.json2Object(object.toString(), RetMsg.class);
                    if (msg != null && msg.getRet() == 0) {
                        orderInfo.setUpstate("1");
                        sqLite.updateOrderinfo(orderInfo, "hisOrderId=?", new String[]{orderInfo.getHisOrderId()});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Object object) {
            }
        });
    }

    public void cancle() {
        iHttp.cancelAll(TAG + "0");
        iHttp.cancelAll(TAG + "1");
        iHttp.cancelAll(TAG + "2");
    }

    public interface Result {
        void result(boolean flag);
    }

    public interface ResultAllOrderinfo {
        void result(Boolean flag, List<OrderInfo> list);
    }


}
