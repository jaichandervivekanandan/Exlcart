package com.exlcart;

import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.exlcart.interfaces.APIResult;
import com.exlcart.model.User;
import com.exlcart.service.APIService;
import com.exlcart.utility.CommonData;
import com.exlcart.utility.SessionSave;
import com.exlcart.utility.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by Saravanan on 6/30/2015.
 */
public class SignInActivity extends BaseActivity implements View.OnClickListener, APIResult {

    /* Header Textviews */
    private TextView txtSignIn;
    private TextView butBack;
    private TextView txtTitle;

    /* USER fields */
    private EditText edtUserID;
    private EditText edtPassword;

    /* Form submission */
    private TextView butLogin;


    @Override
    public int setLayout() {
        return R.layout.signin_layout;
    }

    @Override
    public void Initialize() {
        setCustomHeader();

        butLogin = (TextView) findViewById(R.id.butLogin);
        txtSignIn = (TextView) findViewById(R.id.txtSignInStmt);
        edtUserID = (EditText) findViewById(R.id.edtUserId);
        edtPassword = (EditText) findViewById(R.id.edtPwd);

        /*setting spannable textview*/
        Spannable wordtoSpan = new SpannableString(getResources().getString(R.string.dnthaveacc));
        wordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.orangeClr)), 23, 35, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtSignIn.setText(wordtoSpan);


        butBack.setOnClickListener(this);
        txtSignIn.setOnClickListener(this);
        butLogin.setOnClickListener(this);
    }

    /*Initial header */
    private void setCustomHeader() {
        butBack = (TextView) findViewById(R.id.butBack);
        txtTitle = (TextView) findViewById(R.id.txtHeading);
        txtTitle.setText(getResources().getString(R.string.signin));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtSignInStmt:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;

            case R.id.butBack:
                finish();
                break;

            case R.id.butLogin:
                String userID = edtUserID.getText().toString().trim();
                String userPwd = edtPassword.getText().toString().trim();
                if (userID.length() == 0) {
                    edtUserID.setError(getString(R.string.strVaildUserID));
                }else if(!Utils.validdmail(userID)){
                    edtUserID.setError(getString(R.string.strVaildEmail));
                } else if (userPwd.length() == 0) {
                    edtPassword.setError(getString(R.string.strValidPassword));
                }
                else {
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("username", userID);
                    params.put("password", userPwd);
                    new APIService().requestingWebService(this, CommonData.METHODPOST, "login", this, params);

                }
                break;
        }
    }

    /**
     * API RESPONSE
     *
     * @param isSuccess - boolean for success/failure response from Webservice
     * @param result - json/ web response from web service
     */
    @Override
    public void getResult(boolean isSuccess, String result) {
        JSONObject jsonObject;
        try {
            if (isSuccess) {
                jsonObject = new JSONObject(result);
                if(jsonObject.getString("status").equals(CommonData.STATUS_SUCCESS_CODE)){
                    jsonObject = jsonObject.getJSONObject("customer");
                    SessionSave.saveSession(CommonData.KEY_USER_ID,jsonObject.getString("customer_id"),getApplicationContext());
                    SessionSave.saveSession(CommonData.KEY_EMAIL,jsonObject.getString("email"),getApplicationContext());
                    SessionSave.saveSession(CommonData.KEY_FIRST_NAME,jsonObject.getString("firstname"),getApplicationContext());
                    SessionSave.saveSession(CommonData.KEY_LAST_NAME,jsonObject.getString("lastname"),getApplicationContext());
                    SessionSave.saveSession(CommonData.KEY_TELEPHONE,jsonObject.getString("telephone"),getApplicationContext());
                    SessionSave.saveSession(CommonData.KEY_NEWSLETTER, jsonObject.getString("newsletter"), getApplicationContext());
                    SessionSave.saveSession(CommonData.KEY_FAX, jsonObject.getString("fax"), getApplicationContext());



                    CustomDailog(this, getString(R.string.strSuccess), "Successfully Logged In", getString(R.string.strDone));

                    //To-Do show response from api message
                    //CustomDailog(this, getString(R.string.strSuccess), jsonObject.getString("message"), getString(R.string.strDone));
                    closeChildActivity = false;
                /* Super class listener */
                    txtDone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent intentLaunch = new Intent(SignInActivity.this,HomeActivity.class);
                            startActivity(intentLaunch);
                            finish();
                        }
                    });
                }else {
                    jsonObject = new JSONObject(result);
                    CustomDailog(this, getString(R.string.strNotice),
                            jsonObject.getString("message"), getString(R.string.strOk));
                }
            }else{
                CustomDailog(this, getString(R.string.strNotice),"SOMETHING WENT WRONG, PLEASE TRY AGAIN...!", getString(R.string.strOk));
            }
        }catch(Exception ex){
            Log.d(TAG, ex.getMessage());
            /* IF there is no valid/ Invalid resposne */
            CustomDailog(this, getString(R.string.strNotice),"SOMETHING WENT WRONG, PLEASE TRY AGAIN...!", getString(R.string.strOk));
        }
    }
}