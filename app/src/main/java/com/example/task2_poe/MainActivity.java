package com.example.task2_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText EmailAdd,password;
    Button btnRegis,btnLogIn;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmailAdd= (EditText) findViewById(R.id.et_email);
        password= (EditText) findViewById(R.id.et_psd);
        btnRegis = (Button) findViewById(R.id.btnReg);
        btnLogIn = (Button) findViewById(R.id.btnLogin);
       DB= new DBHelper(this);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= EmailAdd.getText().toString();
                String pass=password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass= DB.checkUserPassword(user,pass);
                    if (checkuserpass==true){
                        Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        openScreen();
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();

            }
        });
    }

    public void openRegister(){
        Intent intent = new Intent(this,Register_activity.class);
        startActivity(intent);
    }

    public void openScreen(){
        Intent intent = new Intent(this, Boards.class);
        startActivity(intent);
    }
}