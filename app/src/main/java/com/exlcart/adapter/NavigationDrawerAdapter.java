package com.exlcart.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.exlcart.R;

/**
 * Created by raghu on 25/7/15.
 */
public class NavigationDrawerAdapter extends BaseAdapter {
    String[] categoryName=null;
    Context context;
    public NavigationDrawerAdapter(Context context, String[] categoryName) {
        this.categoryName=categoryName;
        this.context=context;

    }

    @Override
    public int getCount() {
        return categoryName.length;
    }

    @Override
    public Object getItem(int position) {
        return categoryName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_custom, null);
        }
        TextView txtTitle = (TextView) convertView.findViewById(R.id.category_name);
        txtTitle.setText(categoryName[position]);
        return convertView;
    }
}
