package com.taasa.cardcontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ViewRegisters extends AppCompatActivity {

    private List<StudentModel> listStudents = new ArrayList<StudentModel>();
    private ArrayAdapter<StudentModel> studentModelArrayAdapter;
    private ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private void initComponents(){
        listView = findViewById(R.id.listv_data);
    }

    private void initDatabase(){
        FirebaseApp.initializeApp(this);
        database = DBConnectionHelper.fireDB("https://maindatabase-345104-default-rtdb.firebaseio.com/");
        reference = database.getReference();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registers);
        initComponents();
        initDatabase();
        listData();
    }

    private void listData() {
        reference.child("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listStudents.clear();
                for(DataSnapshot obj : snapshot.getChildren()){
                    StudentModel model = obj.getValue(StudentModel.class);
                    listStudents.add(model);

                    studentModelArrayAdapter = new ArrayAdapter<>(ViewRegisters.this, android.R.layout.simple_list_item_1, listStudents);
                    listView.setAdapter(studentModelArrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}