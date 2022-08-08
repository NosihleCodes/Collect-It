package com.example.task2_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_activity extends AppCompatActivity {

    EditText Uname,email,password,ConfirmPass;
    Button register ,login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Uname=(EditText) findViewById(R.id.et_Name);
        email=(EditText) findViewById(R.id.et_emailReg);
        password=(EditText) findViewById(R.id.et_PassReg);
        ConfirmPass=(EditText) findViewById(R.id.et_Confirm);

        register = (Button) findViewById(R.id.btnRegister);
        login = (Button) findViewById(R.id.btnLog);
        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user= Uname.getText().toString();
                String emails= email.getText().toString();
                String pass= password.getText().toString();
                String cpass= ConfirmPass.getText().toString();

                if( user.equals("") || emails.equals("") || pass.equals("")|| cpass.equals(""))
                    Toast.makeText(Register_activity.this,"Please enter all fields",Toast.LENGTH_LONG).show();
                    else {

                if(pass.equals(cpass)){
                    Boolean checkuser=DB.checkUserEmail(emails);
                    if(checkuser==false)
                    {
                        Boolean insert= DB.insertData(emails,pass);

                        if(insert==true){
                            Toast.makeText(Register_activity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Screen();
                        }else {
                            Toast.makeText(Register_activity.this, "Registered failed", Toast.LENGTH_SHORT).show();
                        } }
                    else
                    {
                        Toast.makeText(Register_activity.this, "User Already exists! please sign in", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(Register_activity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                }

                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void Screen(){
        Intent intent= new Intent(Register_activity.this,MainActivity.class);
        startActivity(intent);
    }
}