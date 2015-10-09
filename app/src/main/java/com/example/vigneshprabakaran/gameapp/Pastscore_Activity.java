package com.example.vigneshprabakaran.gameapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vigneshprabakaran on 05-09-2015.
 */
public class Pastscore_Activity extends Activity {
    ListView listre;
    Pastscore_sub_Activity adapter;
    ProgressDialog mProgressDialog;

    static String STORE = "score";
    static String LESSION_NAME = "lession_name";
    static String LESSION_DATE = "lession_date";
    static String LESSION_RANK = "lession_rank";
    static String LESSION_TIME = "lession_time";
    ArrayList<HashMap<String, String>> ar;

    String url;
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
        setContentView(R.layout.pastscore_layout);
        init();
        listre = (ListView) findViewById(R.id.pastview);

        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String R_user_id = pref.getString("Log_user_id", null);
        url="http://whitehousecbe.in/demo/Duke/lessions/scores/?user_id="+ R_user_id;


        Leader1 leader1 = new Leader1();
        leader1.execute();

    }

    public class Leader1 extends AsyncTask<String, Void, Void> {

        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(Pastscore_Activity.this);
            mProgressDialog.setTitle("Getting Your Scores from Server");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

        }


        protected Void doInBackground(String... urls) {
            ServiceHandler handler = new ServiceHandler();
            String Content = handler.makeServiceCall(url, ServiceHandler.GET);
            System.out.println("values---------->" +
                    "" + Content);
            ar = new ArrayList<HashMap<String, String>>();

            if (Content != null) {
                try {
                    JSONObject jsonObj = new JSONObject(Content);
                    JSONArray jp = jsonObj.getJSONArray(STORE);

                    for (int i = 0; i < jp.length(); i++) {
                        HashMap<String, String>map = new HashMap<String, String>();
                        JSONObject c = jp.getJSONObject(i);
                        map.put("lession_name", c.getString("lession_name"));

                            JSONObject phone1 = c.getJSONObject("result");

                           // System.out.println("json+" + phone1);

//                                JSONObject d = phone1.getJSONObject(j);
                                map.put("lession_date", phone1.getString("current_date"));
                                map.put("lession_time", phone1.getString("time"));
                                map.put("lession_rank", phone1.getString("rank"));



                        ar.add(map);

                            System.out.println("Arraylist{"+i+"}"+ ar.get(i));



                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else
            {
                Toast.makeText(getApplicationContext(), "No Data from Server", Toast.LENGTH_LONG).show();
            }


                return null;
        }


        protected void onPostExecute(Void unused) {
//          int  temp=arraylist1.size();
//           for(int b=0;b<temp;b++)
//         {
//             System.out.println(" arr"+arraylist1.get(b));        }
            adapter = new Pastscore_sub_Activity(Pastscore_Activity.this, ar);
            listre.setAdapter(adapter);
            mProgressDialog.dismiss();


        }
    }
    @Override
    public void onBackPressed(){
        Intent intent=new Intent(Pastscore_Activity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
