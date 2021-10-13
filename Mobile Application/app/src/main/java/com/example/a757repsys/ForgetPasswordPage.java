package com.example.a757repsys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordPage extends AppCompatActivity {

    ImageView backbtn2, resetpass, forgetpasswordbtn;
    TextView viewFP, viewEmail, viewforgetpassword;
    EditText forgetpasswordEmail;
    FirebaseAuth mAuth;
    String resetEmail;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_page);

        mAuth = FirebaseAuth.getInstance();

        backbtn2 = findViewById(R.id.imgBackBtn2);
        resetpass = findViewById(R.id.imgViewResetPassword);
        forgetpasswordbtn = findViewById(R.id.imgViewForgetPasswordBtn);
        viewFP = findViewById(R.id.txtViewForgetPassword);
        viewEmail = findViewById(R.id.txtViewFPEmailAddress);
        viewforgetpassword = findViewById(R.id.txtViewResetPassword);
        forgetpasswordEmail = findViewById(R.id.editTxtFPEmail);

        backbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPasswordPage.this,LoginScreen.class));
                finish();
            }
        });

        backbtn2.setOnTouchListener((v, event) -> {

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

        forgetpasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 resetEmail = forgetpasswordEmail.getText().toString();
                if (TextUtils.isEmpty(resetEmail)){
                    Toast.makeText(getApplicationContext(), "Please Input your Email",
                            Toast.LENGTH_SHORT).show();
                }else{
                    emailReset(resetEmail);
                    Toast.makeText(getApplicationContext(), "Email Sent",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

        forgetpasswordbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode
                                .SRC_ATOP);
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
            }
        });
    }
    public void emailReset(String resetEmail){
        mAuth.sendPasswordResetEmail(resetEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Please Open your Email",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgetPasswordPage.this,
                                    LoginScreen.class));
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Error Sending Password Reset to given Email Address",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}