package com.taasa.cardcontrol;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CameraRegister extends AppCompatActivity {

    private Button btn_scan;
    private EditText txt_code_str;

    private void initComponents(){
        btn_scan = findViewById(R.id.btn_scan);
        txt_code_str = findViewById(R.id.txt_code);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_register);
        initComponents();

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
                txt_code_str.setText(result.getContents());
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}