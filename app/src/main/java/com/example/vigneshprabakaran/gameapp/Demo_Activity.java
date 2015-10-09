package com.example.vigneshprabakaran.gameapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

/**
 * Created by vigneshprabakaran on 22-09-2015.
 */
public class Demo_Activity extends Activity {
    ImageView  imageView_pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_layout);
        imageView_pro = (ImageView) findViewById(R.id.image_profile_demo);

        Datahandele_Activity dd = new Datahandele_Activity(getApplicationContext());
        String data = dd.getImage();
        System.out.println("haI" + data);
        byte [] encodeByte=Base64.decode(String.valueOf(data),Base64.NO_WRAP);
        Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        imageView_pro.setImageBitmap(bitmap);
//        byte[] imageAsBytes = Base64.decode(data.getBytes(), Base64.DEFAULT);
//        //ImageView image = (ImageView)this.findViewById(R.id.imageView1);
//        imageView_pro.setImageBitmap(
//                BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
//        );
    }
    }

