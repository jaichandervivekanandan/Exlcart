package com.exlcart;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exlcart.interfaces.APIResult;
import com.exlcart.service.APIService;
import com.exlcart.utility.CommonData;
import com.exlcart.utility.Utils;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

/**
 * Created by Saravanan on 6/24/2015.
 */
public class SignUpActivity extends BaseActivity implements View.OnClickListener,APIResult{

    TextView txtHeaderTitle;
    TextView txtHeaderBack;

    TextView txtRegister;

    EditText edtFirstName;
    EditText edtLastName;
    EditText edtPhone;
    EditText edtAddress;
    EditText edtCity;
    EditText edtPincode;
    EditText edtCountry;
    EditText edtState;
    EditText edtPwd;
    EditText edtEmail;
    EditText edtCompany;
    EditText edtFax;
    EditText edtNewsLetter;

    private CheckBox chkTermsAndConditions;

    //839418992811863
    @Override
    public int setLayout() {
        return R.layout.signup_layout;
    }

    @Override
    public void Initialize() {
        setCustomHeader();

        txtRegister = (TextView) findViewById(R.id.butRegister);

        edtFirstName = (EditText) findViewById(R.id.edtFName);
        edtLastName = (EditText) findViewById(R.id.edtLName);
        edtPhone = (EditText) findViewById(R.id.edtPhoneNumber);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtCity = (EditText) findViewById(R.id.edtCity);
        edtPincode = (EditText) findViewById(R.id.edtPin);
        edtCountry = (EditText) findViewById(R.id.edtCountry);
        edtState = (EditText) findViewById(R.id.edtState);
        edtPwd = (EditText) findViewById(R.id.edtPwd);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtCompany = (EditText) findViewById(R.id.edtCompany);
        edtFax = (EditText) findViewById(R.id.edtFax);
        edtNewsLetter = (EditText) findViewById(R.id.edtNewsLetter);


        chkTermsAndConditions = (CheckBox) findViewById(R.id.chkTermsAndConditions);
        txtRegister.setOnClickListener(this);

    }

    private void setCustomHeader(){
        txtHeaderBack = (TextView) findViewById(R.id.butBack);
        txtHeaderTitle = (TextView) findViewById(R.id.txtHeading);
        txtHeaderTitle.setText(getString(R.string.strSignUp));

        txtHeaderBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butBack:
                finish();
                break;

            case R.id.butRegister:
                submitValues();
                break;
        }
    }
    private void submitValues(){
        String firstName = edtFirstName.getText().toString();
        String lastName = edtLastName.getText().toString();
        String phoneNumber = edtPhone.getText().toString();
        String address = edtAddress.getText().toString();
        String city = edtCity.getText().toString();
        String pinCode = edtPincode.getText().toString();
        String country = edtCountry.getText().toString();
        String state = edtState.getText().toString();
        String pwd = edtPwd.getText().toString();
        String email = edtEmail.getText().toString();
        String company = edtCompany.getText().toString();
        String fax = edtFax.getText().toString();
        String newsLetter = edtNewsLetter.getText().toString();

        if (firstName.length() == 0)
            edtFirstName.setError(getString(R.string.strValidFirstName));
        else if(lastName.length() == 0)
            edtLastName.setError(getString(R.string.strValidLastName));
        else if(phoneNumber.length() == 0)
            edtPhone.setError("Enter Valid Phone Number");
        else if(address.length() == 0)
            edtAddress.setError("Enter Address..!");
        else if(city.length() == 0)
            edtCity.setError("Enter City..!");
        else if(pinCode.length() == 0)
            edtAddress.setError("Enter Pincode..!");
        else if(country.length() == 0)
            edtCountry.setError("Enter Country..!");
        else if(state.length() == 0)
            edtState.setError("Enter State..!");
        else if(pwd.length() == 0)
            edtPwd.setError("Enter Password..!");
        else if(email.length() == 0)
            edtEmail.setError("Enter Email..!");
        else if(!Utils.validdmail(email))
           edtEmail.setError("Enter vaild Email..!");
        else if(company.length() == 0)
            edtCompany.setError("Enter Company..!");
        else if(fax.length() == 0)
            edtFax.setError("Enter Fax..!");
        else if(newsLetter.length() == 0)
            edtNewsLetter.setError("Enter NewsLetter..!");
        else if(!chkTermsAndConditions.isChecked())
            CustomDailog(SignUpActivity.this,"NOTICE","PLEASE ACCEPT TERMS AND CONDITIONS");
        else{
            HashMap<String, String> postParams = new HashMap<>();
            postParams.put("firstname",firstName);
            postParams.put("lastname",lastName);
            postParams.put("phone",phoneNumber);
            postParams.put("address",address);
            postParams.put("city",city);
            postParams.put("pincode",pinCode);
            postParams.put("country",country);
            postParams.put("state",state);
            postParams.put("password",pwd);
            postParams.put("email",email);
            postParams.put("company",company);
            postParams.put("fax",fax);
            postParams.put("newsletter",newsLetter);
            new APIService().requestingWebService(this, CommonData.METHODPOST,"registration",this,postParams);

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
        if(isSuccess){
            try{
                JSONObject jsonObject = new JSONObject(result);
                String status = jsonObject.getString("status");
                if(status.equals(CommonData.STATUS_SUCCESS)){
                    CustomDailog(SignUpActivity.this,"SUCCESS",jsonObject.getString("message"),"OK");
                    closeChildActivity = true;
                }else{
                    CustomDailog(getApplicationContext(),"NOTICE",jsonObject.getString("message"),"OK");
                }
            }catch (Exception ex){

            }
        }else{
            CustomDailog(this, getString(R.string.strNotice),"SOMETHING WENT WRONG, PLEASE TRY AGAIN...!", getString(R.string.strOk));
        }
    }
}
