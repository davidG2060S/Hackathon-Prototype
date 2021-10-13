package com.example.a757repsys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpPage extends AppCompatActivity {

    ImageView backbtn1, repsys, registerbtn;
    TextView header, emailView, fnView, lnView, contactView, addView, passView, confirmpassView,
            registerView, txtViewTandC;
    EditText email, fn, ln, contact, add, pass, confirmpass;
    CheckBox box;

    String Fname,tempFname;
    String Lname,tempLname;
    String Contact,tempContact;
    String Add,tempAdd;
    String conpass,tempconpass;
    String tempemail,tempemail2;
    String password,temppassword;

    //FIREBASE
    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpPage";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        mAuth = FirebaseAuth.getInstance();

        backbtn1 = findViewById(R.id.imgBackBtn1);
        repsys = findViewById(R.id.imgViewRepSys);
        registerbtn = findViewById(R.id.imgViewRegisterBtn);
        header = findViewById(R.id.txtViewCreateAcc);
        emailView = findViewById(R.id.txtViewEmailAdd);
        fnView = findViewById(R.id.txtViewFirstName);
        lnView = findViewById(R.id.txtViewLastName);
        contactView = findViewById(R.id.txtViewContactNo);
        addView = findViewById(R.id.txtViewAddress);
        passView = findViewById(R.id.txtViewPassword);
        confirmpassView = findViewById(R.id.txtViewSignUpPassword2);
        registerView = findViewById(R.id.txtViewRegister);

        email = findViewById(R.id.editTxtSignUpEmailAdd);
        fn = findViewById(R.id.editTxtFirstName);
        ln = findViewById(R.id.editTxtLastName);
        contact = findViewById(R.id.editTextContactNo);
        add = findViewById(R.id.editTxtAddress);
        pass = findViewById(R.id.editTxtSignUpPassword);
        confirmpass = findViewById(R.id.editTxtSignUpConfirmPassword);
        txtViewTandC = findViewById(R.id.txtViewTermsAndConditions);
        box = findViewById(R.id.chkBxTermsAndConditions);
//FIREBASE






        backbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpPage.this,LoginScreen.class));
                finish();
            }
        });

        backbtn1.setOnTouchListener((v, event) -> {

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



        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                conpass = confirmpass.getText().toString();
                tempemail = email.getText().toString();
                password = pass.getText().toString();

                Fname = fn.getText().toString();

                Lname = ln.getText().toString();

                Contact = contact.getText().toString();

                Add = add.getText().toString();

                if(email.length()==0)
                {
                    email.requestFocus();
                    email.setError("FIELD CANNOT BE EMPTY");
                    return;
                }
                if(Fname.length()==0)
                {
                    fn.requestFocus();
                    fn.setError("FIELD CANNOT BE EMPTY");
                }else if(!Fname.matches("[a-zA-Z ]+")) {
                    fn.requestFocus();
                    fn.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                if(Lname.length()==0)
                {
                    ln.requestFocus();
                    ln.setError("FIELD CANNOT BE EMPTY");
                }else if(!Lname.matches("[a-zA-Z ]+")) {
                    ln.requestFocus();
                    ln.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }

                if(Contact.length()==0)
                {
                    contact.requestFocus();
                    contact.setError("FIELD CANNOT BE EMPTY");
                }else if(!Contact.matches("[0-9 ]+"))
                {
                    contact.requestFocus();
                    contact.setError("ENTER ONLY NUMERICAL NUMBERS");
                }

                if(Add.length()==0)
                {
                    add.requestFocus();
                    add.setError("FIELD CANNOT BE EMPTY");
                }
                if(password.length()==0||password.length()<=5)
                {
                    pass.requestFocus();
                    pass.setError("MUST BE 6 CHARACTERS OR LONGER");
                    return;
                }
                if(conpass.length()==0)
                {
                    confirmpass.requestFocus();
                    confirmpass.setError("FIELD CANNOT BE EMPTY");
                    return;
                }

                if (conpass.equals(password)){
                    registerUser(tempemail,password);
                }else {
                    Toast.makeText(getApplicationContext(), "Password Does Not Match",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        registerbtn.setOnTouchListener(new View.OnTouchListener() {
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

        txtViewTandC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpPage.this,TermsAndConditionsPage
                        .class);
                tempFname = fn.getText().toString();
                tempLname = ln.getText().toString();
                tempContact = contact.getText().toString();
                tempAdd=add.getText().toString();
                tempconpass=confirmpass.getText().toString();
                tempemail2=email.getText().toString();
                temppassword=pass.getText().toString();
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

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Intent i = getIntent();
        tempFname = i.getStringExtra("tempFname");
        tempLname = i.getStringExtra("tempLname");
        tempContact = i.getStringExtra("tempContact");
        tempAdd = i.getStringExtra("tempAdd");
        tempconpass = i.getStringExtra("tempconpass");
        tempemail2 = i.getStringExtra("tempemail2");
        temppassword = i.getStringExtra("temppassword");

        fn.setText(tempFname);
        ln.setText(tempLname);
        contact.setText(tempContact);
        add.setText(tempAdd);
        confirmpass.setText(tempconpass);
        email.setText(tempemail2);
        pass.setText(temppassword);


    }



    public void registerUser(String tempemail,String password){
        mAuth.createUserWithEmailAndPassword(tempemail,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Register:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            updateUI(user,tempemail,password);
                            startActivity(new Intent(SignUpPage.this,
                                    MainMenu1.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "Register:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser currentUser,String tempemail, String password){
        String key = currentUser.getUid();
        Map<String, Object> userdetails = new HashMap<>();
        userdetails.put("FirstName", Fname);
        userdetails.put("LastName", Lname);
        userdetails.put("Email", tempemail);
        userdetails.put("Password",password);
        userdetails.put("Contact", Contact);
        userdetails.put("Address", Add);
        db.collection("Users").document(key).set(userdetails)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Account has been Created",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to create account",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

}