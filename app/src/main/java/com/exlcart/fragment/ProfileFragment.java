package com.exlcart.fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.exlcart.R;
import com.exlcart.interfaces.APIResult;
import com.exlcart.service.APIService;
import com.exlcart.utility.CommonData;
import com.exlcart.utility.SessionSave;
import com.exlcart.utility.Utils;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

/**
 * Created by Saravanan on 7/11/2015.
 * <p/>
 * Handling Profile Functionalities
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    TextView txtMyOrders;
    TextView txtNotificationSettings;
    TextView txtAboutUs;
    TextView txtContactUs;

    EditText editEmail;
    TextView txtChangePwd;

    EditText edtFirstName;
    EditText edtLastName;
    EditText edtPhoneNumber;
    EditText edtCompany;
    EditText edtStreetAddress;
    EditText edtCity;
    EditText edtZipCode;
    EditText edtState;

    Spinner spnCountry;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment_layout, container, false);

        txtMyOrders = (TextView) view.findViewById(R.id.txtMyOrders);
        txtNotificationSettings = (TextView) view.findViewById(R.id.txtNotificationSettings);
        txtAboutUs = (TextView) view.findViewById(R.id.txtAboutUs);
        txtContactUs = (TextView) view.findViewById(R.id.txtContactUs);

        editEmail = (EditText) view.findViewById(R.id.edtEmail);
        txtChangePwd = (TextView) view.findViewById(R.id.txtChangePwd);

        edtFirstName = (EditText) view.findViewById(R.id.edtFName);
        edtLastName = (EditText) view.findViewById(R.id.edtLName);
        edtPhoneNumber = (EditText) view.findViewById(R.id.edtPhoneNumber);
        edtCompany = (EditText) view.findViewById(R.id.edtCompany);
        edtStreetAddress = (EditText) view.findViewById(R.id.edtStreetAddress);
        edtZipCode = (EditText) view.findViewById(R.id.edtZipCode);
        edtState = (EditText) view.findViewById(R.id.edtState);

        spnCountry = (Spinner) view.findViewById(R.id.spnCountry);


        txtMyOrders.setOnClickListener(this);
        txtNotificationSettings.setOnClickListener(this);
        txtContactUs.setOnClickListener(this);
        txtAboutUs.setOnClickListener(this);
        txtChangePwd.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtChangePwd:
                changePassword();
                break;
        }
    }

    /* change password*/
    Dialog changePwdDialog;

    private void changePassword() {
        changePwdDialog = new Dialog(getActivity(), R.style.NewDialog);
        changePwdDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        changePwdDialog.setContentView(R.layout.profile_changepwd_layout);
        changePwdDialog.setCancelable(false);
        changePwdDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        changePwdDialog.show();

        TextView butDone = (TextView) changePwdDialog.findViewById(R.id.txtDone);
        TextView butCancel = (TextView) changePwdDialog.findViewById(R.id.txtCancel);
        final EditText edtEmail = (EditText) changePwdDialog.findViewById(R.id.edtEmail);
        final EditText edtPwd = (EditText) changePwdDialog.findViewById(R.id.edtPwd);
        final EditText edtConPwd = (EditText) changePwdDialog.findViewById(R.id.edtConfirmPwd);

        butDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                if (email.length() == 0 || (!Utils.validdmail(edtEmail.getText().toString()))) {
                    Toast.makeText(getActivity(), "" + getString(R.string.strVaildEmail), Toast.LENGTH_LONG).show();
                } else if (edtPwd.length() == 0) {
                    Toast.makeText(getActivity(), "" + getString(R.string.strValidPassword), Toast.LENGTH_LONG).show();
                } else if (edtConPwd.length() == 0) {
                    Toast.makeText(getActivity(), "" + getString(R.string.strValidPassword), Toast.LENGTH_LONG).show();
                } else if (!(edtPwd.getText().toString().equals(edtConPwd.getText().toString()))) {
                    Toast.makeText(getActivity(), "" + getString(R.string.strPwdConPwd), Toast.LENGTH_LONG).show();
                } else {
                    new ChangePasswordRequest(email, edtPwd.getText().toString(), edtConPwd.toString());
                }
            }
        });
        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePwdDialog.dismiss();
            }
        });

    }

    private class ChangePasswordRequest implements APIResult {

        public ChangePasswordRequest(String email, String pwd, String cPwd) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("email", email);
            hashMap.put("password", pwd);
            hashMap.put("confirm", cPwd);
            new APIService().requestingWebService(getActivity(), CommonData.METHODPOST, "change_password", this, hashMap);
        }

        @Override
        public void getResult(boolean isSuccess, String result) {
            if (isSuccess) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getInt("httpCode") == 200) {
                        changePwdDialog.dismiss();
                        Toast.makeText(getActivity(), "" + jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "" + jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {

                }
            } else {
                Toast.makeText(getActivity(), "SOMETHING WENT WRONG, PLEASE TRY AGAIN...!", Toast.LENGTH_LONG).show();
            }
        }
    }
    private class GetProfile implements APIResult{
        public GetProfile(){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("user_id", SessionSave.getSession("userID",getActivity()));
            new APIService().requestingWebService(getActivity(), CommonData.METHODPOST, "myaccount", this, hashMap);
        }
        @Override
        public void getResult(boolean isSuccess, String result) {

        }
    }

    private void setProfileData(){
        edtFirstName.setText(SessionSave.getSession(CommonData.KEY_FIRST_NAME,getActivity()));
        edtLastName.setText(SessionSave.getSession(CommonData.KEY_LAST_NAME,getActivity()));
        edtPhoneNumber.setText(SessionSave.getSession(CommonData.KEY_TELEPHONE,getActivity()));
        editEmail.setText(SessionSave.getSession(CommonData.KEY_EMAIL,getActivity()));
    }
}
