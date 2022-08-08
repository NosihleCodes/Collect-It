package com.example.task2_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CollectionGoal extends AppCompatActivity {

    EditText goal, startDate, endDate, cat;
    Button save, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_goal);

        goal = findViewById(R.id.edtGoal);
        startDate = findViewById(R.id.edtStart);
        endDate = findViewById(R.id.edtEnd);
        cat = findViewById(R.id.edtCat);

        save = findViewById(R.id.btnSave);
        view = findViewById(R.id.btnViewer);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cGoal = Integer.valueOf(goal.getText().toString());
                String Start = startDate.getText().toString();
                String End = endDate.getText().toString();
                String category = cat.getText().toString();

                if( Start.equals("") || End.equals("")|| category.equals(""))
                    Toast.makeText(CollectionGoal.this,"All fields are required",Toast.LENGTH_LONG).show();
                else{
                    Toast.makeText(CollectionGoal.this,"Saved",Toast.LENGTH_LONG).show();
                }


            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(CollectionGoal.this, Item_Target.class);

                    int cGoal = Integer.valueOf(goal.getText().toString());
                    String Start = startDate.getText().toString();

                    i.putExtra("goal", cGoal);
                    i.putExtra("startDate", Start);

                    startActivity(i);

                }catch (Exception e){
                    Toast.makeText(CollectionGoal.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}