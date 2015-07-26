package com.exlcart.interfaces;

import retrofit.http.FormUrlEncoded;

/**
 * Created by Saravanan on 7/1/2015.
 */
public interface APIResult {
    void getResult(boolean isSuccess, String result);
}
