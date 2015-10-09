package com.example.vigneshprabakaran.gameapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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
 * Created by vigneshprabakaran on 27-07-2015.
 */
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class LoginActivity extends Activity {
    EditText User_name, Pass_word;
    String Use, Pass;
    String user_id;
    ProgressDialog mProgressDialog;
    String group_id;
TextView txt;
    ImageButton Login;
    long startTime = 1000;
    long interVal = 1000;
 //   CTaskDemo ctDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);


//    //    ctDemo.start();
System.out.println("ct Stratd");
        Boolean Net_Status = haveNetworkConnection();
        if (Net_Status) {
           // Toast.makeText(getApplicationContext(), "Welcome to Duke App", Toast.LENGTH_SHORT).show();
        } else {
            showAlertDialog(LoginActivity.this, "No Internet Connection",
                    "You don't have internet connection.", false);
        }

        User_name = (EditText) findViewById(R.id.user_name);
        Pass_word = (EditText) findViewById(R.id.password);
        Use = User_name.getText().toString();
        Pass = Pass_word.getText().toString();
        Login = (ImageButton) findViewById(R.id.login);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.startAnimation(animRotate);
                SharedPreferences pref1= getApplicationContext().getSharedPreferences("User_name1", MODE_PRIVATE);
                SharedPreferences.Editor editorp1 = pref1.edit();
                editorp1.putString("user_name1", User_name.getText().toString());
                editorp1.commit();
                if (User_name.getText().toString().matches("") || Pass_word.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Please Fill username & Login", Toast.LENGTH_SHORT).show();
                    User_name.setText("");
                    Pass_word.setText("");
                }
                else {
                    Login_webservice lo = new Login_webservice();
                    lo.execute();

                }

            }
        });
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        alertDialog.setIcon(R.drawable.not);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        // Showing Alert Message
        alertDialog.show();

    }

    public class Login_webservice extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(LoginActivity.this);
            mProgressDialog.setTitle("Validating Your Data from Server");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            String jsonResult = "";
     HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://whitehousecbe.in/demo/Duke/");

            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("preferred_name",User_name.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("password",Pass_word.getText().toString()));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));


                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResult;
        }




        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mProgressDialog.dismiss();
            System.out.println("Resulted Value:------------> " + result);
            if (result.equals("") || result == null) {
                Toast.makeText(LoginActivity.this, "Server connection failed", Toast.LENGTH_LONG).show();
                User_name.setText("");
                Pass_word.setText("");
                return;
            }
            int jsonResult = 0;
            try {
                jsonResult = returnParsedJsonObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jsonResult == 0) {
                Toast.makeText(LoginActivity.this, "Invalid username (or) password", Toast.LENGTH_LONG).show();
                User_name.setText("");
                Pass_word.setText("");
            }
            if (jsonResult == 1) {
                Login.setClickable(false);
             //o   Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                Intent intent;
                intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("USERNAME", Use);
                intent.putExtra("MESSAGE", "You have been successfully login");
                startActivity(intent);
                User_name.setText("");
                Pass_word.setText("");


            }
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
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            return answer;
        }

        private int returnParsedJsonObject(String result) throws JSONException {
            System.out.println("ashok zone---->Result:"+result);
            JSONObject resultObject = null;
           // JSONArray ja=new JSONArray(result);
            int returnedResult = 0;
            try {
                JSONObject object = new JSONObject(result);
                JSONArray Jarray = object.getJSONArray("data");
                JSONObject Jasonobject = Jarray.getJSONObject(0);


                returnedResult=Jasonobject.getInt("loginSuccess");
                user_id=Jasonobject.getString("user_id");
                group_id=Jasonobject.getString("group_id");
                System.out.println("Hai Group Id"+group_id);
                SharedPreferences pred= getApplicationContext().getSharedPreferences("Group_id", MODE_PRIVATE);
                SharedPreferences.Editor editorp = pred.edit();
                editorp.putString("Group_id",group_id);
                editorp.commit();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("Log_user_id",user_id);
                editor.commit();



            } catch (JSONException e) {

                e.printStackTrace();
            }
            return returnedResult;
        }
    }



        }

