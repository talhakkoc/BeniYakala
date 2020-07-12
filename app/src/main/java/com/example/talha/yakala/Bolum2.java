package com.cygnus.yakala;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Bolum2 extends AppCompatActivity {



    RelativeLayout rellay2;
    TextView txtSkor;
    TextView txtSure;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    ImageView image7;
    ImageView image8;
    ImageView image9;
    int score = 0;
    boolean flag;
    ImageView[] imgArr;
    Runnable runnable;
    Handler handler;

    ImageView bomb1;
    ImageView bomb2;
    ImageView bomb3;
    ImageView bomb4;
    ImageView bomb5;
    ImageView bomb6;
    ImageView bomb7;
    ImageView bomb8;
    ImageView bomb9;
    ImageView[] bombArr;

    final Context context = this;

    TextView txtDesign;
    MediaPlayer mp2;



    @Override
    protected void onPause() {
        super.onPause();
        mp2.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp2=(MediaPlayer.create(Bolum2.this,R.raw.retro));
        mp2.setLooping(true);
        mp2.start();
    }

    @Override
    public void onBackPressed() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(130);
       }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolum2);


        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);
        image6 = (ImageView) findViewById(R.id.image6);
        image7 = (ImageView) findViewById(R.id.image7);
        image8 = (ImageView) findViewById(R.id.image8);
        image9 = (ImageView) findViewById(R.id.image9);


        imgArr = new ImageView[]{image1,image2,image3,image4,image5,image6,image7,image8,image9};


        bomb1 = (ImageView) findViewById(R.id.bomb1);
        bomb2 = (ImageView) findViewById(R.id.bomb2);
        bomb3 = (ImageView) findViewById(R.id.bomb3);
        bomb4 = (ImageView) findViewById(R.id.bomb4);
        bomb5 = (ImageView) findViewById(R.id.bomb5);
        bomb6 = (ImageView) findViewById(R.id.bomb6);
        bomb7 = (ImageView) findViewById(R.id.bomb7);
        bomb8 = (ImageView) findViewById(R.id.bomb8);
        bomb9 = (ImageView) findViewById(R.id.bomb9);

        bombArr = new ImageView[]{bomb1,bomb2,bomb3,bomb4,bomb5,bomb6,bomb7,bomb8,bomb9};


        txtDesign=(TextView) findViewById(R.id.txtDesign);


        flag = true;
        rellay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deneme();
            }
        });


        resimSakla();
        bombaSakla();



    }


    public void deneme() {

        if (flag) {
            new CountDownTimer(15000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    txtSure = (TextView) findViewById(R.id.txtSure);
                    flag = false;
                    txtSure.setText("KALAN SÜRE : " + millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    txtSure.setText("SÜRE BİTTİ ");
                    //        txtSkor.setText("SKOR : 0");
                    flag = true;
                    handler.removeCallbacks(runnable);

                    MyDialog();

                }
            }.start();

        }

    }

    public void skorArttir(View view) {
        deneme();
        score++;
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        v.vibrate(90);
        txtSkor = (TextView) findViewById(R.id.txtSkor);
        txtSure = (TextView) findViewById(R.id.txtSure);
        txtSkor.setTextColor(Color.parseColor("#FF43FEBC"));


        txtSkor.setText("SKOR : " + score);

    }

    public void skorAzalt(View view) {
        deneme();
        if(score>=3)
            score=score-3;
        else{
            score = 0 ;
        }
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        v.vibrate(250);
        txtSkor = (TextView) findViewById(R.id.txtSkor);
        txtSure = (TextView) findViewById(R.id.txtSure);


        final Toast toast = Toast.makeText(getApplicationContext(), "Eyvah Bomba ! ", Toast.LENGTH_LONG );
        toast.setGravity(Gravity.BOTTOM, 0, 920);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 300);

        txtSkor.setText("SKOR : " + score);


    }


    public void resimSakla() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView img : imgArr) {
                    img.setVisibility(View.INVISIBLE);

                }
                Random r = new Random();
             final   int i = r.nextInt(8 - 0);
                    imgArr[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 600);
            }
        };
        handler.post(runnable);


    }

    public void bombaSakla(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView bomb : bombArr) {
                    bomb.setVisibility(View.INVISIBLE);

                }
                Random r = new Random();
                final int i = r.nextInt(8 - 0);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(bombArr[i]!=imgArr[i]) {
                            bombArr[i].setVisibility(View.VISIBLE);
                        }
                        else {
                            bombArr[i].setVisibility(View.INVISIBLE);

                        }

                    }
                },700);


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bombArr[i].setVisibility(View.INVISIBLE);
                    }
                },1400);

                //

                handler.postDelayed(this, 2700);
            }
        };
        handler.post(runnable);

    }

    public void MyDialog() {

        mp2.stop();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setIcon(R.drawable.uzgkost);

        if(score <=9) {
            // set title
            alertDialogBuilder.setTitle("SKORUNUZ : " + score);

            // set dialog message
            alertDialogBuilder
                    .setMessage("Tekrar Dene ? ")
                    .setCancelable(false)
                    .setNegativeButton("ÇIKIŞ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            Bolum2.this.finish();
                            finishAffinity();
                        }
                    })
                    .setPositiveButton("                        DEVAM", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing

                            Intent intent = new Intent(Bolum2.this, Bolum2.class);
                            startActivity(intent);
                            resimSakla();
                            dialog.cancel();

                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams wmlp = alertDialog.getWindow().getAttributes();

            wmlp.gravity = Gravity.TOP | Gravity.LEFT;
            wmlp.x = 100;   //x position
            wmlp.y = 100;
            // show it
            alertDialog.show();

        }
        else {

            alertDialogBuilder.setIcon(R.drawable.mutlukost);

            // set title
            alertDialogBuilder.setTitle("SKORUNUZ : " + score  );

            // set dialog message
            alertDialogBuilder
                    .setMessage("Diğer Bölüme Geçebilirsin")
                    .setCancelable(false)
                    .setNegativeButton("ÇIKIŞ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                           finishAffinity();

                        }
                    })
                    .setPositiveButton("                         DİĞER BÖLÜME GEÇ ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing

                            startActivity(new Intent(Bolum2.this,Bolum3.class));
                            resimSakla();
                            dialog.cancel();

                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams wmlp = alertDialog.getWindow().getAttributes();

            wmlp.gravity = Gravity.TOP | Gravity.LEFT;
            wmlp.x = 100;   //x position
            wmlp.y = 100;

            // show it
            alertDialog.show();

















        }


    }

    }


