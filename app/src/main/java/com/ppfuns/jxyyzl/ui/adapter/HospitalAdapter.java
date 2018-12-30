package com.ppfuns.jxyyzl.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.Hospital;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.SimpleItemViewHolder> {
    private Context mContext;
    private List<Hospital> list;
    private View.OnFocusChangeListener itemFocusChange;
    private OnItemClickListener itemClickListener;

    public HospitalAdapter(Context context, @NonNull List<Hospital> dateItems) {
        this.mContext = context;
        this.list = dateItems;
    }

    @Override
    public SimpleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_hosptal, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SimpleItemViewHolder viewHolder, final int position) {
        viewHolder.itemView.setTag(position);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (position >= list.size()) return;
                    if (itemClickListener != null) {
                        itemClickListener.onItemClickListener(view, list.get(position));
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
                    view.setSelected(b);
                    if (itemFocusChange != null) {
                        itemFocusChange.onFocusChange(view, b);
                    }
                    if (b) {
                        if (!TextUtils.isEmpty(list.get(position).getHosLevel()) && list.get(position).getHosLevel().contains("三级")) {
                            view.setBackgroundResource(R.drawable.hospitalf2);
                        } else {
                            view.setBackgroundResource(R.drawable.itemfocus2);
                        }
                        viewHolder.textView1.setTextColor(0xFFFFFFFF);
                        viewHolder.textView2.setTextColor(0xFFFFFFFF);
                    } else {
                        if (!TextUtils.isEmpty(list.get(position).getHosLevel()) && list.get(position).getHosLevel().contains("三级")) {
                            view.setBackgroundResource(R.drawable.hospitalf1);
                        } else {
                            view.setBackgroundResource(R.drawable.itemfocus1);
                        }
                        viewHolder.textView1.setTextColor(0xFF056B8D);
                        viewHolder.textView2.setTextColor(0xFF056B8D);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        if (!TextUtils.isEmpty(list.get(position).getHosLevel()) && list.get(position).getHosLevel().contains("三级")) {
            viewHolder.itemView.setBackgroundResource(R.drawable.hospitalf1);
        } else {
            viewHolder.itemView.setBackgroundResource(R.drawable.itemfocus1);
        }
        viewHolder.textView1.setText(list.get(position).getHosName());
        viewHolder.textView2.setText(list.get(position).getAddress());
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
        void onItemClickListener(View view, Hospital hospitalInfo);
    }

    final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;

        SimpleItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv01);
            textView1 = (TextView) itemView.findViewById(R.id.tv01);
            textView2 = (TextView) itemView.findViewById(R.id.tv02);
        }
    }
}