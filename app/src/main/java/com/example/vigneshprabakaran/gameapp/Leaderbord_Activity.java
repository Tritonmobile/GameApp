package com.example.vigneshprabakaran.gameapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vigneshprabakaran on 04-09-2015.
 */
public class Leaderbord_Activity extends Activity {
    private Spinner spinner1;
ArrayList Lession_name_led;
    String leader_id_val;
    ListView lisview_leader;
    ArrayList Lession_id_;
    ProgressDialog mProgressDialog;

    String RANK="rank";
    String NAME="name";
    String TIME="time";
    ArrayList<HashMap<String, String>> arraylist1 = new ArrayList<HashMap<String, String>>();

    String url;
    Leaderboard_sub_Activity adapter1;
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

        setContentView(R.layout.leaderboard_layout);
        init();
        spinner1 = (Spinner) findViewById(R.id.leader_board);
        Bundle bb = this.getIntent().getExtras();
        Lession_id_ = (ArrayList) bb.get("it");
        lisview_leader=(ListView)findViewById(R.id.leader_listview);

        Lession_name_led = (ArrayList) bb.get("item2");

        spinner1 = (Spinner) findViewById(R.id.leader_board);
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, Lession_name_led);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String t = (String) spinner1.getItemAtPosition(position);
                System.out.println("lession" + Lession_id_.get(position));
                leader_id_val = String.valueOf(Lession_id_.get(position));
                arraylist1.clear();
                url = "http://whitehousecbe.in/demo/Duke/lessions/leader/?lession_id=" + leader_id_val;
                Leader12 leader12 = new Leader12();
                leader12.execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
        public class Leader12 extends AsyncTask<String, Void, Void> {


            protected void onPreExecute() {
                mProgressDialog = new ProgressDialog(Leaderbord_Activity.this);
                mProgressDialog.setTitle("Getting Your Leaderboard Data from Server");
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();

            }


            protected Void doInBackground(String... urls) {
                System.out.println(url);
                ServiceHandler handler = new ServiceHandler();
                String Content = handler.makeServiceCall(url, ServiceHandler.GET);
                System.out.println("values---------->" +
                        "" + Content);

                if (Content != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(Content);
                        JSONArray jp = jsonObj.getJSONArray("leader");

                        for (int i = 0; i < jp.length(); i++) {

                            HashMap<String, String> map = new HashMap<String, String>();
                            JSONObject c = jp.getJSONObject(i);
                            map.put(NAME, c.getString("name"));
                            try {
                                JSONObject phone1 = c.getJSONObject("result");

                                                              //for (int j = 0; j < phone1.length(); j++) {
                                 //   JSONObject d = phone1.getJSONObject(j);
                                    map.put(RANK, phone1.getString("rank"));
                                    map.put(TIME, phone1.getString("time"));


                                arraylist1.add(map);
                                System.out.println(i+"sasasas" + arraylist1.get(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Not Valid data from server",Toast.LENGTH_LONG).show();
                                finish();
                            }


                        }

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),"Not Valid data from server",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                        finish();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No Data from Server",Toast.LENGTH_LONG).show();
                }


                return null;
            }


            protected void onPostExecute(Void unused) {
                adapter1 = new Leaderboard_sub_Activity(Leaderbord_Activity.this, arraylist1);

                lisview_leader.setAdapter((ListAdapter) adapter1);

                mProgressDialog.dismiss();


            }
        }


    @Override
    public void onBackPressed(){
        Intent intent=new Intent(Leaderbord_Activity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}