package com.example.task2_poe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAme = "Login.db";
    Context context;

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInByte;

    public DBHelper( Context context) {
        super(context, "Login.db", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {


        try{
            MyDB.execSQL("create table users(Email TEXT primary key,password TEXT)");
            MyDB.execSQL("create table imageInfo(imageName TEXT,Images blob)");
            MyDB.execSQL("create table category(ID INTEGER primary key,Name TEXT)");
        }catch (Exception e){

            Toast.makeText(context, "Table created successfully inside our database", Toast.LENGTH_SHORT).show();
        }
    }

//Dropping the table if they exist
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists users");
        MyDB.execSQL("drop table if exists imageInfo");
        MyDB.execSQL("drop table if exists category");
        onCreate(MyDB);
    }
///////////////////////////////Inserting values inside the users Table//////////////////////////////////////////////
    public boolean insertData(String email, String password){
      SQLiteDatabase MyDB = this.getWritableDatabase();
   ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);

        contentValues.put("password",password);
        long result = MyDB.insert("users",null,contentValues);

        if (result==-1) return false;
     else
    return true;

    }

    /////////////////Checking the user Email
    public boolean checkUserEmail(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery("Select * from users where email=?",new String[]{email});
        if (cursor.getCount()>0)
            return true;
        else
            return false;

    }

    /////////////////Checking the user Email and password
    public boolean checkUserPassword(String email,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("Select * from users where email=? and password=?",new String[]{email,password});
        if (cursor.getCount()>0)
            return true;
            else
                return false;

    }


/////////////////////////////Inserting values inside the category Table////////////////////////////////////////////////
    public boolean addData(String Name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",Name);
        long result = MyDB.insert("category",null,contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    public Cursor getListContents(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor data = MyDB.rawQuery("SELECT * FROM category", null);
        return data;

    }


    /////////////////////////////////////////////////////////////

//////Inserting values inside the ImageInfo Table

    public  void storeImage(ModelClass objectModelClass)
    {
        try{
            SQLiteDatabase objectSqLiteDatabase = this.getWritableDatabase();
            Bitmap imageToStoreBitmap=objectModelClass.getImage();
            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);
            imageInByte = objectByteArrayOutputStream.toByteArray();
            ContentValues objectContentValues=new ContentValues();
            objectContentValues.put("imageName",objectModelClass.getImageName());
            objectContentValues.put("images",imageInByte);

            long checkIfQueryRuns=objectSqLiteDatabase.insert("imageInfo",null,objectContentValues);
            if(checkIfQueryRuns!=-1){
                Toast.makeText(context, "Data added into our table", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Fails to add data", Toast.LENGTH_SHORT).show();
 objectSqLiteDatabase.close();
            }
                
        }catch (Exception e){

            Toast.makeText(context, "Table created successfully inside our database", Toast.LENGTH_SHORT).show();
        }
    }

//////////////////////Fetching the values///////////////////////////////

    public ArrayList<ModelClass> getAllImagesData(){

        try{
            SQLiteDatabase objectSqLiteDatabase=this.getWritableDatabase();
            ArrayList<ModelClass> ObjectModelClassList= new ArrayList<>();
            Cursor objectCursor= objectSqLiteDatabase.rawQuery("select * from imageInfo",null);

            if(objectCursor.getCount()!=0){
                while (objectCursor.moveToNext()){
                    String nameOfImage=objectCursor.getString(0);
                    byte[] imageBytes= objectCursor.getBlob(1);

                    Bitmap objectBitmap= BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                    ObjectModelClassList.add(new ModelClass(nameOfImage,objectBitmap));
                }
                return ObjectModelClassList;
            }else{
                Toast.makeText(context, "No values exists in the Database", Toast.LENGTH_SHORT).show();
           return null;
            }

        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        return null;
        }

    }

}
