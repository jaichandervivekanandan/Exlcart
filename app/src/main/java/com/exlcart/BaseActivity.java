package com.exlcart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;


public abstract class BaseActivity extends FragmentActivity {
    public final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        int view = setLayout();
        if (isOnline()) {
            setContentView(view);
            Initialize();
        } else {
            setContentView(R.layout.activity_main);

            findViewById(R.id.button1).setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = getIntent();
                            finish();
                            startActivity(i);
                        }
                    });
            findViewById(R.id.button2).setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
        }
    }

    public abstract int setLayout();

    public abstract void Initialize();

    public void ShowToast(Context contex, String message) {
        Toast toast = Toast.makeText(contex, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showLog(String msg) {
        Log.i(TAG, msg);
    }


    public final boolean isOnline() {
        ConnectivityManager connectivity = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public static String convertPassMd5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return password;
    }


    public boolean closeChildActivity = false;
    public static Dialog dialog;
    public static TextView txtHeading;
    public static TextView txtBody;
    public static TextView txtDone;

    public String SUCCESSVALUE = "OK";
    public boolean CANCELLABLE = false;

    public void CustomDailog(Context context, String heading, String body, String sucessText) {
        SUCCESSVALUE = sucessText;
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_layout);

        dialog.setCancelable(CANCELLABLE);

        txtHeading = (TextView) dialog.findViewById(R.id.popheading);
        txtBody = (TextView) dialog.findViewById(R.id.popbody);
        txtDone = (TextView) dialog.findViewById(R.id.popclose);

        txtHeading.setText(heading);
        txtBody.setText(body);
        txtDone.setText(SUCCESSVALUE);
        dialog.show();

        txtDone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (closeChildActivity) {
                    finish();
                    closeChildActivity = false;
                }
            }
        });
    }

    public void CustomDailog(Context context, String heading, String body) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_layout);

        dialog.setCancelable(CANCELLABLE);

        txtHeading = (TextView) dialog.findViewById(R.id.popheading);
        txtBody = (TextView) dialog.findViewById(R.id.popbody);
        txtDone = (TextView) dialog.findViewById(R.id.popclose);

        txtHeading.setText(heading);
        txtBody.setText(body);
        txtDone.setText(SUCCESSVALUE);
        dialog.show();

        txtDone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (closeChildActivity) {
                    finish();
                    closeChildActivity = false;
                }
            }
        });
    }
}
