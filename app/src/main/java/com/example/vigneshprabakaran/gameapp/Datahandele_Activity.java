package com.example.vigneshprabakaran.gameapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.ByteArrayOutputStream;

/**
 * Created by vigneshprabakaran on 22-09-2015.
 */
public class Datahandele_Activity extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dukdb.db";
    public static final String IMAGE_TABLE_NAME = "image_table";
    public static final String CREATE_TABLE="create table "+IMAGE_TABLE_NAME+"(image_file blob)";
    public  static  final String INSET_VALUE="insert into "+IMAGE_TABLE_NAME+"(image_file)value("+R.id.image+")";
    public  static  final  String SELECT_DB="select * from "+IMAGE_TABLE_NAME;

       // Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.images);

        //Bitmap b= BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
    //    b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] img = bos.toByteArray();

    public Datahandele_Activity(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CREATE_TABLE);
        System.out.println("Created Sucesssfully");
        db.execSQL("INSERT INTO "+IMAGE_TABLE_NAME+"(image_file) Values ("+R.id.image+")");
        System.out.println("Inserted Sucesssfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
   public String getImage()
   {
       SQLiteDatabase db=this.getReadableDatabase();
       Cursor res =  db.rawQuery(SELECT_DB, null);
       System.out.println("db value" + res);
       return String.valueOf((res));

   }


}
