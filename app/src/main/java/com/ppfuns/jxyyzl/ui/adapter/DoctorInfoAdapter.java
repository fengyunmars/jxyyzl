package com.ppfuns.jxyyzl.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.DoctorSchedule;

import java.util.List;

public class DoctorInfoAdapter extends RecyclerView.Adapter<DoctorInfoAdapter.SimpleItemViewHolder> {
    private Context mContext;
    private List<DoctorSchedule> list;
    private View.OnFocusChangeListener itemFocusChange;
    private OnItemClickListener itemClickListener;

    public DoctorInfoAdapter(Context context, @NonNull List<DoctorSchedule> dateItems) {
        this.mContext = context;
        this.list = dateItems;
    }

    @Override
    public SimpleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_doctor, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final SimpleItemViewHolder viewHolder, final int position) {
        viewHolder.itemView.setTag(position);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position >= list.size()) return;
                if (itemClickListener != null) {
                    itemClickListener.onItemClickListener(view,list.get(position));
                }
            }
        });

        viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (itemFocusChange != null) {
                    itemFocusChange.onFocusChange(view, b);
                }
                if (b) {
                    view.setBackgroundResource(R.drawable.itemfocus2);
                    viewHolder.textView1.setTextColor(0xFFFFFFFF);
                    viewHolder.textView2.setTextColor(0xFFFFFFFF);
                    viewHolder.textView3.setTextColor(0xFFFFFFFF);
                } else {
                    view.setBackgroundResource(R.drawable.itemfocus1);
                    viewHolder.textView1.setTextColor(0xFF056B8D);
                    viewHolder.textView2.setTextColor(0xFF056B8D);
                    viewHolder.textView3.setTextColor(0xFFFFFFFF);
                }
                view.setSelected(b);
            }
        });

        viewHolder.textView1.setText(list.get(position).getDoctorName());
        viewHolder.textView2.setText(list.get(position).getHosName()+"\r\n"+list.get(position).getDeptName());
        String type;
        try {
            type = (list.get(position).getRegType().contains("普通")) ? "普通号:" : "专家号:";
        } catch (Exception e) {
            type = "专家号:";
            e.printStackTrace();
        }
        viewHolder.textView3.setText(type + list.get(position).getRegFee() + "元");
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
        void onItemClickListener(View view, DoctorSchedule doctorSchedule);
    }

    final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        SimpleItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv01);
            textView1 = (TextView) itemView.findViewById(R.id.tv01);
            textView2 = (TextView) itemView.findViewById(R.id.tv02);
            textView3 = (TextView) itemView.findViewById(R.id.tv03);
        }
    }
}