package com.example.task2_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Item_Target extends AppCompatActivity {

    EditText goal, startDate;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_target);

        goal = findViewById(R.id.edtGoalSet);
        startDate = findViewById(R.id.edtDateSet);

        back = findViewById(R.id.btnBack);

        Intent i = getIntent();

        int GoalSet = i.getIntExtra("goal",0);
        String dateSet = i.getStringExtra("startDate");

        goal.setText(String.valueOf(GoalSet));
        startDate.setText(dateSet);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Item_Target.this, CatShower.class);
                startActivity(i);
            }
        });
    }
}