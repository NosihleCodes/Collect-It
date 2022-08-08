package com.example.task2_poe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddEditItems extends AppCompatActivity {
 private EditText imageDetailsEt;
 private ImageView objectImageView;

 private static final int PICK_IMAGE_REQUEST=100;
 private Uri ImageFilePath;
 private Bitmap imageTostore;
 DBHelper objectDBhelper;
Button okay,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_items);

        try{
            imageDetailsEt=findViewById(R.id.edtiNameItem);
            objectImageView=findViewById(R.id.imgUpload);
            okay=findViewById(R.id.btnOk);
            cancel=findViewById(R.id.btnCancel);

            objectDBhelper = new DBHelper(this);
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }




      okay.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
storeImage();

          }
      });

      cancel.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(AddEditItems.this,Boards.class);
              startActivity(intent);
          }
      });
    }



    public  void chooseImage(View objectView){
            try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,PICK_IMAGE_REQUEST);

            }catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
                }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       try {
           super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
                 ImageFilePath= data.getData();
                 imageTostore= MediaStore.Images.Media.getBitmap(getContentResolver(),ImageFilePath);

                 objectImageView.setImageBitmap(imageTostore);
       } }catch (Exception e){
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    }

    public void storeImage(){
        try {
            if(!imageDetailsEt.getText().toString().isEmpty()&& objectImageView.getDrawable()!=null && imageTostore!=null){
            objectDBhelper.storeImage(new ModelClass(imageDetailsEt.getText().toString(),imageTostore));
        }
            else{
            Toast.makeText(this, "Please select image name and image", Toast.LENGTH_SHORT).show();
        }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void moveToShowActivity(View view){
        startActivity(new Intent(this,all_collections.class));
    }
}