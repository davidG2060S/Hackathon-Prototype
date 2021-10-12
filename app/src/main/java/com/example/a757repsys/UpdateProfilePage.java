package com.example.a757repsys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfilePage extends AppCompatActivity {

    ImageView backBtn5, viewUIUserInfo, updateprofilebtn;
    TextView viewFN, viewLN, viewEmail, viewContactNo, viewAddress,
            viewUIUpdateProfile, viewUpdateInfo;
    EditText etxtFN, etxtLN, etxtEmail, etxtContactNo, etxtAddress;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DocumentReference Docref;
    Map<String, Object> reportDetails = new HashMap<>();
    String uid,nEmail, dispEmail,nFname,nLname,nContact,nAddress;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = mAuth.getCurrentUser().getUid();
        Docref= db.collection("Users").document(uid);

        setContentView(R.layout.activity_update_profile_page);

        backBtn5 = findViewById(R.id.imgBackBtn5);

        viewUIUserInfo = findViewById(R.id.imgViewUserInformation3);
        updateprofilebtn = findViewById(R.id.imgViewUIUpdateProfileBtn);
        viewFN = findViewById(R.id.txtViewUIFirstName);
        viewLN = findViewById(R.id.txtViewUILastName);
        viewEmail = findViewById(R.id.txtViewUIEmail);
        viewContactNo = findViewById(R.id.txtViewUIContactNo);
        viewAddress = findViewById(R.id.txtViewUIAddress);
        viewUIUpdateProfile = findViewById(R.id.txtViewUIUpdateProfile);
        viewUpdateInfo = findViewById(R.id.txtViewUIUpdateProfile2);
        //EDITTEXTS
        etxtFN = findViewById(R.id.editTxtUIFirstName);
        etxtLN = findViewById(R.id.editTxtUILastName);
        etxtEmail = findViewById(R.id.editTxtUIEmail);
        etxtContactNo = findViewById(R.id.editTxtUIContactNo);
        etxtAddress = findViewById(R.id.editTxtUIAddress);

        dispEmail = user.getEmail();

        backBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateProfilePage.this,
                        ProfilePage.class));
                finish();
            }
        });

        backBtn5.setOnTouchListener((v, event) -> {

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



        updateprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nEmail=etxtEmail.getText().toString();
                nFname = etxtFN.getText().toString().trim();
                nLname = etxtLN.getText().toString().trim();
                nContact = etxtContactNo.getText().toString().trim();
                nAddress = etxtAddress.getText().toString().trim();

                if(nEmail.length()==0)
                {
                    etxtEmail.requestFocus();
                    etxtEmail.setError("FIELD CANNOT BE EMPTY");
                    return;
                }
                if(nFname.length()==0)
                {
                    etxtFN.requestFocus();
                    etxtFN.setError("FIELD CANNOT BE EMPTY");
                }else if(!nFname.matches("[a-zA-Z ]+")) {
                    etxtFN.requestFocus();
                    etxtFN.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                    return;
                }
                if(nLname.length()==0)
                {
                    etxtLN.requestFocus();
                    etxtLN.setError("FIELD CANNOT BE EMPTY");
                }else if(!nLname.matches("[a-zA-Z ]+")) {
                    etxtLN.requestFocus();
                    etxtLN.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                    return;
                }
                if(etxtAddress.length()==0)
                {
                    etxtAddress.requestFocus();
                    etxtAddress.setError("FIELD CANNOT BE EMPTY");
                    return;
                }
                if(nContact.length()==0)
                {
                    etxtContactNo.requestFocus();
                    etxtContactNo.setError("FIELD CANNOT BE EMPTY");
                    return;
                }


                updateEmail();

            }
        });

        updateprofilebtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

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
            }
        });

    }
    public void updateprof(){
        dispEmail = user.getEmail();
        reportDetails.put("FirstName", nFname);
        reportDetails.put("LastName", nLname);
        reportDetails.put("Email", nEmail);
        reportDetails.put("Contact", nContact);
        reportDetails.put("Address", nAddress);

        Docref.update(reportDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });

    }

    public void updateEmail(){

        user.updateEmail(nEmail)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),
                                "Email and Profile details Updated",
                                Toast.LENGTH_SHORT).show();
                        updateprof();
                        startActivity(new Intent(UpdateProfilePage.this,
                                ProfilePage.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Email and Profile details not Updated",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*public void updateEmail(){
        nEmail=etxtEmail.getText().toString();

        user.updateEmail(nEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),
                                "Email and Profile details Updated",
                                Toast.LENGTH_SHORT).show();
                        updateprof();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Email and Profile details not Updated",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}