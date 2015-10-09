package com.example.vigneshprabakaran.gameapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
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

/**
 * Created by vigneshprabakaran on 04-09-2015.
 */
public class Setting_Layout extends Activity {
    EditText passeditText1, passeditText2, passeditText3;
    EditText maileditText1, maileditText2, maileditText3, maileditText4;
    ImageButton passbutton, mailbutton;
    ProgressDialog mProgressDialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
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
        setContentView(R.layout.setting_layout);
       init();

        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        passeditText1 = (EditText) findViewById(R.id.old_password);
        passeditText2 = (EditText) findViewById(R.id.new_password);
        passeditText3 = (EditText) findViewById(R.id.conform_new_password);

        passbutton = (ImageButton) findViewById(R.id.seting_submit);

        maileditText1 = (EditText) findViewById(R.id.current_mail);
        maileditText2 = (EditText) findViewById(R.id.new_mail);
        maileditText3 = (EditText) findViewById(R.id.confirmnew_mail);

        mailbutton = (ImageButton) findViewById(R.id.mail_submit);


        passbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                passbutton.startAnimation(animRotate);
                if (passeditText1.getText().toString().equals("")) {
                    passeditText1.setError("Please Enter PassWord");
                } else if (passeditText2.getText().toString().equals("")) {
                    passeditText2.setError("Please Enter PassWord");
                } else if (passeditText3.getText().toString().equals("")) {
                    passeditText3.setError("Please Enter PassWord");
                } else if (!(passeditText2.getText().toString().equals(passeditText3.getText().toString()))) {
                    passeditText3.setError("PassWord does't match");
                    passeditText2.setText("");
                    passeditText3.setText("");
                } else if (passeditText2.getText().length() != 6) {
                    passeditText3.setError("Fill 6 Digit PasssWord");
                    passeditText2.setText("");
                    passeditText3.setText("");
                } else {
                    Passnet passnet = new Passnet();

                    passnet.execute();
                }


            }
        });
        mailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailbutton.startAnimation(animRotate);
                if (maileditText1.getText().toString().equals("")) {
                    maileditText1.setError("Please Enter Current Mailid");
                } else if (maileditText2.getText().toString().equals("")) {
                    maileditText2.setError("Please Enter  Mailid");
                } else if (maileditText3.getText().toString().equals("")) {
                    maileditText3.setError("Please Enter Current Mailid");
                } else if (!(maileditText1.getText().toString().matches(emailPattern))) {
                    maileditText1.setError("Please enter Valid Mailid");
                    maileditText1.setText("");
                } else if (!(maileditText2.getText().toString().matches(emailPattern))) {
                    maileditText2.setError("Please enter Valid Mailid");
                    maileditText2.setText("");
                } else if (!(maileditText3.getText().toString().matches(emailPattern))) {
                    maileditText3.setError("Please enter Valid Mailid");
                    maileditText3.setText("");
                } else if (!(maileditText2.getText().toString().equals(maileditText3.getText().toString()))) {
                    maileditText2.setError("PassWord Does't Match");
                    maileditText2.setError("PassWord Does't Match");
                    maileditText2.setText("");
                    maileditText3.setText("");
                } else {
                    Mailnet mailnet = new Mailnet();
                    mailnet.execute();
                }

            }
        });
    }

    public class Passnet extends AsyncTask<String, Void, Void> {
        int c;
        private String Content;
        private String Error = null;


        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(Setting_Layout.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Android JSON Parse Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }


        protected Void doInBackground(String... urls) {
            try {
                SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
                String R_user_id = pref.getString("Log_user_id", null);
                String u = "http://whitehousecbe.in/demo/dukeapp/pages/pwdChange/?user_id=" + R_user_id + "&current_password=" + passeditText1.getText().toString() + "&new_password=" + passeditText2.getText().toString();


                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpPost = new HttpGet("http://whitehousecbe.in/demo/Duke/pages/pwdChange/?user_id=" + R_user_id + "&current_password=" + passeditText1.getText().toString() + "&new_password=" + passeditText2.getText().toString());
                try {
                    System.out.println(u);
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity resEntity = response.getEntity();


                    //  Log.d("hai", "having lunch");
                    if (resEntity != null) {

                        Content = EntityUtils.toString(resEntity);
                        System.out.println("Ashok password" + Content);
                    }

                } catch (ClientProtocolException e) {
                    Error = e.getMessage();
                    cancel(true);
                }

            } catch (IOException e) {
                Error = e.getMessage();
                cancel(true);
            }
            return null;
        }

        protected void onPostExecute(Void unused) {
            //   System.out.println("Ashok" + Content);

            try {
                JSONObject jsonObj = new JSONObject(Content);

                JSONArray jp = jsonObj.getJSONArray("data");


                JSONObject phone = jp.getJSONObject(0);
                c = phone.getInt("loginSuccess");

            } catch (JSONException e) {
                e.printStackTrace();
            }


            mProgressDialog.dismiss();
            if (c == 0) {
                passeditText1.setText("");
                passeditText2.setText("");
                passeditText3.setText("");
                Toast.makeText(getApplicationContext(), "Invalid Old PassWord", Toast.LENGTH_LONG).show();
            } else if (c == 1) {
                passeditText1.setText("");
                passeditText2.setText("");
                passeditText3.setText("");

                Toast.makeText(getApplicationContext(), "Password Sucessfully Resetted", Toast.LENGTH_LONG).show();
            } else {
                passeditText1.setText("");
                passeditText2.setText("");
                passeditText3.setText("");
                Toast.makeText(getApplicationContext(), "Sorry....Server Having Issue", Toast.LENGTH_LONG).show();
            }
        }


    }

    public class Mailnet extends AsyncTask<String, Void, Void> {

        int c;
        private String Content;
        private String Error = null;


        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(Setting_Layout.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Android JSON Parse Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
            mProgressDialog.setCanceledOnTouchOutside(true);
        }


        protected Void doInBackground(String... urls) {
            try {
                SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
                String R_user_id = pref.getString("Log_user_id", null);
                String u = "http://whitehousecbe.in/demo/Duke/pages/emailChange/?user_id=" + R_user_id + "&current_mailid=" + maileditText1.getText().toString() + "&new_mailid=" + maileditText2.getText().toString();


                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpPost = new HttpGet("http://whitehousecbe.in/demo/Duke/pages/emailChange/?user_id=" + R_user_id + "&current_mailid=" + maileditText1.getText().toString() + "&new_mailid=" + maileditText2.getText().toString());
                try {
                    System.out.println(u);
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity resEntity = response.getEntity();


                    //  Log.d("hai", "having lunch");
                    if (resEntity != null) {

                        Content = EntityUtils.toString(resEntity);
                        System.out.println("Ashok password" + Content);
                    }

                } catch (ClientProtocolException e) {
                    Error = e.getMessage();
                    cancel(true);
                }

            } catch (IOException e) {
                Error = e.getMessage();
                cancel(true);
            }
            return null;
        }

        protected void onPostExecute(Void unused) {


            try {
                JSONObject jsonObj = new JSONObject(Content);

                JSONArray jp = jsonObj.getJSONArray("data");


                JSONObject phone = jp.getJSONObject(0);
                c = phone.getInt("loginSuccess");

            } catch (JSONException e) {
                e.printStackTrace();
            }


            mProgressDialog.dismiss();
            if (c == 0) {
                maileditText1.setText("");
                maileditText2.setText("");
                maileditText3.setText("");
                Toast.makeText(getApplicationContext(), "Invalid Old PassWord", Toast.LENGTH_LONG).show();
            } else if (c == 1) {
                maileditText1.setText("");
                maileditText2.setText("");
                maileditText3.setText("");

                Toast.makeText(getApplicationContext(), "Password Sucessfully Resetted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Sorry....Server Having Issue", Toast.LENGTH_LONG).show();
            }
        }

    }
    @Override
    public void onBackPressed(){
        Intent intent=new Intent(Setting_Layout.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
