package com.example.a757repsys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordPage extends AppCompatActivity {

    ImageView backBtn4, viewCPUserInfo, changepasswordbtn;
    TextView viewChangePassword, viewOldPasword, viewNewPassword, viewConfirmPassword,
            viewUpdatePassword;
    EditText oldpassword, newpassword, confirmpassword;
    FirebaseFirestore db;
    FirebaseUser user;
    FirebaseAuth mAuth;
    AuthCredential cred;

    Map<String, Object> userdetails = new HashMap<>();
    String uid,oldPass,newPass,conPass;
    private String compare;




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_page);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = mAuth.getCurrentUser().getUid();
        //DocumentReference Docref = db.collection("Users").document(uid);

        backBtn4 = findViewById(R.id.imgBackBtn4);
        viewCPUserInfo = findViewById(R.id.imgViewUserInformation2);
        changepasswordbtn = findViewById(R.id.imgViewCPChangePasswordBtn);
        viewChangePassword = findViewById(R.id.txtViewChangePassword);
        viewOldPasword = findViewById(R.id.txtViewCPOldPassword);
        viewNewPassword = findViewById(R.id.txtViewCPNewPassword);
        viewConfirmPassword = findViewById(R.id.txtViewCPConfirmPassword);
        viewUpdatePassword = findViewById(R.id.txtViewCPUpdatePassword);

        oldpassword = findViewById(R.id.editTxtCPOldPassword);
        newpassword = findViewById(R.id.editTxtCPNewPassword);
        confirmpassword = findViewById(R.id.editTxtCPConfirmPassword);

        db.collection("Users").document(uid).addSnapshotListener(this,
                new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value,
                                        @Nullable FirebaseFirestoreException error) {
                        if (value!=null){
                            compare = value.getString("Password");
                        }
                    }
                });
        backBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePasswordPage.this,
                        ProfilePage.class));
                finish();
            }
        });

        backBtn4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().setColorFilter(0x77000000,
                                PorterDuff.Mode.SRC_ATOP);
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


        //How to change password in FIREBASE FIrestore

        changepasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPass = newpassword.getText().toString();
                if(oldpassword.length()==0)
                {
                    oldpassword.requestFocus();
                    oldpassword.setError("FIELD CANNOT BE EMPTY");
                    return;
                }
                else if(newpassword.length()==0||newpassword.length()<=5)
                {
                    newpassword.requestFocus();
                    newpassword.setError("MUST BE 6 CHARACTERS OR LONGER");
                    return;
                }
                else if(confirmpassword.length()==0)
                {
                    confirmpassword.requestFocus();
                    confirmpassword.setError("FIELD CANNOT BE EMPTY");
                    return;
                }
                updatepassword();

            }
        });

        changepasswordbtn.setOnTouchListener((v, event) -> {

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
    }


    public void updatepassword(){
        conPass = confirmpassword.getText().toString().trim();
        newPass = newpassword.getText().toString().trim();
        oldPass = oldpassword.getText().toString().trim();
        userdetails.put("Password",newPass);
        Log.d("error","valOldpass "+oldPass);

        if (compare.equals(oldPass)&&newPass.equals(conPass)){
            String email = user.getEmail().trim();
            Log.d("error","valEmail "+ email);
            AuthCredential cred = EmailAuthProvider.getCredential(email,oldPass);
            Log.d("error","valcred "+ cred);
            user.reauthenticate(cred).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isComplete()){
                        user.updatePassword(newPass).addOnCompleteListener
                                (new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), "Password Reset ",
                                        Toast.LENGTH_SHORT).show();
                                db.collection("Users").document(uid).
                                        update(userdetails);
                                startActivity(new Intent(ChangePasswordPage.this,
                                        ProfilePage.class));
                                finish();
                            }
                        });
                    }else {
                        Toast.makeText(getApplicationContext(), "Password Not Reset ",
                                Toast.LENGTH_SHORT).show();

                    }
                }
            });
            Log.d("error","valUdetails "+ userdetails);
            Log.d("error","valUserID "+ uid);

        }else{
            Toast.makeText(getApplicationContext(), "Old Password/New Password does not Match "
                    ,Toast.LENGTH_SHORT).show();

        }

    }
    public void emailReset(String resetEmail) {
        mAuth.sendPasswordResetEmail(resetEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Please Open your Email",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}