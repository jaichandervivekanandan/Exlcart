package com.exlcart.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.exlcart.R;

/**
 * Created by Saravanan on 7/22/2015.
 */
public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.ViewHolder>{
    @Override
    public MainCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_main_category_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainCategoryAdapter.ViewHolder holder, int position) {
        holder.txtCategory.setText(""+catName[position]);

    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    private Context mContext;
    String[] catName;String[] img;
    public MainCategoryAdapter(Context context,String[] catName,String[] img){
        this.mContext = context;
        this.catName =catName;
        this.img = img;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layCategoryItem;
        ImageView imgCategory;
        TextView txtCategory;
        public ViewHolder(View v){
            super(v);
            imgCategory = (ImageView) v.findViewById(R.id.imgMainCategory);
            txtCategory = (TextView) v.findViewById(R.id.txtMainCategory);
            layCategoryItem = (LinearLayout) v.findViewById(R.id.layMainCategoryItem);
        }
    }
}
