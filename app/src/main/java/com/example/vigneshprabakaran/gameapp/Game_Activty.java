package com.example.vigneshprabakaran.gameapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by vigneshprabakaran on 16-07-2015.
 */
public class Game_Activty extends Activity {
    List arrayList = new ArrayList<>();
    MediaPlayer mp;
    Animation shake;
    int ct = 0;
    ArrayList tt=new ArrayList();
    Timer T;
    int m;

    TextView textView_time,textView_goal;
    ArrayList arrayList1 = new ArrayList<>();
    Button butt_1, butt_2, butt_3, butt_4, butt_5, butt_6;
    public int Count_b1 = 0, Count_b2 = 0, Count_b3 = 0, Count_b4 = 0, Count_b5 = 0, Count_b6 = 0;
    int D_count = 0;
Button button_object;
    int Card1, Card2, Card3, Card4, Card5, Card6;

    String variable1,variable2,variable3,variable4,variable5,variable6;
    TextView Displycount, Display_goal;
    boolean State_button1 = false;
    boolean State_button2 = false;
    boolean State_button3 = false;
    boolean State_button4 = false;
    boolean State_button5 = false;
    boolean State_button6 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);


        Bundle bb = this.getIntent().getExtras();
        if (bb!=null) {
            System.out.println("eee11" + bb);
            arrayList1 = (ArrayList) bb.get("array");

        } else {


            System.out.println("Nw Ur In end");
    }



        textView_time = (TextView) findViewById(R.id.crono);
         textView_goal=(TextView)findViewById(R.id.goal_val_id);



        Displycount = (TextView) findViewById(R.id.display03_count);
        Displycount.setText(String.valueOf(D_count));
        Display_goal = (TextView) findViewById(R.id.Goal_id);


        SharedPreferences pref = getSharedPreferences("MyPref3", MODE_PRIVATE);
       String goal_id12=pref.getString("goal_val1", null);
       textView_goal.setText(goal_id12);
        T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_time.setText("" + ct);
                        Display_goal.setText(String.valueOf(ct + 2 * (D_count)));

                        ct++;
                    }
                });
            }
        }, 0, 1000);


        textView_time = (TextView) findViewById(R.id.crono);

        butt_1 = (Button) findViewById(R.id.button1);
        butt_2 = (Button) findViewById(R.id.button2);
        butt_3 = (Button) findViewById(R.id.button3);
        butt_4 = (Button) findViewById(R.id.button4);
        butt_5 = (Button) findViewById(R.id.button5);
        butt_6 = (Button) findViewById(R.id.button6);

        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);


        mp = new MediaPlayer();
        try {
            int le = arrayList1.size();
            System.out.println("final try---->" + le);
            System.out.println("final try---->" + arrayList1.size());
            for (int i = 0; i <= 17; i = 3 + i) {
                System.out.println(i);

                arrayList.add((String) arrayList1.get(i));


            }


        }catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"No Values are assigned for this Lesson",Toast.LENGTH_SHORT).show();
            finish();
        }

Collections.shuffle(arrayList);
        butt_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                butt_1.setText((CharSequence) arrayList.get(0));
                variable1=butt_1.getText().toString();
                ++Count_b1;
                ++D_count;
                Displycount.setText(String.valueOf(D_count));
                  button_object=butt_1;

                Display_goal.setText(String.valueOf(ct + 2 * (D_count)));

                System.out.println("but--------------->" + Count_b1);

                State_button1 = true;
               m= arrayList1.indexOf(butt_1.getText().toString());

                DownloadImageTask1 downloadImageTask1=new DownloadImageTask1();
                    downloadImageTask1.execute();


                try {
                    MediaPlayer mp1 = new MediaPlayer();
                    String tp = (String) arrayList1.get(m + 2);
                    mp1.setDataSource("http://" + tp);
                    mp1.prepare();
                    mp1.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }


                if (State_button1 && State_button2) {
                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        System.out.println("array list----[]=" + k);
                        System.out.println("ma"+arrayList1.get(k)+"ma"+butt_2.getText().toString());
                        if (arrayList1.get(k).equals(variable2)) {
                            Card1 = Count_b1 + Count_b2;
                            System.out.println("-----Crds---------->" + Card1);

                            butt_1.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_2.setClickable(false);

                            State_button1 = false;
                            State_button2 = false;


                        } else {

                            State_button1 = false;
                            State_button2 = false;

                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setText("");
                                    butt_2.setText("");
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        System.out.println("array list----[]=" + l);
                        if (arrayList1.get(l).equals(variable2)) {
                            Card1 = Count_b1 + Count_b2;
                            Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                            butt_1.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_2.setClickable(false);

                            State_button1 = false;
                            State_button2 = false;


                        } else {

                            State_button1 = false;
                            State_button2 = false;

                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setText("");
                                    butt_2.setText("");
                                }
                            }.start();
                        }


                    }


                } else if (State_button3 && State_button1) {
                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        System.out.println("array list----[]=" + k);
                        if (arrayList1.get(k).equals(variable3)) {
                            Card1 = Count_b1 + Count_b3;

                            butt_1.startAnimation(shake);
                            butt_3.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_3.setClickable(false);
                            State_button1 = false;
                            State_button3 = false;


                        } else {
//
                            State_button1 = false;
                            State_button3 = false;

                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setText("");
                                    butt_3.setText("");
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        System.out.println("array list----[]=" + l);
                        if (arrayList1.get(l).equals(variable3)) {
                            Card1 = Count_b1 + Count_b3;
                            butt_1.startAnimation(shake);
                            butt_3.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_3.setClickable(false);
                            State_button1 = false;
                            State_button3 = false;


                        } else {

                            State_button1 = false;
                            State_button3 = false;

                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setText("");
                                    butt_3.setText("");
                                }
                            }.start();
                        }


                    }

                } else if (State_button4 && State_button1
                        ) {
                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        System.out.println("array list----[]=" + k);
                        if (arrayList1.get(k).equals(variable4)) {
                            Card1 = Count_b1 + Count_b4;
                            butt_1.startAnimation(shake);
                            butt_4.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_4.setClickable(false);
                            State_button1 = false;
                            State_button4 = false;


                        } else {

                            State_button1 = false;
                            State_button4 = false;

                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setText("");
                                    butt_4.setText("");
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        System.out.println("array list----[]=" + l);
                        if (arrayList1.get(l).equals(variable4)) {
                            Card1 = Count_b1 + Count_b4;
                            butt_1.startAnimation(shake);
                            butt_4.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_4.setClickable(false);
                            State_button1 = false;
                            State_button4 = false;


                        } else {

                            State_button1 = false;
                            State_button4 = false;


                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setText("");
                                    butt_4.setText("");
                                }
                            }.start();
                        }


                    }
                } else if (State_button5 && State_button1) {
                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        System.out.println("array list----[]=" + k);
                        if (arrayList1.get(k).equals(variable5)) {
                            Card1 = Count_b1 + Count_b5;
                            butt_1.startAnimation(shake);
                            butt_5.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_5.setClickable(false);
                            State_button1 = false;
                            State_button5 = false;


                        } else {State_button5 = false;
                            State_button1=false;



                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setText("");
                                    butt_1.setText("");
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        System.out.println("array list----[]=" + l);
                        if (arrayList1.get(l).equals(variable5)) {
                            Card1 = Count_b1 + Count_b5;
                            butt_1.startAnimation(shake);
                            butt_5.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_5.setClickable(false);
                            State_button1 = false;
                            State_button5 = false;


                        } else {
                          State_button1 = false;
                            State_button5 = false;

                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setText("");
                                    butt_5.setText("");
                                }
                            }.start();
                        }


                    }


                } else if (State_button6 && State_button1) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        System.out.println("array list----[]=" + k);
                        if (arrayList1.get(k).equals(variable6)) {
                            Card1 = Count_b1 + Count_b6;
                            butt_1.startAnimation(shake);
                            butt_6.startAnimation(shake);
                            butt_1.setClickable(false);
                            butt_6.setClickable(false);
                            State_button1 = false;
                            State_button6 = false;


                        } else {

                            State_button1 = false;
                            State_button6 = false;

                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setText("");
                                    butt_6.setText("");
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        System.out.println("array list----[]=" + l);
                        if (arrayList1.get(l).equals(variable6)) {
                            Card1 = Count_b1 + Count_b6;
                            butt_1.startAnimation(shake);
                            butt_6.startAnimation(shake);
                            butt_1.setClickable(false);
                            butt_6.setClickable(false);
                            State_button6 = false;
                            State_button1 = false;


                        } else {

                            State_button1 = false;
                            State_button6 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setText("");
                                    butt_6.setText("");
                                }
                            }.start();
                        }
                    }
                } else {
                    System.out.println("----------Card1:  " + Card1 + "------------Card2:" + Card2 + "------------Card3:" + Card3 + "--------------card4:" + Card4 + "----------------card5:" + Card5 + "-------------card6:" + Card6);

                }
                if (!(butt_2.isClickable())) {
                    if (!(butt_3.isClickable())) {
                        if (!butt_4.isClickable()) {
                            if (!butt_5.isClickable()) {
                                if (!butt_6.isClickable()) {
                                    T.cancel();
                                    long elapsedMillis = Long.parseLong(textView_time.getText().toString());


                                    Intent i = new Intent(Game_Activty.this, ResultActivity.class);
                                    i.putExtra("count_toal",D_count);
                                    i.putExtra("card1", Card1);
                                    i.putExtra("card2", Card2);
                                    i.putExtra("card3", Card3);
                                    i.putExtra("card4", Card4);
                                    i.putExtra("card5", Card5);
                                    i.putExtra("card6", Card6);
                                    System.out.println("*****************************" + elapsedMillis);
                                    i.putExtra("time", elapsedMillis);
                                    i.putExtra("English1", (String) arrayList1.get(0));
                                    i.putExtra("English2", (String) arrayList1.get(6));
                                    i.putExtra("English3", (String) arrayList1.get(12));
                                   i.putExtra("ArraY", arrayList1);


                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivityForResult(i, 1);
finish();

                                }
                            }
                        }
                    }
                }
            }
        });
//------------------------>button 2----------------------------------------->
        butt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                butt_2.setText((CharSequence) arrayList.get(1));
                variable2=butt_2.getText().toString();
                ++D_count;
                ++Count_b2;
                Displycount.setText(String.valueOf(D_count));

                Display_goal.setText(String.valueOf(ct + 2 * (D_count)));

                System.out.println("but2---------------------" + Count_b2);
                button_object=butt_2;

                State_button2 = true;

                 m = arrayList1.indexOf(butt_2.getText().toString());
                System.out.println("mmmmmmmmmmmmmmmmmmmm---------->"+m+butt_2.getText().toString()+" "+arrayList1.indexOf(butt_2.getText().toString()));
            DownloadImageTask1 tt=new DownloadImageTask1();
                tt.execute();
    System.out.println("arry[]" + arrayList1.get(m + 2));
                try {
                    MediaPlayer mp1 = new MediaPlayer();
                    String tp = (String) arrayList1.get(m + 2);
                    mp1.setDataSource("http://" + tp);
                    mp1.prepare();
                    mp1.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (State_button1 && State_button2) {
                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable1)) {
                            Card2 = Count_b1 + Count_b2;
                            butt_1.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_2.setClickable(false);
                            State_button1 = false;
                            State_button2 = false;


                        } else {

                            State_button1 = false;
                            State_button2 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_2.setText("");
                                    butt_1.setText("");
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable1)) {
                            Card2 = Count_b1 + Count_b2;
                            butt_1.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_2.setClickable(false);
                            State_button1 = false;
                            State_button2 = false;


                        } else {

                            State_button1 = false;
                            State_button2 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setText("");
                                    butt_1.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button3 && State_button2) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable3)) {
                            Card2 = Count_b3 + Count_b2;
                            butt_3.startAnimation(shake);
                            butt_2.startAnimation(shake);


                            butt_2.setClickable(false);
                            butt_3.setClickable(false);
                            State_button3 = false;
                            State_button2 = false;


                        } else {

                            State_button2 = false;
                            State_button3 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_3.setText("");
                                    butt_2.setText("");
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        System.out.println(array+"+");
                        if (arrayList1.get(l).equals(variable3)) {
                            butt_3.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_3.setClickable(false);
                            butt_2.setClickable(false);
                            State_button3 = false;
                            State_button2 = false;
                            Card2 = Count_b3 + Count_b2;


                        } else {

                            State_button2 = false;
                            State_button3 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setText("");
                                    butt_2.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button4 && State_button2) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable4)) {
                            Card2 = Count_b4 + Count_b2;
                            butt_4.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_2.setClickable(false);
                            butt_4.setClickable(false);
                            State_button4 = false;
                            State_button2 = false;


                        } else {

                            State_button2 = false;
                            State_button4 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_2.setText("");
                                    butt_4.setText("");
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;

                        if (arrayList1.get(l).equals(variable4)) {
                            Card2 = Count_b4 + Count_b2;
                            butt_4.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_4.setClickable(false);
                            butt_2.setClickable(false);
                            State_button4 = false;
                            State_button2 = false;


                        } else {

                            State_button2 = false;
                            State_button4 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setText("");
                                    butt_4.setText("");
                                }
                            }.start();

                        }


                    }
                } else if (State_button5 && State_button2) {
                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable5)) {
                            Card2 = Count_b5 + Count_b2;
                            butt_5.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_2.setClickable(false);
                            butt_5.setClickable(false);
                            State_button5 = false;
                            State_button2 = false;


                        } else {

                            State_button2 = false;
                            State_button5 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_2.setText("");
                                    butt_5.setText("");
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable5)) {
                            Card2 = Count_b5 + Count_b2;
                            butt_5.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_5.setClickable(false);
                            butt_2.setClickable(false);
                            State_button5 = false;
                            State_button2 = false;


                        } else {

                            State_button2 = false;
                            State_button5 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setText("");
                                    butt_5.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button6 && State_button2) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable6)) {
                            Card2 = Count_b6 + Count_b2;
                            butt_6.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_2.setClickable(false);
                            butt_6.setClickable(false);
                            State_button6 = false;
                            State_button2 = false;


                        } else {

                            State_button2 = false;
                            State_button6 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_2.setText("");
                                    butt_6.setText("");
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable6)) {
                            Card2 = Count_b6 + Count_b2;
                            butt_6.startAnimation(shake);
                            butt_2.startAnimation(shake);
                            butt_6.setClickable(false);
                            butt_2.setClickable(false);
                            State_button6 = false;
                            State_button2 = false;


                        } else {

                            State_button2 = false;
                            State_button6 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setText("");
                                    butt_6.setText("");
                                }
                            }.start();

                        }


                    }


                } else

                {


                }
                if (!(butt_1.isClickable())) {
                    if (!(butt_3.isClickable())) {
                        if (!butt_4.isClickable()) {
                            if (!butt_5.isClickable()) {
                                if (!butt_6.isClickable()) {
                                    T.cancel();


                                    long elapsedMillis = Long.parseLong(textView_time.getText().toString());

                                    Intent i = new Intent(Game_Activty.this, ResultActivity.class);
                                    i.putExtra("count_toal",D_count);
                                    i.putExtra("card1", Card1);
                                    i.putExtra("card2", Card2);
                                    i.putExtra("card3", Card3);
                                    i.putExtra("card4", Card4);
                                    i.putExtra("card5", Card5);
                                    i.putExtra("card6", Card6);
                                    i.putExtra("English1", (String) arrayList1.get(0));
                                    i.putExtra("English2", (String) arrayList1.get(6));
                                    i.putExtra("English3", (String) arrayList1.get(12));
                                    i.putExtra("time", elapsedMillis);
                                    i.putExtra("ArraY", arrayList1);

                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivityForResult(i, 1);

                                }
                            }
                        }
                    }
                }
            }

        });
        //------------------------>button 3----------------------------------------->

        butt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butt_3.setText((CharSequence) arrayList.get(2));
                variable3=butt_3.getText().toString();
                ++Count_b3;
                button_object=butt_3;
                ++D_count;
                Displycount.setText(String.valueOf(D_count));
                 m = arrayList1.indexOf(butt_3.getText().toString());

                Display_goal.setText(String.valueOf(ct + 2 * (D_count)));
                 DownloadImageTask1 dd=new DownloadImageTask1();
                  dd.execute();
                try {

                    MediaPlayer mp1 = new MediaPlayer();
                    String tp = (String) arrayList1.get(m + 2);
                    mp1.setDataSource("http://" + tp);
                    mp1.prepare();
                    mp1.start();


                } catch (Exception e) {
                    e.printStackTrace();
                }

                State_button3 = true;
                System.out.println("but3---------------------" + Count_b3);
                if (State_button1 && State_button3) {
                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable1)) {
                            Card3 = Count_b1 + Count_b3;
                            butt_3.startAnimation(shake);
                            butt_1.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_3.setClickable(false);
                            State_button3 = false;
                            State_button1 = false;


                        } else {

                            State_button3 = false;
                            State_button1 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_3.setText("");
                                    butt_1.setText("");
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable1)) {
                            Card3 = Count_b1 + Count_b3;
                            butt_3.startAnimation(shake);
                            butt_1.startAnimation(shake);

                           butt_1.setClickable(false);
                            butt_3.setClickable(false);
                            State_button3 = false;
                            State_button1 = false;


                        } else {

                            State_button3 = false;
                            State_button1 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setText("");
                                    butt_1.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button3 && State_button2) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable2)) {
                            Card3 = Count_b2 + Count_b3;
                            butt_3.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_3.setClickable(false);
                            butt_2.setClickable(false);
                            State_button3 = false;
                            State_button2 = false;


                        } else {

                            State_button3 = false;
                            State_button2 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_3.setText("");
                                    butt_2.setText("");
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable2)) {
                            Card3 = Count_b2 + Count_b3;
                            butt_3.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_3.setClickable(false);
                            butt_2.setClickable(false);
                            State_button3 = false;
                            State_button2 = false;


                        } else {

                            State_button3 = false;
                            State_button2 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setText("");
                                    butt_2.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button4 && State_button3) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable4)) {
                            Card3 = Count_b4 + Count_b3;
                            butt_3.startAnimation(shake);
                            butt_4.startAnimation(shake);

                            butt_4.setClickable(false);
                            butt_3.setClickable(false);
                            State_button3 = false;
                            State_button4 = false;


                        } else {

                            State_button3 = false;
                            State_button4 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_3.setText("");
                                    butt_4.setText("");
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable4)) {
                            Card3 = Count_b4 + Count_b3;
                            butt_3.startAnimation(shake);
                            butt_4.startAnimation(shake);

                            butt_4.setClickable(false);
                            butt_3.setClickable(false);
                            State_button3 = false;
                            State_button4 = false;


                        } else {

                            State_button3 = false;
                            State_button4 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setText("");
                                    butt_4.setText("");
                                }
                            }.start();

                        }


                    }
                } else if (State_button5 && State_button3) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable5)) {
                            Card3 = Count_b5 + Count_b3;
                            butt_3.startAnimation(shake);
                            butt_5.startAnimation(shake);

                            butt_3.setClickable(false);
                            butt_5.setClickable(false);
                            State_button3 = false;
                            State_button5 = false;


                        } else {

                            State_button3 = false;
                            State_button5 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_3.setText("");
                                    butt_5.setText("");
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable5)) {
                            Card3 = Count_b5 + Count_b3;
                            butt_3.startAnimation(shake);
                            butt_5.startAnimation(shake);

                            butt_5.setClickable(false);
                            butt_3.setClickable(false);
                            State_button3 = false;
                            State_button5 = false;


                        } else {

                            State_button3 = false;
                            State_button5 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setText("");
                                    butt_5.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button6 && State_button3) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable6)) {
                            Card3 = Count_b6 + Count_b3;
                            butt_3.startAnimation(shake);
                            butt_6.startAnimation(shake);
                            butt_3.setClickable(false);
                            butt_6.setClickable(false);
                            State_button3 = false;
                            State_button6 = false;


                        } else {

                            State_button3 = false;
                            State_button6 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_3.setText("");
                                    butt_6.setText("");
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable6)) {
                            Card3 = Count_b6 + Count_b3;
                            butt_3.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_6.setClickable(false);
                            butt_3.setClickable(false);
                            State_button3 = false;
                            State_button6 = false;


                        } else {

                            State_button3 = false;
                            State_button6 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setText("");
                                    butt_6.setText("");
                                }
                            }.start();

                        }


                    }


                } else

                {

                    System.out.println("----------Card1:  " + Card1 + "------------Card2:" + Card2 + "------------Card3:" + Card3 + "--------------card4:" + Card4 + "----------------card5:" + Card5 + "-------------card6:" + Card6);

                }
                if (!(butt_1.isClickable())) {
                    if (!(butt_3.isClickable())) {
                        if (!butt_4.isClickable()) {
                            if (!butt_5.isClickable()) if (!butt_6.isClickable()) {
                                T.cancel();
                                long elapsedMillis = Long.parseLong(textView_time.getText().toString());


                                Intent i = new Intent(Game_Activty.this, ResultActivity.class);
                                i.putExtra("count_toal",D_count);
                                i.putExtra("card1", Card1);
                                i.putExtra("card2", Card2);
                                i.putExtra("card3", Card3);
                                i.putExtra("card4", Card4);
                                i.putExtra("card5", Card5);
                                i.putExtra("card6", Card6);
                                i.putExtra("time", elapsedMillis);
                                i.putExtra("English1", (String) arrayList1.get(0));
                                i.putExtra("English1", (String) arrayList1.get(6));
                                i.putExtra("English1", (String) arrayList1.get(12));
                                i.putExtra("ArraY", arrayList1);

                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivityForResult(i, 1);

                            }
                        }
                    }
                }
            }
        });
//---------------->button4--------------------------->

        butt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butt_4.setText((CharSequence) arrayList.get(3));
                variable4=butt_4.getText().toString();
                ++Count_b4;
                ++D_count;
                Displycount.setText(String.valueOf(D_count));
                button_object=butt_4;
                Display_goal.setText(String.valueOf(ct + 2 * (D_count)));

                 m = arrayList1.indexOf(butt_4.getText().toString());
                DownloadImageTask1 dd=new DownloadImageTask1();
                dd.execute();

                   try {
                        MediaPlayer mp1 = new MediaPlayer();
                        String tp = (String) arrayList1.get(m + 2);
                        mp1.setDataSource("http://" + tp);
                        mp1.prepare();
                        mp1.start();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                System.out.println("but4---------------------" + Count_b4);

                State_button4 = true;
                if (State_button1 && State_button4) {
                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable1)) {
                            Card4 = Count_b1 + Count_b4;
                            butt_4.startAnimation(shake);
                            butt_1.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_4.setClickable(false);
                            State_button4 = false;
                            State_button1 = false;


                        } else {

                            State_button4 = false;
                            State_button4 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_4.setText("");
                                    butt_1.setText("");
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable1)) {
                            Card4 = Count_b1 + Count_b4;
                            butt_1.startAnimation(shake);
                            butt_4.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_4.setClickable(false);
                            State_button4 = false;
                            State_button1 = false;


                        } else {

                            State_button4 = false;
                            State_button1 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setText("");
                                    butt_1.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button4 && State_button2) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable2)) {
                            Card4 = Count_b2 + Count_b4;
                            butt_2.startAnimation(shake);
                            butt_4.startAnimation(shake);

                            butt_4.setClickable(false);
                            butt_2.setClickable(false);
                            State_button4 = false;
                            State_button2 = false;

                        } else {

                            State_button4 = false;
                            State_button2 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_4.setText("");
                                    butt_2.setText("");
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable2)) {
                            Card4 = Count_b2 + Count_b4;
                            butt_1.startAnimation(shake);
                            butt_4.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_4.setClickable(false);
                            butt_2.setClickable(false);
                            State_button4 = false;
                            State_button2 = false;


                        } else {

                            State_button4 = false;
                            State_button2 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setText("");
                                    butt_2.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button4 && State_button3) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable3)) {
                            Card4 = Count_b3 + Count_b4;
                            butt_4.startAnimation(shake);
                            butt_3.startAnimation(shake);

                            butt_4.setClickable(false);
                            butt_3.setClickable(false);
                            State_button4 = false;
                            State_button3 = false;


                        } else {

                            State_button4 = false;
                            State_button3 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_4.setText("");
                                    butt_3.setText("");
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable3)) {
                            Card4 = Count_b3 + Count_b4;
                            butt_4.startAnimation(shake);
                            butt_3.startAnimation(shake);

                            butt_3.setClickable(false);
                            butt_4.setClickable(false);
                            State_button4 = false;
                            State_button3 = false;

                        } else {

                            State_button4 = false;
                            State_button3 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setText("");
                                    butt_3.setText("");
                                }
                            }.start();

                        }


                    }
                } else if (State_button5 && State_button4) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable5)) {
                            Card4 = Count_b5 + Count_b4;
                            butt_4.startAnimation(shake);
                            butt_5.startAnimation(shake);

                            butt_4.setClickable(false);
                            butt_5.setClickable(false);
                            State_button4 = false;
                            State_button5 = false;

                        } else {

                            State_button4 = false;
                            State_button5 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_4.setText("");
                                    butt_5.setText("");
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable5)) {
                            Card4 = Count_b5 + Count_b4;
                            butt_4.startAnimation(shake);
                            butt_5.startAnimation(shake);

                            butt_5.setClickable(false);
                            butt_4.setClickable(false);
                            State_button4 = false;
                            State_button5 = false;

                        } else {

                            State_button4 = false;
                            State_button5 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setText("");
                                    butt_5.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button6 && State_button4) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable6)) {
                            Card4 = Count_b6 + Count_b4;
                            butt_4.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_4.setClickable(false);
                            butt_6.setClickable(false);
                            State_button4 = false;
                            State_button6 = false;


                        } else {

                            State_button4 = false;
                            State_button6 = false;
                            new CountDownTimer(3000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_4.setText("");
                                    butt_6.setText("");
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable6)) {
                            Card4 = Count_b6 + Count_b4;
                            butt_4.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_6.setClickable(false);
                            butt_4.setClickable(false);
                            State_button4 = false;
                            State_button6 = false;

                        } else {

                            State_button4 = false;
                            State_button6 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setText("");
                                    butt_6.setText("");
                                }
                            }.start();

                        }


                    }


                } else

                {



                }

                if (!(butt_2.isClickable())) {
                    if (!(butt_3.isClickable())) {
                        if (!butt_1.isClickable()) {
                            if (!butt_5.isClickable()) {
                                if (!butt_6.isClickable()) {
                                    T.cancel();

                                    long elapsedMillis = Long.parseLong(textView_time.getText().toString());


                                    Intent i = new Intent(Game_Activty.this, ResultActivity.class);
                                    i.putExtra("count_toal",D_count);
                                    i.putExtra("card1", Card1);
                                    i.putExtra("card2", Card2);
                                    i.putExtra("card3", Card3);
                                    i.putExtra("card4", Card4);
                                    i.putExtra("card5", Card5);
                                    i.putExtra("card6", Card6);
                                    i.putExtra("English1", (String) arrayList1.get(0));
                                    i.putExtra("English2", (String) arrayList1.get(6));
                                    i.putExtra("English3", (String) arrayList1.get(12));
                                    i.putExtra("time", elapsedMillis);
                                    i.putExtra("ArraY", arrayList1);

                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivityForResult(i, 1);

                                }
                            }
                        }
                    }
                }
            }
        });
        //-----------------------Button5------------------------>
        butt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butt_5.setText((CharSequence) arrayList.get(4));
                variable5=butt_5.getText().toString();
                ++D_count;
                Displycount.setText(String.valueOf(D_count));
                button_object=butt_5;
                Display_goal.setText(String.valueOf(ct + 2 * (D_count)));

                 m = arrayList1.indexOf(butt_5.getText().toString());
                DownloadImageTask1 dd=new DownloadImageTask1();
                dd.execute();

          try {
                        MediaPlayer mp1 = new MediaPlayer();
                        String tp = (String) arrayList1.get(m + 2);
                        mp1.setDataSource("http://" + tp);
                        mp1.prepare();
                        mp1.start();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
              ++Count_b5;

                State_button5 = true;
                System.out.println("but5---------------------" + Count_b5);
                if (State_button1 && State_button5) {
                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable1)) {
                            Card5 = Count_b1 + Count_b5;
                            butt_5.startAnimation(shake);
                            butt_1.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_5.setClickable(false);
                            State_button5 = false;
                            State_button1 = false;


                        } else {

                            State_button5 = false;
                            State_button1 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_5.setText("");
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setText("");
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable1)) {
                            Card5 = Count_b1 + Count_b5;
                            butt_5.startAnimation(shake);
                            butt_1.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_5.setClickable(false);
                            State_button5 = false;
                            State_button1 = false;

                        } else {

                            State_button5 = false;
                            State_button1 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setText("");
                                    butt_1.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button5 && State_button2) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable2)) {
                            Card5 = Count_b2 + Count_b5;
                            butt_5.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_5.setClickable(false);
                            butt_2.setClickable(false);
                            State_button5 = false;
                            State_button2 = false;

                        } else {

                            State_button5 = false;
                            State_button2 = false;

                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_5.setText("");
                                    butt_2.setText("");
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable2)) {
                            Card5 = Count_b2 + Count_b5;
                            butt_5.startAnimation(shake);
                            butt_2.startAnimation(shake);

                            butt_5.setClickable(false);
                            butt_2.setClickable(false);
                            State_button5 = false;
                            State_button2 = false;

                        } else {

                            State_button5 = false;
                            State_button2 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setText("");
                                    butt_2.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button5 && State_button3) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable3)) {
                            Card5 = Count_b3 + Count_b5;
                            butt_5.startAnimation(shake);
                            butt_3.startAnimation(shake);

                            butt_5.setClickable(false);
                            butt_3.setClickable(false);
                            State_button5 = false;
                            State_button3 = false;

                        } else {

                            State_button5 = false;
                            State_button3 = false;

                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_5.setText("");
                                    butt_3.setText("");
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable3)) {
                            Card5 = Count_b3 + Count_b5;
                            butt_5.startAnimation(shake);
                            butt_3.startAnimation(shake);

                            butt_3.setClickable(false);
                            butt_5.setClickable(false);
                            State_button5 = false;
                            State_button3 = false;

                        } else {

                            State_button5 = false;
                            State_button3 = false;

                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setText("");
                                    butt_3.setText("");
                                }
                            }.start();

                        }


                    }
                } else if (State_button5 && State_button4) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable4)) {
                            Card5 = Count_b4 + Count_b5;
                            butt_5.startAnimation(shake);
                            butt_4.startAnimation(shake);

                            butt_4.setClickable(false);
                            butt_5.setClickable(false);
                            State_button5 = false;
                            State_button4 = false;

                        } else {

                            State_button5 = false;
                            State_button4 = false;

                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_5.setText("");
                                    butt_4.setText("");
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable4)) {
                            Card5 = Count_b4 + Count_b5;
                            butt_5.startAnimation(shake);
                            butt_4.startAnimation(shake);

                            butt_5.setClickable(false);
                            butt_4.setClickable(false);
                            State_button5 = false;
                            State_button4 = false;

                        } else {

                            State_button5 = false;
                            State_button4 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setText("");
                                    butt_4.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button6 && State_button5) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable6)) {
                            Card5 = Count_b6 + Count_b5;
                            butt_5.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_5.setClickable(false);
                            butt_6.setClickable(false);
                            State_button5 = false;
                            State_button6 = false;


                        } else {

                            State_button5 = false;
                            State_button6 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_5.setText("");
                                    butt_6.setText("");
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable6)) {
                            Card5 = Count_b5 + Count_b5;
                            butt_5.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_6.setClickable(false);
                            butt_5.setClickable(false);
                            State_button5 = false;
                            State_button6 = false;


                        } else {

                            State_button5 = false;
                            State_button6 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setText("");
                                    butt_6.setText("");
                                }
                            }.start();

                        }


                    }


                } else

                {

                    //   System.out.println("----------Card1:  " + Card1 + "------------Card2:" + Card2 + "------------Card3:" + Card3 + "--------------card4:" + Card4 + "----------------card5:" + Card5 + "-------------card6:" + Card6);

                }
                if (!(butt_2.isClickable())) {
                    if (!(butt_3.isClickable())) {
                        if (!butt_4.isClickable()) {
                            if (!butt_1.isClickable()) {
                                if (!butt_6.isClickable()) {
                                    T.cancel();

                                    long elapsedMillis = Long.parseLong(textView_time.getText().toString());


                                    Intent i = new Intent(Game_Activty.this, ResultActivity.class);
                                    i.putExtra("count_toal",D_count);
                                    i.putExtra("card1", Card1);
                                    i.putExtra("card2", Card2);
                                    i.putExtra("card3", Card3);
                                    i.putExtra("card4", Card4);
                                    i.putExtra("card5", Card5);
                                    i.putExtra("card6", Card6);
                                    i.putExtra("English1", (String) arrayList1.get(0));
                                    i.putExtra("English2", (String) arrayList1.get(6));
                                    i.putExtra("English3", (String) arrayList1.get(12));
                                    i.putExtra("time", elapsedMillis);
                                    i.putExtra("ArraY", arrayList1);

                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivityForResult(i, 1);

                                }
                            }
                        }
                    }
                }
            }

        });

        //----------------------->Button6---------------------------------->
        butt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butt_6.setText((CharSequence) arrayList.get(5));
                variable6=butt_6.getText().toString();
                System.out.println("value"+variable5);
                ++Count_b6;
                button_object=butt_6;
                ++D_count;
                Displycount.setText(String.valueOf(D_count));

                Display_goal.setText(String.valueOf(ct + 2 * (D_count)));

                 m = arrayList1.indexOf(butt_6.getText().toString());
                DownloadImageTask1 dd=new DownloadImageTask1();
                dd.execute();

           try {
                        MediaPlayer mp1 = new MediaPlayer();
                        String tp = (String) arrayList1.get(m + 2);
                        mp1.setDataSource("http://" + tp);
                        mp1.prepare();
                        mp1.start();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
      State_button6 = true;
                if (State_button1 && State_button6) {
                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable1)) {
                            Card6 = Count_b1 + Count_b6;
                            butt_1.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_6.setClickable(false);
                            State_button6 = false;
                            State_button1 = false;


                        } else {

                            State_button6 = false;
                            State_button1 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_6.setText("");
                                    butt_1.setText("");
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable1)) {
                            Card6 = Count_b1 + Count_b6;
                            butt_1.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_1.setClickable(false);
                            butt_6.setClickable(false);
                            State_button6 = false;
                            State_button1 = false;


                        } else {

                            State_button6 = false;
                            State_button1 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_1.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setText("");
                                    butt_1.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button6 && State_button2) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable2)) {
                            Card6 = Count_b2 + Count_b6;
                            butt_2.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_6.setClickable(false);
                            butt_2.setClickable(false);
                            State_button6 = false;
                            State_button2 = false;

                        } else {

                            State_button6 = false;
                            State_button2 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_6.setText("");
                                    butt_2.setText("");
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        System.out.println("nw array position" + l);
                        if (arrayList1.get(l).equals(variable2)) {
                            Card6 = Count_b2 + Count_b6;
                            butt_2.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_6.setClickable(false);
                            butt_2.setClickable(false);
                            State_button6 = false;
                            State_button2 = false;


                        } else {

                            State_button6 = false;
                            State_button2 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_2.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setText("");
                                    butt_2.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button6 && State_button3) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable3)) {
                            Card6 = Count_b3 + Count_b6;
                            butt_3.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_6.setClickable(false);
                            butt_3.setClickable(false);
                            State_button6 = false;
                            State_button3 = false;


                        } else {

                            State_button6 = false;
                            State_button3 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_6.setText("");
                                    butt_3.setText("");
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable3)) {
                            Card6 = Count_b3 + Count_b6;
                            butt_3.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_3.setClickable(false);
                            butt_6.setClickable(false);
                            State_button6 = false;
                            State_button3 = false;


                        } else {

                            State_button6 = false;
                            State_button3 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_3.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setText("");
                                    butt_3.setText("");
                                }
                            }.start();

                        }


                    }
                } else if (State_button6 && State_button4) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable4)) {
                            Card6 = Count_b4 + Count_b6;
                            butt_4.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_4.setClickable(false);
                            butt_6.setClickable(false);
                            State_button6 = false;
                            State_button4 = false;


                        } else {

                            State_button6 = false;
                            State_button4 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_6.setText("");
                                    butt_4.setText("");
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable4)) {
                            Card6 = Count_b5 + Count_b6;
                            butt_4.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_6.setClickable(false);
                            butt_4.setClickable(false);
                            State_button6 = false;
                            State_button4 = false;

                        } else {

                            State_button6 = false;
                            State_button4 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_4.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setText("");
                                    butt_4.setText("");
                                }
                            }.start();

                        }


                    }


                } else if (State_button6 && State_button5) {

                    int array = m;
                    if (array % 2 == 0) {
                        int k = 3 + array;
                        if (arrayList1.get(k).equals(variable5)) {
                            Card6 = Count_b5 + Count_b6;
                            butt_5.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_5.setClickable(false);
                            butt_6.setClickable(false);
                            State_button6 = false;
                            State_button5 = false;


                        } else {

                            State_button6 = false;
                            State_button5 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_6.setText("");
                                    butt_5.setText("");
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                }
                            }.start();

                        }
                    } else {
                        int l = array - 3;
                        if (arrayList1.get(l).equals(variable5)) {
                            Card6 = Count_b5 + Count_b6;
                            butt_5.startAnimation(shake);
                            butt_6.startAnimation(shake);

                            butt_6.setClickable(false);
                            butt_5.setClickable(false);
                            State_button6 = false;
                            State_button5 = false;


                        } else {

                            State_button6 = false;
                            State_button5 = false;
                            new CountDownTimer(1000, 50) {

                                @Override
                                public void onTick(long arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onFinish() {
                                    butt_6.setBackgroundResource(R.drawable.main_03);
                                    butt_5.setBackgroundResource(R.drawable.main_03);
                                    butt_6.setText("");
                                    butt_5.setText("");
                                }
                            }.start();

                        }


                    }


                } else

                {

                    // System.out.println("----------Card1:  " + Card1 + "------------Card2:" + Card2 + "------------Card3:" + Card3 + "--------------card4:" + Card4 + "----------------card5:" + Card5 + "-------------card6:" + Card6);

                }
                if (!(butt_2.isClickable())) {
                    if (!(butt_3.isClickable())) {
                        if (!butt_4.isClickable()) {
                            if (!butt_5.isClickable()) {
                                if (!butt_1.isClickable()) {
                                    T.cancel();

                                    long elapsedMillis = Long.parseLong(textView_time.getText().toString());


                                    Intent i = new Intent(Game_Activty.this, ResultActivity.class);
                                    i.putExtra("count_toal",D_count);
                                    i.putExtra("card1", Card1);
                                    i.putExtra("card2", Card2);
                                    i.putExtra("card3", Card3);
                                    i.putExtra("card4", Card4);
                                    i.putExtra("card5", Card5);
                                    i.putExtra("card6", Card6);
                                    i.putExtra("time", elapsedMillis);
                                    i.putExtra("English1", (String) arrayList1.get(0));
                                    i.putExtra("English2", (String) arrayList1.get(6));
                                    i.putExtra("English3", (String) arrayList1.get(12));
                                    i.putExtra("ArraY", arrayList1);

                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivityForResult(i, 1);


                                }
                            }
                        }
                    }
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("ashok got data1");
        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {

                tt=data.getParcelableArrayListExtra("ee");
                System.out.println("ashok got data1" + tt);

                butt_1.setBackgroundResource(R.drawable.main_03);
                butt_2.setBackgroundResource(R.drawable.main_03);
                butt_3.setBackgroundResource(R.drawable.main_03);
                butt_4.setBackgroundResource(R.drawable.main_03);
                butt_5.setBackgroundResource(R.drawable.main_03);
                butt_6.setBackgroundResource(R.drawable.main_03);
                butt_1.setClickable(true);
                butt_2.setClickable(true);
                butt_3.setClickable(true);
                butt_4.setClickable(true);
                butt_5.setClickable(true);
                butt_6.setClickable(true);
                butt_1.setText("");
                butt_2.setText("");
                butt_3.setText("");
                butt_4.setText("");
                butt_5.setText("");
                butt_6.setText("");

                butt_1.clearAnimation();
                butt_2.clearAnimation();
                butt_3.clearAnimation();
                butt_4.clearAnimation();
                butt_5.clearAnimation();
                butt_6.clearAnimation();

                Card1=0;
                Card2=0;
                Card3=0;
                Card4=0;
                Card5=0;
                Card6=0;
                Count_b1=0;
                Count_b2=0;
                Count_b3=0;
                Count_b3=0;
                Count_b4=0;
                Count_b5=0;
                D_count=0;

                Displycount.setText("");
           System.out.println(arrayList1.get(0));
             ct=0;
                T = new Timer();
                T.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView_time.setText("" + ct);
                                Display_goal.setText(String.valueOf(ct + 2 * (D_count)));

                                ct++;
                            }
                        });
                    }
                }, 0, 1000);


            }
        }
    }


    class DownloadImageTask1 extends AsyncTask<String,Button,Bitmap> {
        Drawable drawable1;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @SuppressLint("NewApi")
        protected Bitmap doInBackground(String... urls) {

            Bitmap mIcon11 = null;
            try {

                button_object.setBackgroundColor(getResources().getColor(R.color.dukecolor));
                System.out.println("null value checking"+m);

                if (m % 2 == 0) {
                    if(arrayList1.get(m + 1).equals(0))
                    {
                        System.out.println("background color changed");
                        button_object.setBackgroundColor(getResources().getColor(R.color.dukecolor));
                    }else {
                        URL url = new URL("http://" + arrayList1.get(m + 1));
                        Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        drawable1 = new BitmapDrawable(getResources(), image);
                        button_object.setBackground(drawable1);
                        System.out.println("demo issue" + button_object.getBackground().toString());
                    }
                } else {
                    String temp = button_object.getText().toString();
                    System.out.println("lusu poda"+arrayList1.get(m+1));
                    if(!(arrayList1.get(m+1).equals(0)))
                    {
                        temp = temp + "\n" + arrayList1.get(m + 1);
                    }

                    button_object.setText(temp);
                    button_object.setBackgroundColor(getResources().getColor(R.color.dukecolor));
                    System.out.println("ask final aaaaaaaassssssssskkkkk" + button_object);
                }

            } catch (Exception e) {

            //    button_object.setBackgroundColor(getResources().getColor(R.color.dukecolor));
                e.printStackTrace();
            }
            return null;
        }






    }
    @Override
       public void onBackPressed() {
        Intent intent = new Intent(Game_Activty.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

