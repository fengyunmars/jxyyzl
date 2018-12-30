package com.ppfuns.jxyyzl.ui.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.constant.HisReqXmlText;
import com.ppfuns.jxyyzl.data.AppointInfo;
import com.ppfuns.jxyyzl.data.Resp;
import com.ppfuns.jxyyzl.module.HisHttpHandle;

import java.util.List;

public class AppointInfoAdapter extends RecyclerView.Adapter<AppointInfoAdapter.SimpleItemViewHolder> {
    private Context mContext;
    private List<AppointInfo> list;
    private View.OnFocusChangeListener itemFocusChange;
    private OnItemClickListener itemClickListener;
    private HisHttpHandle<Resp> hisHttpHandle;
    //    private SQLite sqLite;
    private ProgressDialog progressDialog;

    public AppointInfoAdapter(Context context, @NonNull List<AppointInfo> dateItems) {
        this.mContext = context;
        this.list = dateItems;
        hisHttpHandle = new HisHttpHandle<>();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("正在取消订单......");
//        sqLite = new SQLite(context);
    }

    @Override
    public SimpleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_appointinfo, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SimpleItemViewHolder viewHolder, final int position) {
        viewHolder.itemView.setTag(position);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (itemClickListener != null) {
                    itemClickListener.onItemClickListener(view, list.get(pos));
                }
            }
        });

        viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                view.setSelected(b);
                if (itemFocusChange != null) {
                    itemFocusChange.onFocusChange(view, b);
                }
                if (b) {
                    viewHolder.ll01.setBackgroundResource(R.drawable.itemfocus2);
                    viewHolder.textView1.setTextColor(0xFFFFFFFF);
                    viewHolder.textView2.setTextColor(0xFFFFFFFF);
                    viewHolder.textView3.setTextColor(0xFFFFFFFF);
                } else {
                    viewHolder.textView1.setTextColor(0xFF056B8D);
                    viewHolder.textView2.setTextColor(0xFF056B8D);
                    viewHolder.textView3.setTextColor(0xFF056B8D);
                    viewHolder.ll01.setBackgroundResource(R.drawable.itemfocus1);
                }
            }
        });

        viewHolder.ll02.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    view.setBackgroundResource(R.drawable.itemfocus2);
                    viewHolder.textView4.setTextColor(0xFFFFFFFF);
                } else {
                    viewHolder.textView4.setTextColor(0xFF056B8D);
                    view.setBackgroundResource(R.drawable.itemfocus1);
                }
            }
        });

        // 预约登记成功 = 0,已就诊 = 1,预约登记取消成功 = 2,逾期 = 3,作废 = 4,HIS预约登记中 = 5,支付中 = 6,取消预约中 = 7,预约登记失败 = 8
        switch (list.get(position).getState()) {
            case "0":
            case "5":
            case "6":
            case "7":
                viewHolder.ll02.setVisibility(View.VISIBLE);
                viewHolder.textView2.setText("订单进行中");
                viewHolder.itemView.setFocusable(false);
                viewHolder.ll02.setFocusable(true);
                break;
            case "1":
                viewHolder.textView2.setText("订单已完成");
                viewHolder.itemView.setFocusable(true);
                viewHolder.ll02.setFocusable(false);
                viewHolder.ll02.setVisibility(View.GONE);
                break;
            case "2":
            case "3":
            case "4":
            case "8":
            default:
                viewHolder.textView2.setText("订单已取消");
                viewHolder.itemView.setFocusable(true);
                viewHolder.ll02.setFocusable(false);
                viewHolder.itemView.setFocusable(true);
                viewHolder.ll02.setFocusable(false);
                viewHolder.ll02.setVisibility(View.GONE);
                break;
        }
        AppointInfo info = list.get(position);
        String hospitalId = "30655";
        String text = "";
        try {
            String bizCode = info.getBizCode();
            String str[] = bizCode.split("\\|");
            viewHolder.textView1.setText(str[0]);
            String name = str[1];
            String startendTime = str[2];
            if (str.length > 3) {
                hospitalId = str[3];
            }
            String time1 = info.getTimeSlice().equals("1") ? "上午" : "下午";
            text = "预约时间：" + info.getDate() + " " + time1 + " " + startendTime +
                    "\n就诊科室：" + info.getDeptName() +
                    "\n就诊医生：" + info.getDoctorName() +
                    "\n患者姓名：" + name +
                    "\n诊察费用：￥" + info.getFee() + "元" +
                    "\n号\t\t序：" + info.getRegNo() + "号";
            viewHolder.textView3.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final String fhospitalId = hospitalId;

        //取消预约
        viewHolder.ll02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                hisHttpHandle.requset(mContext,
                        "Resp",
                        HisReqXmlText.requestCancleOrder,
                        Resp.class,
                        new HisHttpHandle.HisResult<Resp>() {
                    @Override
                    public void result(List<Resp> listr) {
                        if (position >= list.size()) return;
                        progressDialog.dismiss();
                        try {
                            if ("0".equals(listr.get(0).getR())) {
                                Toast.makeText(mContext, "取消成功", Toast.LENGTH_SHORT).show();
                                list.get(position).setState("2");
                                list.remove(position);
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(mContext, "取消失败:" + listr.get(0).getM(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void faile(int hisError) {
                        progressDialog.dismiss();
                        Toast.makeText(mContext, "取消失败", Toast.LENGTH_SHORT).show();
                    }
                }, fhospitalId, list.get(position).getHisOrderId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (this.list != null) ? this.list.size() : 0;
    }

    public void setItemFocusChange(View.OnFocusChangeListener itemFocusChange) {
        this.itemFocusChange = itemFocusChange;
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, AppointInfo appointInfo);
    }

    final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public LinearLayout ll01, ll02;

        SimpleItemViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.iod_tv01);
            textView2 = itemView.findViewById(R.id.iod_tv02);
            textView3 = itemView.findViewById(R.id.iod_tv03);
            textView4 = itemView.findViewById(R.id.iod_tv04);
            textView4.setText("取消\n预约");
            ll01 = itemView.findViewById(R.id.iod_ll01);
            ll02 = itemView.findViewById(R.id.iod_ll02);
        }
    }
}