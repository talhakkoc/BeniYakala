package com.cygnus.yakala;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class Bolum4 extends AppCompatActivity {

    RelativeLayout rellay4;
    TextView txtSkor;
    TextView txtSure;
    ImageView imageb1;
    ImageView imageb2;
    ImageView imageb3;
    ImageView imageb4;
    ImageView imageb5;
    ImageView imageb6;
    ImageView imageb7;
    ImageView imageb8;
    ImageView imageb9;
    int score = 0;
    boolean flag;
    ImageView[] imgArr;
    Runnable runnable;
    Handler handler;

    ImageView bombab1;
    ImageView bombab2;
    ImageView bombab3;
    ImageView bombab4;
    ImageView bombab5;
    ImageView bombab6;
    ImageView bombab7;
    ImageView bombab8;
    ImageView bombab9;
    ImageView[] bombArr;

    TextView txtDesign;

    final Context context = this;

    MediaPlayer mp2;


    @Override
    protected void onPause() {
        super.onPause();
        mp2.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp2=(MediaPlayer.create(Bolum4.this,R.raw.videogame2));
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
        setContentView(R.layout.activity_bolum4);

        rellay4 = (RelativeLayout) findViewById(R.id.rellay4);
        imageb1 = (ImageView) findViewById(R.id.imageb1);
        imageb2 = (ImageView) findViewById(R.id.imageb2);
        imageb3 = (ImageView) findViewById(R.id.imageb3);
        imageb4 = (ImageView) findViewById(R.id.imageb4);
        imageb5 = (ImageView) findViewById(R.id.imageb5);
        imageb6 = (ImageView) findViewById(R.id.imageb6);
        imageb7 = (ImageView) findViewById(R.id.imageb7);
        imageb8 = (ImageView) findViewById(R.id.imageb8);
        imageb9 = (ImageView) findViewById(R.id.imageb9);


        imgArr = new ImageView[]{imageb1,imageb2,imageb3,imageb4,imageb5,imageb6,imageb7,imageb8,imageb9};


        bombab1 = (ImageView) findViewById(R.id.bombab1);
        bombab2 = (ImageView) findViewById(R.id.bombab2);
        bombab3 = (ImageView) findViewById(R.id.bombab3);
        bombab4 = (ImageView) findViewById(R.id.bombab4);
        bombab5 = (ImageView) findViewById(R.id.bombab5);
        bombab6 = (ImageView) findViewById(R.id.bombab6);
        bombab7 = (ImageView) findViewById(R.id.bombab7);
        bombab8 = (ImageView) findViewById(R.id.bombab8);
        bombab9 = (ImageView) findViewById(R.id.bombab9);

        bombArr = new ImageView[]{bombab1,bombab2,bombab3,bombab4,bombab5,bombab6,bombab7,bombab8,bombab9};


        txtDesign=(TextView) findViewById(R.id.txtDesign);


        flag = true;
        rellay4.setOnClickListener(new View.OnClickListener() {
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
                handler.postDelayed(this, 400);
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
                        if(bombArr[i]!=imgArr[i])
                            bombArr[i].setVisibility(View.VISIBLE);
                    }
                },700);


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bombArr[i].setVisibility(View.INVISIBLE);
                    }
                },1000);

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

        if (score <=9) {
            // set title
            alertDialogBuilder.setIcon(R.drawable.uzgkost);

            alertDialogBuilder.setTitle("SKORUNUZ : " + score);

            // set dialog message
            alertDialogBuilder
                    .setMessage("Tekrar Dene ?")
                    .setCancelable(false)
                    .setNegativeButton("ÇIKIŞ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            Bolum4.this.finish();
                            finishAffinity();
                        }
                    })
                    .setPositiveButton("                        DEVAM ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing

                            Intent intent = new Intent(Bolum4.this, Bolum4.class);
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
        } else {


            alertDialogBuilder.setIcon(R.drawable.mutlukost);

            // set title
            alertDialogBuilder.setTitle("SKORUNUZ : " + score);

            // set dialog message
            alertDialogBuilder
                    .setMessage("Diğer Bölüme Geçebilirsin")
                    .setCancelable(false)
                    .setNegativeButton("ÇIKIŞ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            Bolum4.this.finish();
                            finishAffinity();


                        }
                    })
                    .setPositiveButton("                         DİĞER BÖLÜME GEÇ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing

                            startActivity(new Intent(Bolum4.this, Bolum5.class));
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