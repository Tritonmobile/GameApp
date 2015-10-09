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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vigneshprabakaran on 19-08-2015.
 */
public class Lession_Activity extends Activity {
    int i;

    String Lesson_id;
    int a[];
    String m;
    int Button_val1;
    String Button_val2;
    Button btn;
    LinearLayout lm;
    ArrayList arrayList;
    ArrayList aa;
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
        setContentView(R.layout.lession_layout);

init();
        lm = (LinearLayout) findViewById(R.id.lession_id_layout);
        LongOperation longOperation = new LongOperation();
        longOperation.execute();

    }

    public class LongOperation extends AsyncTask<String, Void, Void> {
        JSONObject userDetails;
        private final HttpClient Client = new DefaultHttpClient();
        private String Content;
        String goal_val[]=new String[15];
        private String Error = null;
        JSONObject c;
        ProgressDialog mProgressDialog;
        ArrayList<Integer> items = new ArrayList<>();
        ArrayList<String> items2 = new ArrayList<>();

        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(Lession_Activity.this);
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
                String val="ashok";
                String Url = "";
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpPost = new HttpGet("http://whitehousecbe.in/demo/Duke/lessions" +
                        "?user_id="+R_user_id);
                try {
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity resEntity = response.getEntity();
                    Log.d("hai", "having lunch");
                    if (resEntity != null) {

                        Content = EntityUtils.toString(resEntity);
                        System.out.println("Dead line"+Content);

                    }


                } catch (ClientProtocolException e) {
                    Error = e.getMessage();
                    cancel(true);
                }

                } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"Url is Not Valid",Toast.LENGTH_SHORT).show();
                    Error = e.getMessage();
                    cancel(true);
                }
            return null;
        }

        protected void onPostExecute(Void unused) {

            if (Content != null) {
                mProgressDialog.dismiss();

                try {
                    JSONObject jsonObj = new JSONObject(Content);
                    JSONArray jp = jsonObj.getJSONArray("data");
                    //System.out.println("hai"+jp.getJSONObject(0));


                    // System.out.println("array lenth" + jj);

                    for (int i = 0; i < jp.length(); i++) {
                        {

                            JSONObject phone = jp.getJSONObject(i);
                            JSONObject phone1 = phone.getJSONObject("Goal");
                            goal_val[i] = phone1.getString("goal");
                            items.add(phone1.getInt("lession_id"));
                            JSONObject cc = phone.getJSONObject("Lesson");
                            items2.add(cc.getString("lesson_name"));
                            System.out.println(items.get(i));
                        }


                    }


                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


                    for (int j = 0; j <= jp.length() - 1; j++) {

                        btn = new Button(Lession_Activity.this);
                        params.gravity = Gravity.CENTER;
                        btn.setId(items.get(j));
                        btn.setWidth(150);
                      btn.setBackgroundColor(getResources().getColor(R.color.dukecolor));
                        btn.setTextColor(getResources().getColor(R.color.white));
                        //btn.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                        btn.setPadding(Gravity.CENTER, 10, Gravity.CENTER, 10);
                        btn.setTextSize(15);
                        params.setMargins(Gravity.CENTER, 30, 30, 30);
                        btn.setText(items2.get(j));
                        btn.setTypeface(Typeface.SERIF, Typeface.BOLD);
                        btn.setLayoutParams(params);


                        lm.addView(btn);
                        final int index = j;
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                         btn.setClickable(false);
                                Button_val1 = ((Button) v).getId();
                                Button_val2 = (String) ((Button) v).getText();
                                System.out.println("position of button " + index);
                                Lesson_id = String.valueOf(Button_val1);
                                System.out.println("Lesson_id friday nt wk" + Lesson_id);

                                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref1", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("Lesson_id", Lesson_id);
                                editor.commit();
                                SharedPreferences pref3 = getApplicationContext().getSharedPreferences("MyPref3", MODE_PRIVATE);
                                SharedPreferences.Editor editor3 = pref3.edit();
                                editor3.putString("goal_val1", String.valueOf(goal_val[index]));
                                editor3.commit();
                                System.out.println("position of button value " + goal_val[index]);
                                new Login_webservice1().execute();
                            }
                        });
                    }


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Not Valid Data from Server",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    finish();
                }

            }else
            {
                Toast.makeText(getApplicationContext(),"No Data From Server",Toast.LENGTH_LONG).show();
                finish();
            }
        }

    }

    public class Login_webservice1 extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String jsonResult = "";

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://whitehousecbe.in/demo/Duke/lessions/words");

            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("lession_id", (String.valueOf(Button_val1))));
                //nameValuePairs.add(new BasicNameValuePair("lession_name", Button_val2));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                System.out.println("@##---->" + Button_val2);
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
            } catch (ClientProtocolException e) {
                Toast.makeText(getApplicationContext(),"Nodata from Server",Toast.LENGTH_SHORT).show();
                finish();
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"Nodata from Server",Toast.LENGTH_SHORT).show();
                finish();
                e.printStackTrace();
            }
            return jsonResult;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        int a=0;
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            if (result.equals("") || result == null) {
                Toast.makeText(Lession_Activity.this, "Server connection failed", Toast.LENGTH_LONG).show();

                return;
            }
            try {
                System.out.println("===>"+result);
                JSONObject jsonObj = new JSONObject(result);
                JSONArray jp = jsonObj.getJSONArray("result");
              int de=jp.length()-1;
               System.out.println("as%%%%%--->"+jp);
                aa = new ArrayList<>();
                for (int i = 0; i <= jp.length()-1; i++) {

                    JSONObject phone2 = jp.getJSONObject(i);
                    try {


                        aa.add(phone2.get("english_word"));
                        System.out.println(aa.get(i));
                        a++;
                      aa.add( phone2.get("picture"));
                        System.out.println(phone2.get("picture"));

                        a++;
                        System.out.println(phone2.get("english_sound"));
                        aa.add(phone2.get("english_sound"));
                        a++;
                        aa.add(phone2.get("chinese_word"));
                        a++;
                        aa.add(phone2.get("pin_yin"));
                        a++;
                        aa.add(phone2.get("chinese_sound"));
                        a++;
                        System.out.println(a);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"No data from Server",Toast.LENGTH_SHORT).show();
                        finish();
                    }


                }
            } catch (JSONException e1) {
                e1.printStackTrace();
                Toast.makeText(getApplicationContext(),"No data from Server",Toast.LENGTH_SHORT).show();
                finish();

            }
           // System.out.println(aa.get(17));
            Intent intent=new Intent(Lession_Activity.this,Game_Activty.class);
            intent.putExtra("array", aa);

            startActivity(intent);
            finish();
        }

        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            try {
                while ((rLine = br.readLine()) != null) {
                    System.out.println("-------------------->outout---------------->" + rLine);
                    answer.append(rLine);

                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"No data from Server",Toast.LENGTH_SHORT).show();
                finish();    }
            return answer;
        }


    }
    @Override
    public void onBackPressed(){
        Intent intent=new Intent(Lession_Activity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
