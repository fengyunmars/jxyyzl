package com.ppfuns.jxyyzl.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.DayInfo;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.SimpleItemViewHolder> {
    private Context mContext;
    private List<DayInfo> list;
    private View.OnFocusChangeListener itemFocusChange;
    private OnItemClickListener itemClickListener;

    public DateAdapter(Context context, @NonNull List<DayInfo> dateItems) {
        this.mContext = context;
        this.list = dateItems;
    }

    @Override
    public SimpleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_deptdate, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final SimpleItemViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        viewHolder.itemView.setTag(position);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (position >= list.size()) return;
                    if (itemClickListener != null) {
                        if (!list.get(position).getDoctorScheduleList().isEmpty()) {
                            itemClickListener.onItemClickListener(view, list.get(position));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                try {
                    if (position >= list.size()) return;
                    if (itemFocusChange != null) {
                        itemFocusChange.onFocusChange(view, b);
                    }
                    if (b) {
                        view.setBackgroundResource(R.drawable.itemfocus2);
                        view.setPadding(0, 25, 0, 25);
                        if (!list.get(position).getDoctorScheduleList().isEmpty()) {
                            viewHolder.textView1.setTextColor(0xFFFFFFFF);
                            viewHolder.textView2.setTextColor(0xFFFFFFFF);
                        }
                    } else {
                        view.setBackgroundResource(R.drawable.itemfocus1);
                        view.setPadding(0, 25, 0, 25);
                        if (!list.get(position).getDoctorScheduleList().isEmpty()) {
                            viewHolder.textView1.setTextColor(0xFF056B8D);
                            viewHolder.textView2.setTextColor(0xFF056B8D);
                        }
                    }
                    view.setSelected(b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (list.get(position).getDoctorScheduleList().isEmpty()) {
            viewHolder.textView1.setText(list.get(position).getWeek() + "（满）");
            viewHolder.textView2.setText(list.get(position).getStrDay());
            viewHolder.textView1.setTextColor(0xFFAAAAAA);
            viewHolder.textView2.setTextColor(0xFFAAAAAA);
        } else {
            viewHolder.textView1.setText(list.get(position).getWeek());
            viewHolder.textView2.setText(list.get(position).getStrDay());
            viewHolder.textView1.setTextColor(0xFF056B8D);
            viewHolder.textView2.setTextColor(0xFF056B8D);
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
        void onItemClickListener(View view, DayInfo deptDate);
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