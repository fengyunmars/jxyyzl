package com.ppfuns.jxyyzl.constant;


/**
 * Author:Administrator
 * Time:2017/12/15.8:56
 */

public class HisReqXmlText {
    public static final String requestCommonRegion =
            "<Req>\n" +
                    "   <Head>\n" +
                    "       <T></T>\n" +
                    "       <K></K>\n" +
                    "       <H>0001</H>\n" +
                    "       <C>9001</C>\n" +
                    "       <P></P>\n" +
                    "   </Head>\n" +
                    "</Req>";
    public static final String requestHospital =
            "<Req>\n" +
                    "   <Head>\n" +
                    "       <T></T>\n" +
                    "       <K></K>\n" +
                    "       <H>0001</H>\n" +
                    "       <C>9002</C>\n" +
                    "       <P></P>\n" +
                    "   </Head>\n" +
                    "   <Service>\n" +
                    "       <regionCode >%s</regionCode >\n" +
                    "   </Service>\n" +
                    "</Req>";
    public static final String requestDept = "<Req>\n" +
            "<Head>\n" +
            "    <T></T>\n" +
            "    <K></K>\n" +
            "    <H>%s</H>\n" +
            "    <C>1002</C>\n" +
            "    <P></P>\n" +
            "</Head>\n" +
            "</Req>";
    public static final String requstDoctor = "<Req>\n" +
            "<Head>\n" +
            "    <T></T>\n" +
            "    <K></K>\n" + //第三方获取的Token值
            "    <H>%s</H>\n" + //医院编号（参照医院代码表）
            "    <C>1003</C>\n" +
            "    <P></P>\n" +
            "</Head>\n" +
            "<Service>\n" +
            "    <deptId>%s</deptId>\n" +//科室ID
            "</Service>\n" +
            "</Req>";
    public static final String requestSchedule = "<Req>\n" +
            "<Head>\n" +
            "    <T></T>\n" +
            "    <K></K>\n" +
            "    <H>%s</H>\n" +
            "    <C>1004</C>\n" +
            "    <P></P>\n" +
            "</Head>\n" +
            "<Service>\n" +
            "    <deptId>%s</deptId>\n" +
//            "    <doctorId>%s</doctorId>\n" +
            "    <searchDate>%s</searchDate>\n" +
            "</Service>\n" +
            "</Req>";
    /**
     * 获取排班号源1005
     */
    public static final String requestReginfo = "<Req>\n" +
            "<Head>\n" +
            "    <T></T>\n" +
            "    <K></K>\n" +
            "    <H>%s</H>\n" +
            "    <C>1005</C>\n" +
            "    <P></P>\n" +
            "</Head>\n" +
            "<Service>\n" +
            "<scheduleID>%s</scheduleID>\n" +
            "<regType>%s</regType>\n" +
            "</Service>\n" +
            "</Req>";
    //就诊卡校验
    public static final String requestCard = "<Req>\n" +
            "<Head>\n" +
            "    <T></T>\n" +
            "    <K></K>\n" +
            "    <H>%s</H>\n" +
            "    <C>9003</C>\n" +
            "    <P></P>\n" +
            "</Head>\n" +
            "<Service>\n" +
            "<CardNo>%s</CardNo>\n" +//卡号
            "<CardType>%s</CardType>\n" +//卡类型:0医院就诊卡1居民健康卡
            "<Name>%s</Name>\n" +
            "</Service>\n" +
            "</Req>";
    //预约1006
    public static final String requestOrder = "<Req>\n" +
            "   <Head>\n" +
            "       <T></T>\n" +
            "       <K></K>\n" +
            "       <H>%s</H>\n" +
            "       <C>1006</C>\n" +
            "       <P></P>\n" +
            "   </Head>\n" +
            "   <Service>\n" +
            "       <CardNo>%s</CardNo>\n" +
            "       <CardType>%s</CardType>\n" +
            "       <code>%s</code>\n" +
            "       <denNo>%s</denNo>\n" +
            "       <name>%s</name>\n" +
            "       <sex>%s</sex>\n" +
            "       <birthday>%s</birthday>\n" +
            "       <phone>%s</phone>\n" +
            "       <guardian>%s</guardian>\n" +
            "       <isguardian>%s</isguardian>\n" +
            "       <BizCode>%s</BizCode>\n" +
            "   </Service>\n" +
            "</Req>";
    //按订单号查询订单1022
    public static final String requestOrderInfo = "<Req>\n" +
            "<Head>\n" +
            "    <T></T>\n" +
            "    <K></K>\n" +
            "    <H>%s</H>\n" +
            "    <C>1022</C>\n" +
            "    <P></P>\n" +
            "</Head>\n" +
            "<Service>\n" +
            "<hisOrderId>%s</hisOrderId>\n" +
            "</Service>\n" +
            "</Req>\n";
    //取消预约订单
    public static final String requestCancleOrder = "<Req>\n" +
            "<Head>\n" +
            "    <T></T>\n" +
            "    <K></K>\n" +
            "    <H>%s</H>\n" +
            "    <C>1009</C>\n" +
            "    <P></P>\n" +
            "</Head>\n" +
            "<Service>\n" +
            "<hisOrderId>%s</hisOrderId>\n" +
            "</Service>\n" +
            "</Req>\n";
}
