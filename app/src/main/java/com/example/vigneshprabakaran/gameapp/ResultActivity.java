package com.example.vigneshprabakaran.gameapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by vigneshprabakaran on 23-07-2015.
 */
public class ResultActivity extends Activity{
    TextView textView,textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;
    TextView EngStar1,EngStar2,EngStar3,EngStar4,EngStar5,EngStar6;
    ArrayList a=new ArrayList();
    String R_user_id,R_lesson_id;
    int avgg,temp1,temp2,temp3;
    ImageView Home,Again,Perform;
    int cards_valu[]=new int[6];
    int val[]=new int[10];
    int sc=0;
    int total;
    int t=0;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_layout);
        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
      R_user_id=pref.getString("Log_user_id", null);

       // System.out.println("Demo03" + demo);
        SharedPreferences pref1 = getSharedPreferences("MyPref1", MODE_PRIVATE);
       R_lesson_id=pref1.getString("Lesson_id", null);
        SharedPreferences pref3 = getSharedPreferences("MyPref3", MODE_PRIVATE);
        String goal_id12=pref3.getString("goal_val1", null);

      Home=(ImageView)findViewById(R.id.home);
        Again=(ImageView)findViewById(R.id.pan);
        Perform=(ImageView)findViewById(R.id.perfom);
         textView=(TextView)findViewById(R.id.time);
         textView1=(TextView)findViewById(R.id.score);
        textView2=(TextView)findViewById(R.id.flipse);
        textView3=(TextView)findViewById(R.id.goal);
        textView4=(TextView)findViewById(R.id.total);
        textView5=(TextView)findViewById(R.id.english1);
        textView6=(TextView)findViewById(R.id.english2);
        textView7=(TextView)findViewById(R.id.english3);
        textView8=(TextView)findViewById(R.id.english1_val1);
        textView9=(TextView)findViewById(R.id.english2_val2);
        textView10=(TextView)findViewById(R.id.english3_val3);
       // ---------------------------------->
        EngStar1=(TextView)findViewById(R.id.english1_star1);

        EngStar3=(TextView)findViewById(R.id.english1_star2);

        EngStar5=(TextView)findViewById(R.id.english1_star3);

//--------------------------------------------->
        Bundle bundle = getIntent().getExtras();
        int  count_toal= (int) bundle.get("count_toal");
        cards_valu[0]= bundle.getInt("card1");
        cards_valu[1]= bundle.getInt("card2");
        cards_valu[2]= bundle.getInt("card3");
        cards_valu[3]= bundle.getInt("card4");
        cards_valu[4]= bundle.getInt("card5");
        cards_valu[5]= bundle.getInt("card6");
        System.out.println("ssssssssssssccccccccccccooooooore"+cards_valu[0]+" "+cards_valu[1]+" "+cards_valu[2]+" "+cards_valu[3]+" "+cards_valu[4]+" "+cards_valu[5]);
        String English1=bundle.getString("English1");
        String English2=bundle.getString("English2");
        String English3=bundle.getString("English3");
        a= (ArrayList) bundle.get("ArraY");
        System.out.println("assssss" + a);
        textView5.setText(English1);
        System.out.println("firday demo "+textView5.getText().toString());
        textView6.setText(English2);
        textView7.setText(English3);
        long time=bundle.getLong("time");

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Home.startAnimation(animRotate);
                Intent intent=new Intent(ResultActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
startActivity(intent);
                finish();

            }
        });
        Again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Again.startAnimation(animRotate);

                sc=0;
                total=0;

                Intent intent=new Intent(ResultActivity.this,Game_Activty.class);
                intent.putParcelableArrayListExtra("ee", a);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                setResult(RESULT_OK, intent);

                finish();

            }
        });
        Perform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Perform.startAnimation(animRotate);

                Intent intent=new Intent(ResultActivity.this,Pastscore_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
finish();
            }
        });
System.out.println("Time in Result Activity"+time);
     // final String tt= String.format(" %d sec",TimeUnit.MILLISECONDS.toSeconds(time)
       // );
       // int sec=(int)TimeUnit.MILLISECONDS.toSeconds(time);
        total=cards_valu[0]+cards_valu[1]+cards_valu[2]+cards_valu[3]+cards_valu[4]+cards_valu[5];
System.out.println("tttttttttttttttttttttttttt"+total);
        System.out.println("tttttttttttttttttttttttttt2  "+time);
        sc=(int) (time+(2*count_toal));
        float sc1=sc;
        System.out.println("goal  score+" + sc);

        float goaltotal_percent=(((Integer.valueOf(goal_id12)/(sc1))));
        System.out.println((Integer.valueOf(goal_id12)/(40)));
        avgg= (int) (goaltotal_percent*100);
        System.out.println("goal total percent" + goaltotal_percent);

      //  System.out.println("Second for average"+sec);
        textView.setText(String.valueOf(time));
        textView1.setText(String.valueOf(sc));

        textView2.setText(String.valueOf(count_toal));
        textView3.setText(String.valueOf(goal_id12
        ));
        //textView4.setText(String.valueOf(avgg) + "%");
        if(avgg<25)
        {
            textView4.setTextColor(getResources().getColor(R.color.red));
            textView4.setText(String.valueOf(avgg) + "%");
        }else if(avgg<50 && avgg>25)
        {

            textView4.setTextColor(getResources().getColor(R.color.Oranage));
            textView4.setText(String.valueOf(avgg) + "%");
        }else if(avgg<75 && avgg>50)
        {
            textView4.setTextColor(getResources().getColor(R.color.goldered));
            textView4.setText(String.valueOf(avgg) + "%");
        }else if (avgg<99 && avgg>75)
        {
            textView4.setTextColor(getResources().getColor(R.color.lightpink));
            textView4.setText(String.valueOf(avgg) + "%");
        }
        else
        {
            textView4.setTextColor(getResources().getColor(R.color.lightblue));
            textView4.setText(String.valueOf(avgg) + "%");
        }


for(int i=0;i<=5;i++)
{
    if(!(cards_valu[i]==0))
    {
        val[t]=cards_valu[i];
        System.out.println("po------>"+val[t]);
        t++;

    }

}

float eng_val_avg=time/3;
        float goal_avg=Integer.valueOf(goal_id12)/3;
        final float  v1=  ((val[0]*2)+eng_val_avg);
        final float v2=(val[1]*2)+eng_val_avg;
       final float v3=(val[2]*2)+eng_val_avg;
        System.out.println("Va------>"+v1);
        float value1=(goal_avg/v1);
        float value2=(goal_avg/v2);
        float value3=(goal_avg/v3);
        System.out.println("Value------>"+value1);
       temp1= (int) (value1 * 100);
         temp2= (int) (value2*100);
         temp3= (int) (value3*100);
        if(temp1<25)
        {EngStar1.setBackground(getResources().getDrawable(R.drawable.onegold));
            textView8.setTextColor(getResources().getColor(R.color.red));
            textView8.setText(String.valueOf(temp1) + "%");

        }else if(temp1<50 && temp1>25)
        {
            EngStar1.setBackground(getResources().getDrawable(R.drawable.twogold));
            textView8.setTextColor(getResources().getColor(R.color.Oranage));
            textView8.setText(String.valueOf(temp1) + "%");
        }else if(temp1<75 && temp1>50)
        {
            EngStar1.setBackground(getResources().getDrawable(R.drawable.threegold));
            textView8.setTextColor(getResources().getColor(R.color.goldered));
            textView8.setText(String.valueOf(temp1) + "%");
        }else if (temp1<99 && temp1>75)
        {EngStar1.setBackground(getResources().getDrawable(R.drawable.fourgold));
            textView8.setTextColor(getResources().getColor(R.color.lightpink));
            textView8.setText(String.valueOf(temp1) + "%");
        }
        else
        {EngStar1.setBackground(getResources().getDrawable(R.drawable.fivegold));
          textView8.setTextColor(getResources().getColor(R.color.lightblue));
            textView8.setText(String.valueOf(temp1) + "%");
        }

       // ------------------------------------------->
        if(temp2<25)
        {EngStar3.setBackground(getResources().getDrawable(R.drawable.onegold));
            textView9.setTextColor(getResources().getColor(R.color.red));
            textView9.setText(String.valueOf(temp2) + "%");

        }else if(temp2<50 && temp2>25)
        {
            EngStar3.setBackground(getResources().getDrawable(R.drawable.twogold));
            textView9.setTextColor(getResources().getColor(R.color.Oranage));
            textView9.setText(String.valueOf(temp2) + "%");
        }else if(temp2<75 && temp2>50)
        {
            EngStar3.setBackground(getResources().getDrawable(R.drawable.threegold));
            textView9.setTextColor(getResources().getColor(R.color.goldered));
            textView9.setText(String.valueOf(temp2) + "%");
        }else if (temp2<99 && temp2>75)
        {EngStar3.setBackground(getResources().getDrawable(R.drawable.fourgold));
            textView9.setTextColor(getResources().getColor(R.color.lightpink));
            textView9.setText(String.valueOf(temp2) + "%");
        }
        else
        {EngStar3.setBackground(getResources().getDrawable(R.drawable.fivegold));
            textView9.setTextColor(getResources().getColor(R.color.lightblue));
            textView9.setText(String.valueOf(temp2) + "%");
        }

     //---------------------------------------------------->

        if(temp3<25)
        {EngStar5.setBackground(getResources().getDrawable(R.drawable.onegold));
            textView10.setTextColor(getResources().getColor(R.color.red));
            textView10.setText(String.valueOf(temp3) + "%");

        }else if(temp3<50 && temp3>25)
        {EngStar5.setBackground(getResources().getDrawable(R.drawable.twogold));
            textView10.setTextColor(getResources().getColor(R.color.Oranage));
            textView10.setText(String.valueOf(temp3) + "%");

        }else if(temp3<75 && temp3>50)
        {
            EngStar5.setBackground(getResources().getDrawable(R.drawable.threegold));
            textView10.setTextColor(getResources().getColor(R.color.goldered));
            textView10.setText(String.valueOf(temp3) + "%");
        }else if (temp3<99 && temp3>75)
        {
            EngStar5.setBackground(getResources().getDrawable(R.drawable.fourgold));
            textView10.setTextColor(getResources().getColor(R.color.lightpink));
            textView10.setText(String.valueOf(temp3) + "%");

        }
        else
        {EngStar5.setBackground(getResources().getDrawable(R.drawable.fivegold));
            textView10.setTextColor(getResources().getColor(R.color.lightblue));
            textView10.setText(String.valueOf(temp3) + "%");
        }
new Result_web().execute();


    }
    public class Result_web extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
            SharedPreferences pref2 = getSharedPreferences("Group_id", MODE_PRIVATE);
            String group_id=pref2.getString("Group_id", null);
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpPost = new HttpGet("http://whitehousecbe.in/demo/Duke/lessions/result/?Group_id="+group_id+"&User_id=" + R_user_id + "&Lession_id=" +
                    R_lesson_id + "&Time=" + textView.getText().toString() + "&Flips=" + textView1.getText().toString() + "&Total=" + textView2.getText().toString() +
                    "&Goal=" + textView3.getText().toString() + "&Total_avg=" + String.valueOf(avgg) + "&Word1=" +  URLEncoder.encode(textView5.getText().toString())
                    + "&Word_avg1=" + String.valueOf(temp1) + "&Word2=" +  URLEncoder.encode(textView6.getText().toString()) + "&Word_avg2=" + String.valueOf(temp2) + "&Word3="
                    + URLEncoder.encode(textView7.getText().toString()) + "&Word_avg3=" + String.valueOf(temp3));
            System .out.println("Group id in Result"+group_id);



                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity resEntity = response.getEntity();


                Log.d("hai", "having lunch");
                if (resEntity != null) {

                    String Content1 = EntityUtils.toString(resEntity);
                    System.out.println("Dead line" + Content1);

                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Server Not Available", Toast.LENGTH_LONG).show();
                cancel(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  null;
        }







    }
    @Override
    public void onBackPressed(){
        Intent intent=new Intent(ResultActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
