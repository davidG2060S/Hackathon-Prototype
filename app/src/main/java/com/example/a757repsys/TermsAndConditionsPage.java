package com.example.a757repsys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TermsAndConditionsPage extends AppCompatActivity {
    ImageView backBtntc;

    String tempFname;
    String tempLname;
    String tempContact;
    String tempAdd;
    String tempconpass;
    String tempemail2;
    String temppassword;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions_page);
        backBtntc = findViewById(R.id.backBtntc);

        Intent i = getIntent();
        tempFname = i.getStringExtra("tempFname");
        tempLname = i.getStringExtra("tempLname");
        tempContact = i.getStringExtra("tempContact");
        tempAdd = i.getStringExtra("tempAdd");
        tempconpass = i.getStringExtra("tempconpass");
        tempemail2 = i.getStringExtra("tempemail2");
        temppassword = i.getStringExtra("temppassword");


        backBtntc.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    ImageView view = (ImageView) v;
                    view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    view.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {
                    ImageView view = (ImageView) v;
                    view.getDrawable().clearColorFilter();
                    view.invalidate();
                    break;
                }
            }
            return false;
        });

        backBtntc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermsAndConditionsPage.this,SignUpPage.class);
                intent.putExtra("tempFname",tempFname);
                intent.putExtra("tempLname",tempLname);
                intent.putExtra("tempContact",tempContact);
                intent.putExtra("tempAdd",tempAdd);
                intent.putExtra("tempconpass",tempconpass);
                intent.putExtra("tempemail2",tempemail2);
                intent.putExtra("temppassword",temppassword);
                startActivity(intent);
                finish();
            }
        });
    }
}