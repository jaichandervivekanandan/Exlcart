package com.exlcart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Saravanan on 7/4/2015.
 */
public class PageFragment extends Fragment {
    private TextView description;
    private ImageView image;


    public static PageFragment getInstance(String tabName,int position){
       PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString("page_title", tabName);
        args.putInt("position", position);
       return fragment;
   }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page, container,false);

        description = (TextView)rootView.findViewById(R.id.textView);
        image = (ImageView) rootView.findViewById(R.id.image);

        description.setText(getArguments().getString("page_title"));

        return rootView;
    }
}
