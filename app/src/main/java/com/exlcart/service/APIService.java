package com.exlcart.service;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.exlcart.AppController;
import com.exlcart.R;
import com.exlcart.interfaces.APIResult;
import com.exlcart.utility.CommonData;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Saravanan on 7/1/2015.
 */
public class APIService {
    public StringRequest stringRequest;
    private int mRequestingMethod = 1;
    private Context mContext;

    private HashMap<String, String> mHashMap;
    private APIResult mResult;

    private Dialog mProgressdialog;

    public void requestingWebService(Context context, int method, String url, APIResult result, HashMap<String, String> hashMap) {

        this.mRequestingMethod = method;
        this.mContext = context;
        this.mResult = result;
        this.mHashMap = hashMap;

        View view = View.inflate(mContext, R.layout.progress_bar, null);
        mProgressdialog = new Dialog(mContext, R.style.NewDialog);
        mProgressdialog.setContentView(view);
        mProgressdialog.setCancelable(false);
        mProgressdialog.show();

        stringRequest = new StringRequest(method, CommonData.BASE_PATH + url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mProgressdialog.dismiss();
                mResult.getResult(true, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressdialog.dismiss();
                String errorMessage = "Please Try Again ,";
                if (error instanceof NetworkError) {
                } else if (error instanceof ServerError) {
                    errorMessage += "Server Error ";
                } else if (error instanceof AuthFailureError) {
                    errorMessage += "AuthFailureError ";
                } else if (error instanceof ParseError) {
                    errorMessage += "ParseError ";
                } else if (error instanceof NoConnectionError) {
                    errorMessage += "Net Connection Error ";
                } else if (error instanceof TimeoutError) {
                    errorMessage += "Request Timeout";
                } else if (error instanceof Exception) {
                    errorMessage += error.getMessage();
                }
                errorMessage+=error.getMessage();
                mResult.getResult(true, errorMessage);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                if (mRequestingMethod == CommonData.METHODGET) {
                    mHashMap.put("dummy", "dummy");
                    return mHashMap;
                }
                return mHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
