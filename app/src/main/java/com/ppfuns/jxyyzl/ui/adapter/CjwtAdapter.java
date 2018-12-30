package com.ppfuns.jxyyzl.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ppfuns.jxyyzl.R;

import java.util.List;

public class CjwtAdapter extends RecyclerView.Adapter<CjwtAdapter.SimpleItemViewHolder> {
    private Context mContext;
    private List<String> list;

    public CjwtAdapter(Context context, @NonNull List<String> dateItems) {
        this.mContext = context;
        this.list = dateItems;
    }

    @Override
    public SimpleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cjwt, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SimpleItemViewHolder viewHolder, int position) {

        viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    view.setBackgroundResource(R.drawable.itemfocus2);
                    viewHolder.textView1.setTextColor(0xFFFFFFFF);
                } else {
                    viewHolder.textView1.setTextColor(0xFF056B8D);
                    view.setBackgroundResource(R.drawable.itemfocus1);
                }
            }
        });

        viewHolder.textView1.setText(""+list.get(position));

    }

    @Override
    public int getItemCount() {
        return (this.list != null) ? this.list.size() : 0;
    }


    final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;

        SimpleItemViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.icjwt_tv01);
        }
    }
}