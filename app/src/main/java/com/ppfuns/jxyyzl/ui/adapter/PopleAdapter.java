package com.ppfuns.jxyyzl.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.Pople;

import java.util.List;

public class PopleAdapter extends RecyclerView.Adapter<PopleAdapter.SimpleItemViewHolder> {
    private Context mContext;
    private List<Pople> list;
    private View.OnFocusChangeListener itemFocusChange;
    private OnItemClickListener itemClickListener;

    public PopleAdapter(Context context, @NonNull List<Pople> dateItems) {
        this.mContext = context;
        this.list = dateItems;
    }

    @Override
    public SimpleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pople1, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SimpleItemViewHolder viewHolder, int position) {
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
                    view.setBackgroundResource(R.drawable.itemfocus2);
                    view.setPadding(25, 25, 25, 25);
                    viewHolder.textView1.setTextColor(0xFFFFFFFF);
                    viewHolder.textView2.setTextColor(0xFFFFFFFF);
                } else {
                    viewHolder.textView1.setTextColor(0xFF056B8D);
                    viewHolder.textView2.setTextColor(0xFF056B8D);
                    view.setBackgroundResource(R.drawable.itemfocus1);
                    view.setPadding(25, 25, 25, 25);
                }
            }
        });
        if ("添加就诊人".equals(list.get(position).getName())) {
            viewHolder.textView2.setVisibility(View.GONE);
            viewHolder.textView1.setGravity(Gravity.CENTER);
            viewHolder.textView1.setText(list.get(position).getName());
        } else if ("添加就诊卡".equals(list.get(position).getName())) {
            viewHolder.textView2.setVisibility(View.GONE);
            viewHolder.textView1.setGravity(Gravity.CENTER);
            viewHolder.textView1.setText(list.get(position).getName());
        } else {
            viewHolder.textView2.setVisibility(View.VISIBLE);
            viewHolder.textView1.setGravity(Gravity.LEFT);
            String name = "姓名：" + list.get(position).getName();
            String card = "身份证号：" + list.get(position).getDenNo();
            viewHolder.textView1.setText(name);
            viewHolder.textView2.setText(card);
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

    public interface OnItemClickListener {
        void onItemClickListener(View view, Pople pople);
    }

    final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;

        SimpleItemViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.tv01);
            textView2 = (TextView) itemView.findViewById(R.id.tv02);
        }
    }
}