package com.example.a757repsys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;



public class CompanyLogoScreen extends AppCompatActivity {

    private static final String TAG = "CompanyLogoScreen";
    ImageView companylogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_logo_screen);

        companylogo = findViewById(R.id.imgViewCompanyLogo);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(CompanyLogoScreen.this,LoadingScreen.class));
                finish();
            }
        },3000);


    }
}