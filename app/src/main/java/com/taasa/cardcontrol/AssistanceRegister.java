package com.taasa.cardcontrol;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class AssistanceRegister extends AppCompatActivity {

    private Button btn_scan_assiis;
    private TextView tt_data;
    private String[] qr_data;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private Date date = new Date();

    private void initComponents(){
        btn_scan_assiis = findViewById(R.id.btn_scan_assis);
        tt_data = findViewById(R.id.tv_data_assis);
    }

    private void initDatabase(){
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance("https://maindatabase-345104-default-rtdb.firebaseio.com/");
        reference = database.getReference().child("Assistance");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistance_register);
        initComponents();
        initDatabase();
        btn_scan_assiis.setOnClickListener(view -> {
            IntentIntegrator integrator = new IntentIntegrator(AssistanceRegister.this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            integrator.setPrompt("Lector QR");
            integrator.setCameraId(0);
            integrator.setBeepEnabled(true);
            integrator.setBarcodeImageEnabled(true);
            integrator.initiateScan();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(getApplicationContext(), "Lectura cancelada", Toast.LENGTH_SHORT).show();
            }else{
                qr_data = result.getContents().split("[,]");
                tt_data.setText(result.getContents());
                AssistanceModel model = new AssistanceModel();
                model.setStudent_id(qr_data[0]);
                model.setAssistance_date(LocalDate.now().toString());
                model.setAssistance(true);
                model.setStudent_name(qr_data[1]);
                reference.child(model.getStudent_id()).setValue(model);
            }
        }
    }
}