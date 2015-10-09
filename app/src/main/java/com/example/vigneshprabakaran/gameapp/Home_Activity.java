package com.example.vigneshprabakaran.gameapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by vigneshprabakaran on 22-08-2015.
 */
public class Home_Activity extends Activity
{
    TextView text;


    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home_layout);

        SharedPreferences pref2 = getSharedPreferences("MyPref", MODE_PRIVATE);
        String user_id1=pref2.getString("Log_user_id", null);
        if (!(user_id1 == null)){

            Intent i=new Intent(Home_Activity.this,MainActivity.class);
            startActivity(i);
            finish();
        }

        text=(TextView)findViewById(R.id.tv1);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Home_Activity.this,LoginActivity.class);
                startActivity(i);
                overridePendingTransition( R.anim.slide_in,R.anim.slide_out);
            }
        });
}

}
