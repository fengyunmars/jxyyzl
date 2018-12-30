package com.ppfuns.jxyyzl.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.Pople;

import java.util.List;

public class PopleMgAdapter extends RecyclerView.Adapter<PopleMgAdapter.SimpleItemViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<Pople> list;
    private View.OnFocusChangeListener itemFocusChange;
    private OnItemClickListener itemClickListener;

    public PopleMgAdapter(Context context, @NonNull List<Pople> dateItems) {
        this.mContext = context;
        this.list = dateItems;
    }

    @Override
    public SimpleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pople2, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SimpleItemViewHolder viewHolder, int position) {
        viewHolder.itemView.setTag(position);
        viewHolder.ll01.setTag(position);
        viewHolder.ll02.setTag(position);
        viewHolder.ll03.setTag(position);
        viewHolder.ll04.setTag(position);
        viewHolder.itemView.setOnClickListener(this);
        viewHolder.ll01.setOnClickListener(this);
        viewHolder.ll02.setOnClickListener(this);
        viewHolder.ll03.setOnClickListener(this);
        viewHolder.ll04.setOnClickListener(this);

        viewHolder.ll01.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    view.setBackgroundResource(R.drawable.itemfocus2);
                    viewHolder.textView1.setTextColor(0xFFFFFFFF);
                    viewHolder.textView2.setTextColor(0xFFFFFFFF);
                } else {
                    viewHolder.textView1.setTextColor(0xFF056B8D);
                    viewHolder.textView2.setTextColor(0xFFFFFFFF);
                    view.setBackgroundResource(R.drawable.itemfocus1);
                }
            }
        });

        viewHolder.ll02.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    view.setBackgroundResource(R.drawable.itemfocus2);
                    viewHolder.textView3.setTextColor(0xFFFFFFFF);
                } else {
                    viewHolder.textView3.setTextColor(0xFF056B8D);
                    view.setBackgroundResource(R.drawable.itemfocus1);
                }
            }
        });

        viewHolder.ll03.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        viewHolder.ll04.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    view.setBackgroundResource(R.drawable.itemfocus2);
                    viewHolder.textView5.setTextColor(0xFFFFFFFF);
                } else {
                    viewHolder.textView5.setTextColor(0xFF056B8D);
                    view.setBackgroundResource(R.drawable.itemfocus1);
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
                } else {
                    viewHolder.ll01.setBackgroundResource(R.drawable.itemfocus1);
                    viewHolder.textView1.setTextColor(0xFF056B8D);
                    viewHolder.textView2.setTextColor(0xFF056B8D);
                }
            }
        });
        if ("添加就诊人".equals(list.get(position).getName()) || "添加就诊卡".equals(list.get(position).getName())) {
            viewHolder.itemView.setFocusable(true);
            viewHolder.itemView.setFocusableInTouchMode(true);
            viewHolder.ll02.setVisibility(View.INVISIBLE);
            viewHolder.ll03.setVisibility(View.INVISIBLE);
            viewHolder.ll04.setVisibility(View.INVISIBLE);
            viewHolder.textView2.setVisibility(View.GONE);
            viewHolder.textView1.setGravity(Gravity.CENTER);
            viewHolder.textView1.setText(list.get(position).getName());
        } else {
            viewHolder.itemView.setFocusable(false);
            viewHolder.itemView.setFocusableInTouchMode(false);
            viewHolder.ll02.setVisibility(View.VISIBLE);
            viewHolder.ll03.setVisibility(View.VISIBLE);
            viewHolder.ll04.setVisibility(View.VISIBLE);
            viewHolder.textView2.setVisibility(View.VISIBLE);
            String name = "姓名：" + list.get(position).getName();
            String card = "身份证号：" + list.get(position).getDenNo();
            viewHolder.textView1.setGravity(Gravity.LEFT);
            viewHolder.textView1.setText(name);
            viewHolder.textView2.setText(card);
        }

        if("1".equals(list.get(position).getDefult())){
//            String name = "姓名：" + list.get(position).getName()+"（默认）";
//            viewHolder.textView1.setText(name);
            viewHolder.iv01.setVisibility(View.VISIBLE);
        } else {
            viewHolder.iv01.setVisibility(View.GONE);
        }


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

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        if (itemClickListener != null) {
            itemClickListener.onItemClickListener(v, list.get(pos));
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, Pople pople);
    }

    final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;
        public RelativeLayout ll01;
        public LinearLayout ll02;
        public LinearLayout ll03;
        public LinearLayout ll04;
        public ImageView iv01;

        SimpleItemViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.tv01);
            textView2 = itemView.findViewById(R.id.tv02);
            textView3 = itemView.findViewById(R.id.tv03);
            textView4 = itemView.findViewById(R.id.tv04);
            textView5 = itemView.findViewById(R.id.tv05);
            textView3.setText("设为\n默认");
            textView4.setText("更改\n信息");
            textView5.setText("移除\n信息");
            ll01 = itemView.findViewById(R.id.ll01);
            ll02 = itemView.findViewById(R.id.ll02);
            ll03 = itemView.findViewById(R.id.ll03);
            ll04 = itemView.findViewById(R.id.ll04);
            iv01 = itemView.findViewById(R.id.iv01);
        }
    }
}