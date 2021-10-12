package com.example.a757repsys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainMenu1 extends AppCompatActivity {

    private static final String TAG = "tag";
    Button newreport, reportstatus;
    ImageView viewReport, uploadbtn, submitbtn,
            cameralogo, profilebtn, logoutbtn, imgvReview;
    TextView viewEmergency, viewReportCategory, viewLocation,
            viewReportDesc, refNumTest;
    Spinner emergency, report;
    EditText txtLocation, txtReportDesc;
    ArrayAdapter<CharSequence> adapter1, adapter2;
    String reportN,reportE;
    double LAT,LONG;
    int temp1,temp2,month;
    Calendar date = Calendar.getInstance();
    String reportImgUrl,UID;

    Geocoder geocoder;

    LocationManager locationManager;
    //FIREBASE


    FirebaseAuth mAuth;
    FirebaseFirestore db;

    Map<String, Object> reportDetails = new HashMap<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu1);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(MainMenu1.this,Manifest.permission
                .ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED&& ContextCompat
                .checkSelfPermission(MainMenu1.this,Manifest.permission
                        .ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            //ASKS PERMISSION
            ActivityCompat.requestPermissions(MainMenu1.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1
                , 1, new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                       LAT = location.getLatitude();
                        Log.d(TAG, "SUCCESS ON LATITUDE  "+LAT);
                       LONG = location.getLongitude();
                        Log.d(TAG, "SUCCESS ON LONGTITUDE  "+LONG);
                    }
                });

        geocoder=new Geocoder(this, Locale.getDefault());




        newreport = findViewById(R.id.btnNewReport);
        reportstatus = findViewById(R.id.btnReportStatus);
        viewReport = findViewById(R.id.imgViewReport);
        uploadbtn = findViewById(R.id.imgViewUploadBtn);
        imgvReview = findViewById(R.id.imgvReview);//preview

        submitbtn = findViewById(R.id.imgViewSubmitBtn);//sendreport

        cameralogo = findViewById(R.id.imgViewCameraLogo);
        profilebtn = findViewById(R.id.imgViewProfileBtn);
        logoutbtn = findViewById(R.id.imgViewLogOutBtn);

        viewEmergency = findViewById(R.id.txtViewEmergencyCategory);
        viewReportCategory = findViewById(R.id.txtViewReportCategory);

        viewLocation = findViewById(R.id.txtViewLocation);
        viewReportDesc = findViewById(R.id.txtViewReportDescription);
        emergency = findViewById(R.id.spnEmegencyCategory);
        report = findViewById(R.id.spnReportCategory);

        txtLocation = findViewById(R.id.editTxtLocation);
        txtReportDesc = findViewById(R.id.editTxtReportDescription);
        refNumTest = findViewById(R.id.editTextTestRefNumber);


        adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Emergency, android.R.layout.simple_spinner_item);
        adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Reports, android.R.layout.simple_spinner_item);

        emergency.setAdapter(adapter1);
        report.setAdapter(adapter2);
        refNumTest.setEnabled(false);

        emergency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.BLACK);
                reportE = parentView.getItemAtPosition(position).toString();
                temp1=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        report.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.BLACK);
                reportN = parentView.getItemAtPosition(position).toString();
                temp2=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UID = user.getUid();
                //GPS ACCURACY

                month =month+1+(date.get(Calendar.MONTH));

                String loc = String.valueOf(LAT)+", "+String.valueOf(LONG);
                txtLocation.setText(loc);
                String desc = txtReportDesc.getText().toString();
                //insert logic to choose one
                if (temp1==0&&temp2==0||TextUtils.isEmpty(loc)||TextUtils.isEmpty(desc)){
                    Toast.makeText(getApplicationContext(),
                            "Please Provide Essential Information",
                            Toast.LENGTH_SHORT).show();
                }else if (temp1!=0&&temp2==0){
                    sendEreport(reportImgUrl,loc,desc,UID);

                }else if (temp2!=0&&temp1==0){
                    sendNreport(reportImgUrl,loc,desc,UID);

                }else
                    Toast.makeText(getApplicationContext(),
                            "Please Select Only One Report Category",
                            Toast.LENGTH_SHORT).show();
            }
        });


        reportstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu1.this,MainMenu2.class));
                finish();
            }
        });


        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });

        uploadbtn.setOnTouchListener(new View.OnTouchListener() {
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



        submitbtn.setOnTouchListener(new View.OnTouchListener() {
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

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu1.this,ProfilePage.class));

                finish();
            }
        });

        profilebtn.setOnTouchListener(new View.OnTouchListener() {
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

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainMenu1.this,LoginScreen.class));
                finish();
            }
        });

        logoutbtn.setOnTouchListener(new View.OnTouchListener() {
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

    public void sendEreport(String imgUrl, String loc, String desc,String UID) {
        reportDetails.put("Report Location", loc);
        reportDetails.put("Report Description", desc);
        reportDetails.put("Report Category", reportE);
        reportDetails.put("Report Status","URGENT");
        reportDetails.put("Image",imgUrl);
        reportDetails.put("Admin Reply","");
        reportDetails.put("User Reply","");
        reportDetails.put("User UID",UID);
        reportDetails.put("DATE",month+"/"+
                date.get(Calendar.DAY_OF_MONTH)+"/"+date.get(Calendar.YEAR));

        String reportUID = db.collection("collection_name").document().getId();

        db.collection("Emergency Reports").document(reportUID).set(reportDetails)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),
                                "Report SENT, Reference Code: ",
                                Toast.LENGTH_SHORT).show();
                        month=0;
                        refNumTest.setEnabled(true);

                        refNumTest.setText(reportUID);

                    }
                });
    }

    public void sendNreport(String imgUrl,String loc, String desc,String UID){

        reportDetails.put("Report Location", loc);
        reportDetails.put("Report Description", desc);
        reportDetails.put("Report Category", reportN);
        reportDetails.put("Report Status","Pending");
        reportDetails.put("Image",imgUrl);
        reportDetails.put("Admin Reply","");
        reportDetails.put("User Reply","");
        reportDetails.put("User UID",UID);
        reportDetails.put("DATE",month+"/"+
                date.get(Calendar.DAY_OF_MONTH)+"/"+date.get(Calendar.YEAR));
        String reportUID = db.collection("collection_name").document().getId();

        db.collection("Normal Reports").document(reportUID).set(reportDetails)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),
                                "Report SENT, Reference Code: ",
                                Toast.LENGTH_SHORT).show();
                        month=0;
                        refNumTest.setEnabled(true);

                        refNumTest.setText(reportUID);
                    }
                });
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                String filePath = ImageFilePath.getPath(
                        MainMenu1.this,
                        selectedImage
                );
                String file_extn = filePath.substring(filePath.lastIndexOf(".") + 1);
                Log.i("Img", filePath);
                Log.i("Ext", file_extn);
                if (
                        file_extn.equals("img") ||
                        file_extn.equals("jpg") ||
                        file_extn.equals("jpeg") ||
                        file_extn.equals("gif") ||
                        file_extn.equals("png")
                ) {
                    Bitmap bmp = null;
                    try {
                        bmp = MediaStore.Images.Media.getBitmap(
                                this.getContentResolver(),
                                selectedImage
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    imgvReview.setImageResource(android.R.color.transparent);
                    imgvReview.setImageBitmap(bmp);
                    imgvReview.setVisibility(View.VISIBLE);

                    @SuppressLint("SimpleDateFormat")
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                            .format(new Date());
                    String hashstr = timeStamp + "." + (Math.random() * 100);

                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    StorageReference storageRef = storage
                            .getReferenceFromUrl("gs://repsys-14ce6.appspot.com");
                    String imgFileName = String.format("%s-%s",
                            hashstr.hashCode(),
                            file_extn
                    );

                    StorageReference sr = storageRef.child("images/" + imgFileName);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] bt = baos.toByteArray();

                    UploadTask uploadtask = sr.putBytes(bt);
                    uploadtask.addOnFailureListener(exception ->
                            Log.e("FBFx", exception.getMessage())
                    )
                            .addOnSuccessListener(taskSnapshot ->
                                    sr.getDownloadUrl().addOnSuccessListener(downloadUrl -> {
                                        reportImgUrl = downloadUrl.toString();
                                        //Log.i("ImgURL", downloadUrl.toString());
                                    })
                            );
                } else {
                    Toast.makeText(
                            MainMenu1.this,
                            "Invalid file format.",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }
        }
    }
}