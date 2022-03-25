package com.taasa.cardcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_reg, btn_v_reg;

    private void initComponents(){
        btn_reg = findViewById(R.id.btn_reg);
        btn_v_reg = findViewById(R.id.btn_view_reg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        btn_reg.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Abriendo registro...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CameraRegister.class);
            startActivity(intent);
        });

        btn_v_reg.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Visualizando registro...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ViewRegisters.class);
            startActivity(intent);
        });
    }
}