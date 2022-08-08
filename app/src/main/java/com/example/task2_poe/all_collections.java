package com.example.task2_poe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class all_collections extends AppCompatActivity {
    private DBHelper objectDBHelper;
    private RecyclerView objectRecyclerView;
private Button btnImageAdd;
private Button btnGraph;

    private RVAdapter objectRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_collections);

        try {
            objectRecyclerView=findViewById(R.id.rvAllItems);
            btnImageAdd= findViewById(R.id.btnAdd);
            objectDBHelper= new DBHelper(this);

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        btnGraph = findViewById(R.id.btnGraph);

        btnGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(all_collections.this, graph_view.class);

                startActivity(i);
            }
        });
    }

    public void getData(View view){
        try{
            objectRvAdapter=new RVAdapter(objectDBHelper.getAllImagesData());
            objectRecyclerView.setHasFixedSize(true);
            objectRecyclerView.setLayoutManager(new LinearLayoutManager(all_collections.this));

            objectRecyclerView.setAdapter(objectRvAdapter);
        }catch (Exception e){
            Toast.makeText(all_collections.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}