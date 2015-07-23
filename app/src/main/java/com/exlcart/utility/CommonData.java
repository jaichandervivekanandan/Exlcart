package com.exlcart.utility;

import com.android.volley.Request;
import com.android.volley.toolbox.Volley;

/**
 * Created by Saravanan on 7/1/2015.
 */
public class CommonData {
    public static String BASE_PATH = "http://eshop.exlcart.com/api/";
    public static int METHODGET  = Request.Method.GET;
    public static int METHODPOST = Request.Method.POST;

    public static String STATUS_SUCCESS = "success";


    //Shared Pref KEY
    public static String KEY_USER_ID = "userID";
    public static String KEY_EMAIL = "email";
    public static String KEY_FIRST_NAME = "firstname";
    public static String KEY_LAST_NAME = "lastname";
    public static String KEY_TELEPHONE = "telephone";
    public static String KEY_NEWSLETTER = "newsletter";
    public static String KEY_FAX = "fax";


}
