package com.example.vigneshprabakaran.gameapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends Activity{
ImageButton Bt_lession,Bt_notification;
    ImageButton Bt_leaderboard,Bt_pastscore,Bt_setting;
    ImageView imageView;
    LinearLayout l1;
    ProgressDialog mProgressDialog;
    ArrayList<Integer> leader_id = new ArrayList<>();
    ArrayList<String> leader_name = new ArrayList<>();
    Button btn_logout;
    String url;
    int  not=0,les=0,pas=0,lead=0,set=0,log=0;
TextView textView;
    String  use1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        Bt_lession = (ImageButton) findViewById(R.id.my_play);
        Bt_notification=(ImageButton)findViewById(R.id.my_notification);
        Bt_pastscore=(ImageButton)findViewById(R.id.my_past_play);
        Bt_setting=(ImageButton)findViewById(R.id.my_setting);
        imageView=(ImageView)findViewById(R.id.vv);
        Bt_leaderboard=(ImageButton)findViewById(R.id.my_leader);
        btn_logout=(Button)findViewById(R.id.logout_mainid);
        textView=(TextView)findViewById(R.id.my_username);
//        LinearLayout l12 = (LinearLayout) findViewById(R.id.lay);
//        l12.setOnTouchListener((View.OnTouchListener) MainActivity.this);
//        l12.setTouchscreenBlocksFocus(false);
        Leader ll=new Leader();
        ll.execute();

        Bt_notification.setClickable(true);
        Bt_pastscore.setClickable(true);
        Bt_leaderboard.setClickable(true);
        Bt_setting.setClickable(true);
        Bt_lession.setClickable(true);
        btn_logout.setClickable(true);
        SharedPreferences pref1 = getSharedPreferences("User_name1", MODE_PRIVATE);
         use1= pref1.getString("user_name1", null);
        System.out.println("17------>" + use1);
textView.setText(use1.toUpperCase());
System.out.println("the count of cy id-------------------------->" + not);
     Bt_notification.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Bt_notification.startAnimation(animRotate);
             ++not;
             if (not == 1 && set == 0 && pas == 0 && lead == 0 && les == 0 && log == 0) {
                 Intent intent = new Intent(MainActivity.this, Notification_Activity.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(intent);
             }not=0;les=0;pas=0;lead=0;set=0;log=0;
         }

                 });

    btn_logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ++log;
            btn_logout.startAnimation(animRotate);
            if(not==0 && set == 0 && pas == 0 && lead==0 && les==0  && log==1) {
                SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
                String R_user_id = pref.getString("Log_user_id", null);


                url = "http://whitehousecbe.in/demo/Duke/pages/logout/?user_id=" + R_user_id;


                Logout logout = new Logout();
                logout.execute();

                SharedPreferences pref2 = getSharedPreferences("MyPref", MODE_PRIVATE);
                pref2.edit().remove("Log_user_id").commit();
                Intent intent = new Intent(MainActivity.this,
                        Home_Activity.class);

                startActivity(intent);
                finish();
            }not=0;les=0;pas=0;lead=0;set=0;log=0;
        }
    });

      Bt_lession.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ++les;
               Bt_lession.startAnimation(animRotate);

               if(not==0 && set == 0 && pas == 0 && lead==0 && les==1  && log==0) {
                   //Bt_lession.setBackground(getResources().getDrawable(R.drawable.main4));
                   Intent intent = new Intent(MainActivity.this, Lession_Activity.class);
                   startActivity(intent);
                   finish();
               }not=0;les=0;pas=0;lead=0;set=0;log=0;
           }
      });
        Bt_leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                System.out.println("please"+leader_id.get(0));
              ++lead;
                if(not==0 && set == 0 && pas == 0 && lead==1 && les==0  && log==0) {
                    Bt_leaderboard.startAnimation(animRotate);
                    Intent inte = new Intent(MainActivity.this, Leaderbord_Activity.class);
                    inte.putExtra("it", leader_id);
                    inte.putExtra("item2", leader_name);
                    startActivity(inte);
finish();
                }not=0;les=0;pas=0;lead=0;set=0;log=0;

            }
        });
        Bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++set;
                if(not==0 && set == 1 && pas == 0 && lead==0 && les==0  && log==0) {


                    Bt_setting.startAnimation(animRotate);
                    Intent intent = new Intent(MainActivity.this, Setting_Layout.class);
                    startActivity(intent);
                    finish();
                }not=0;les=0;pas=0;lead=0;set=0;log=0;
            }
        });
        Bt_pastscore = (ImageButton) findViewById(R.id.my_past_play);
        Bt_pastscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++pas;
                if(not==0 && set == 0 && pas == 1 && lead==0 && les==0  && log==0) {

                    Bt_pastscore.startAnimation(animRotate);
                    Intent intent = new Intent(MainActivity.this, Pastscore_Activity.class);
                    startActivity(intent);
                    finish();
                }not=0;les=0;pas=0;lead=0;set=0;log=0;
            }
    });
    }
    public class Leader extends AsyncTask<String, Void, Void> {

        private String Content;
        private String Error = null;


        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Getting Your LeaderBoard from Server");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

        }


        protected Void doInBackground(String... urls) {
            try {
                SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
                String R_user_id = pref.getString("Log_user_id", null);


                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpPost = new HttpGet("http://whitehousecbe.in/demo/Duke/lessions" +
                        "?user_id=" + R_user_id);
                try {


                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity resEntity = response.getEntity();


                    Log.d("hai", "having lunch");
                    if (resEntity != null) {

                        Content = EntityUtils.toString(resEntity);
                        System.out.println("Dead line" + Content);
                    }

                } catch (ClientProtocolException e) {
                    Error = e.getMessage();

                }

            } catch (IOException e) {
                Error = e.getMessage();

            }
            return null;
        }

        protected void onPostExecute(Void unused) {
            mProgressDialog.dismiss();

            try {
                JSONObject jsonObj = new JSONObject(Content);
                JSONArray jp = jsonObj.getJSONArray("data");


                for (int i = 0; i < jp.length(); i++) {

                    try {
                        JSONObject phone = jp.getJSONObject(i);
                        JSONObject phone1 = phone.getJSONObject("Lesson");

                       leader_id.add(phone1.getInt("id"));
                      //  JSONObject cc = phone.getJSONObject("lesson_name");
                        leader_name.add(phone1.getString("lesson_name"));

                        System.out.println(leader_id.get(i));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    public class Logout extends AsyncTask<String, Void, Void> {


        protected void onPreExecute() {
            System.out.println("url for logout"+url);

        }


        protected Void doInBackground(String... urls) {
            ServiceHandler handler = new ServiceHandler();
            String Content = handler.makeServiceCall(url, ServiceHandler.GET);
            System.out.println("values---------->" +
                    "" + Content);
return  null;
        }
    }
    @Override
    public void onBackPressed() {

        finish();
    }
}
