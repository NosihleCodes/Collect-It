package com.example.task2_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Boards extends AppCompatActivity {

    Button bt,btnView;
    EditText Cname;

DBHelper DB;
    ArrayList<String> arrayList;
    ArrayAdapter<String>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boards);

        Cname= (EditText)findViewById(R.id.editNm);
        bt=(Button) findViewById(R.id.btnCateg);
        btnView = (Button) findViewById(R.id.btnViewer);
        DB = new DBHelper(this);

bt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String newEntry = Cname.getText().toString();
        if(Cname.length()!= 0){
            AddData(newEntry);
            Cname.setText("");
        }else{
            Toast.makeText(Boards.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
        }

    }
});
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Boards.this, CatShower.class);
                startActivity(intent);
            }
        });
    }

    public void AddData(String newEntry) {

        boolean insertData = DB.addData(newEntry);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }

    }

