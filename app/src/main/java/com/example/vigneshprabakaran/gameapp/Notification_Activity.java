package com.example.vigneshprabakaran.gameapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * Created by vigneshprabakaran on 10-09-2015.
 */
public class Notification_Activity extends Activity {
    LinearLayout linearLayout;
    TextView textView;
    public  void init() {
        MainActivity m=new MainActivity();
        m.not=0;
        m.les=0;
        m.pas=0;
        m.lead=0;
        m.set=0;
        m.log=0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        init();


        linearLayout = (LinearLayout) findViewById(R.id.noti_layout);
        LongOperation2 longOperation = new LongOperation2();
        longOperation.execute();


}
    public class LongOperation2 extends AsyncTask<String, Void, Void> {
        JSONObject userDetails;
        private final HttpClient Client = new DefaultHttpClient();
        private String Content;
        String goal_val[] = new String[15];
        private String Error = null;
        JSONObject c;
        ProgressDialog mProgressDialog;
        ArrayList<String> items1= new ArrayList<>();
        ArrayList<String> items2 = new ArrayList<>();

        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(Notification_Activity.this);
            mProgressDialog.setTitle("Getting Your Lession from Server");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {
            try {
                SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
                String R_user_id = pref.getString("Log_user_id", null);
                String val = "ashok";
                String Url = "";
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
                    cancel(true);
                }

            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Url is Not Valid", Toast.LENGTH_SHORT).show();
                Error = e.getMessage();
                cancel(true);
            }
            return null;
        }

        protected void onPostExecute(Void unused) {

//            if (Content != null) {
            mProgressDialog.dismiss();

                try {
                    JSONObject jsonObj = new JSONObject(Content);
                    JSONArray jp = jsonObj.getJSONArray("data");
                    //System.out.println("hai"+jp.getJSONObject(0));


                    // System.out.println("array lenth" + jj);

                    for (int i = 0; i < jp.length(); i++) {
                        {

                            JSONObject phone = jp.getJSONObject(i);
//                            JSONObject phone1 = phone.getJSONObject("Lession");
//                            goal_val[i] = phone1.getString("goal");
//                            items.add(phone1.getInt("lession_id"));
                           JSONObject cc = phone.getJSONObject("Lesson");
                            items1.add(cc.getString("to_date"));
                            items2.add(cc.getString("lesson_name"));

                            System.out.println(items1.get(i));
                        }


                    }


                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


                    for (int j = 0; j <= jp.length() - 1; j++) {

                        textView = new TextView(Notification_Activity.this);
                        params.gravity = Gravity.CENTER;

                        textView.setWidth(150);
                        textView.setBackgroundColor(getResources().getColor(R.color.dukecolor));
                        textView.setTextColor(getResources().getColor(R.color.white));
                        //btn.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                        textView.setPadding(Gravity.CENTER, 10, Gravity.CENTER, 10);
                        textView.setTextSize(15);
                        params.setMargins(Gravity.CENTER, 30, 30, 30);
                        textView.setText("New lesson  " + "'" + items2.get(j) + "'" + "  is assigned to you. It expires on  "+"'" + items1.get(j) + "'" +".Play before it expires.");
                        textView.setTypeface(Typeface.SERIF, Typeface.BOLD);
                        textView.setLayoutParams(params);


                        linearLayout.addView(textView);
                        final int index = j;

                    }


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Not Valid Data from Server", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    finish();
                }
            }
        }
    @Override
    public void onBackPressed(){
        Intent intent=new Intent(Notification_Activity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    }