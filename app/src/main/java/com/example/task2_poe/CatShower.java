package com.example.task2_poe;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CatShower extends AppCompatActivity {

    DBHelper DB;
    ListView lv;
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_shower);
        try {
            lv = (ListView) findViewById(R.id.listView_V);
            DB = new DBHelper(this);

            //populate an ArrayList<String> from the database and then view it
            ArrayList<String> theList = new ArrayList<>();
            Cursor data=DB.getListContents();
            if (data.getCount() == 0) {
                Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
            } else {
                while (data.moveToNext()) {
                    theList.add(data.getString(1));
                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                    lv.setAdapter(listAdapter);
                }

            }
        }
            catch(Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            lv.setOnItemClickListener(this::onItemClick);

        show = findViewById(R.id.btnShow);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CatShower.this, CollectionGoal.class);
                startActivity(i);
            }
        });
        }

    private void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


        if (i == 0) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));
        } else if (i == 1) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 2) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 3) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 4) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 5) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 6) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 7) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 8) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 9) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 10) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 11) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 12) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 13) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 14) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        } else if (i == 15) {
            startActivity(new Intent(CatShower.this, AddEditItems.class));

        }
    }


}
