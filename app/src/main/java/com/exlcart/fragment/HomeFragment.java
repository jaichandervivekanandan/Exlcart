package com.exlcart.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exlcart.HomeActivity;
import com.exlcart.R;
import com.exlcart.adapter.MainCategoryAdapter;
import com.exlcart.adapter.NavigationDrawerAdapter;
import com.exlcart.interfaces.APIResult;
import com.exlcart.service.APIService;
import com.exlcart.utility.CommonData;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Saravanan on 7/8/2015.
 */
public class HomeFragment extends Fragment implements APIResult {

    RecyclerView recyclerView;
    RecyclerView.Adapter<MainCategoryAdapter.ViewHolder> adapter;
    RecyclerView.LayoutManager recyclerLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerMainCategory);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        recyclerLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        apiRequest();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void apiRequest() {
        new APIService().requestingWebService(getActivity(),
                CommonData.METHODGET, "getcategories?language_id=1", this, null);
    }

    String[] categoryName;
    String[] image;

    @Override
    public void getResult(boolean isSuccess, String result) {
        if (isSuccess) {
            try {
                JSONObject object = new JSONObject(result);
                if (object.getString("status").equals(CommonData.STATUS_SUCCESS_CODE)) {
                    JSONArray jsonArray = object.getJSONArray("categories");
                    int total_main_category = jsonArray.length();

                    categoryName = new String[total_main_category];
                    image = new String[total_main_category];

                    for (int i = 0; i < total_main_category; i++) {
                        categoryName[i] = jsonArray.getJSONObject(i).getString("name");
                        image[i] = "http://www.cwrodtool.com/customer/cwroto/images/camera-no-image.jpg";
                    }
                    MainCategoryAdapter adapter = new MainCategoryAdapter(getActivity(), categoryName, image);
                    recyclerView.setAdapter(adapter);
                    HomeActivity.naviAdapter = new NavigationDrawerAdapter(getActivity(), categoryName);
                    HomeActivity.drawerlist.setAdapter(HomeActivity.naviAdapter);
                    HomeActivity.naviAdapter.notifyDataSetChanged();
                    getActivity().invalidateOptionsMenu();
                }
            } catch (Exception ex) {
                Log.d("OBJECT", "" + ex.getMessage());
            }


        } else {
            Log.d("isSuccess else", "isSuccess else");
        }
    }
}