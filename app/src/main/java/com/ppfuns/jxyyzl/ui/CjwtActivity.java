package com.ppfuns.jxyyzl.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.VerticalGridView;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.ui.adapter.CjwtAdapter;
import com.ppfuns.jxyyzl.ui.adapter.StringAdapter;
import com.ppfuns.jxyyzl.view.ListViewTv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:Administrator
 * Time:2017/12/27.23:41
 */

public class CjwtActivity extends BaseActivity {
    private List<String> mMenuList = new ArrayList<>();
    private ListViewTv listView;
    private VerticalGridView verticalGridView;
    private StringAdapter menuAdapter;
    private List<String> listStr;
    private CjwtAdapter cjwtAdapter;
    private String cjwts[] = new String[]{
            "问：“江西省预约诊疗”预约挂号后，如何到医院就诊？\n\n" +
                    "答：您好，就诊当天根据预约成功提醒按医院规定的取号时间及地点凭有效身份证件缴费取号（未在线支付的）就诊。具体流程参见各医院须知。",
            "问：“江西省预约诊疗”通过江西省预约诊疗系统预约需要手续费吗？\n\n" +
                    "答：您好，预约诊疗服务是我省推出的一项便民利民服务措施，无论通过为民健康网预约、还是12320电话预约还是医院预约服务台预约、微信预约，均不收取预约挂号手续费，只需要支付正常的挂号费用。",
            "问：“江西省预约诊疗”如何知道已预约成功？\n\n" +
                    "答：您好，您确认预约后，手机用户会收到问：“预约成功通知（可能含取号密码）”短信，就表示您已预约成功。如果未收到短信，可登陆预约系统，在预约记录里查询预约信息。还可拨打12320卫生公益热线咨询。",
            "问：“江西省预约诊疗”网站上显示有号，确认预约时却告诉我预约失败？\n\n" +
                    "答：您好，因为有很多用户同时在网上预约此号，在您提交材料的同时，号源已被其他用户先预约了。可以再重新选号预约。",
            "问：“江西省预约诊疗”用户一次最多可以预约几个号？\n\n" +
                    "答：您好，同一患者实名（有效证件号）在同一就诊日、同一医院、同一科室只能预约一次;在同一就诊日的预约总量不可超过4次。",
            "问：“江西省预约诊疗”已经预约成功的号能否转给别人？\n\n" +
                    "答：您好，不能。预约挂号采用的是实名制。",
            "问：“江西省预约诊疗”已成功预约上×××科室的号，但上午没有去，请问这个预约还有效吗？是否可以转到当天下午再去？\n\n" +
                    "答：您好，不能。您必须取消重新预约。"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cjwt);
        mMenuList.add("热点");
        initView(this);
    }

    private void initView(Context context) {
        listView = findViewById(R.id.yygh_lv01);
        verticalGridView = findViewById(R.id.jzrgladd_fl01);

        menuAdapter = new StringAdapter(this, mMenuList);
        listView.setAdapter(menuAdapter);

        listStr = Arrays.asList(cjwts);

        cjwtAdapter = new CjwtAdapter(this,listStr);
        verticalGridView.setNumColumns(1);
        verticalGridView.setAdapter(cjwtAdapter);

//        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, final long l) {
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                FlyLog.d();
//            }
//        });

    }
}
