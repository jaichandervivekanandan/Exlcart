package com.exlcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.exlcart.utility.SessionSave;


public class SplashActivity extends Activity {

    TextView butLetsShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        butLetsShop = (TextView) findViewById(R.id.butSplash);

        butLetsShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* checks already signed in or not  */
                if(SessionSave.getSession("userID",getApplicationContext()).length() > 0){
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}
