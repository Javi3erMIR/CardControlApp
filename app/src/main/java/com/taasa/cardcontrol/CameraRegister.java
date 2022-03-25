package com.taasa.cardcontrol;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.Date;
import java.util.Locale;
import java.util.UUID;

public class CameraRegister extends AppCompatActivity {

    private Button btn_scan;
    private TextView tv_data;
    private DatabaseReference reference;
    private FirebaseDatabase database;
    private String[] qr_data;

    private void initComponents(){
        btn_scan = findViewById(R.id.btn_scan);
        tv_data = findViewById(R.id.tv_data);
    }

    private void initDatabase(){
        FirebaseApp.initializeApp(this);
        database = DBConnectionHelper.fireDB("https://maindatabase-345104-default-rtdb.firebaseio.com/");
        reference = database.getReference().child("Students");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_register);
        initComponents();
        initDatabase();
        btn_scan.setOnClickListener(view ->{
            IntentIntegrator integrator = new IntentIntegrator(CameraRegister.this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
            integrator.setPrompt("Lector QR");
            integrator.setCameraId(0);
            integrator.setBeepEnabled(true);
            integrator.setBarcodeImageEnabled(true);
            integrator.initiateScan();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(getApplicationContext(), "Lectura cancelada", Toast.LENGTH_SHORT).show();
            }else{
                qr_data = result.getContents().split("[,]");
                tv_data.setText(result.getContents());
                StudentModel model = new StudentModel();
                model.setStudent_id(UUID.randomUUID().toString());
                model.setStudent_name(qr_data[0]);
                model.setCurp(qr_data[1]);
                model.setDegree(Integer.parseInt(qr_data[2]));
                model.setStudent_group(qr_data[3]);
                model.setStudy_time(qr_data[4]);
                model.setValidity(qr_data[5]);
                model.setSchool_id(qr_data[6]);
                reference.child(model.getStudent_id()).setValue(model);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}