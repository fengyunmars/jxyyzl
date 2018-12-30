package com.ppfuns.jxyyzl.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ppfuns.jxyyzl.R;
import com.ppfuns.jxyyzl.data.CommonRegion;

import java.util.List;

/**
 * Author: Fly
 * Time: 17-12-18 上午6:52.
 * Discription: This is RegionAdapter
 */

public class RegionAdapter extends BaseAdapter {
    private List<CommonRegion> mItems;
    private Context mContext;

    public RegionAdapter(Context context,List<CommonRegion> list){
        this.mContext=context;
        this.mItems = list;
    }
    @Override
    public int getCount() {
        return mItems ==null?0: mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listmenu, null);
            holder.textView = convertView.findViewById(R.id.tv01);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(mItems.get(position).getName());
        return convertView;
    }

    private class ViewHolder {
        TextView textView;
    }
}
