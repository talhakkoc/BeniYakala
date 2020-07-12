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

public class Bolum3 extends AppCompatActivity {

    RelativeLayout rellay3;
    TextView txtSkor;
    TextView txtSure;
    ImageView imgt1;
    ImageView imgt2;
    ImageView imgt3;
    ImageView imgt4;
    ImageView imgt5;
    ImageView imgt6;
    ImageView imgt7;
    ImageView imgt8;
    ImageView imgt9;
    int score = 0;
    boolean flag;
    ImageView[] imgArr;
    Runnable runnable;
    Handler handler;

    ImageView bombt1;
    ImageView bombt2;
    ImageView bombt3;
    ImageView bombt4;
    ImageView bombt5;
    ImageView bombt6;
    ImageView bombt7;
    ImageView bombt8;
    ImageView bombt9;
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
        mp2=(MediaPlayer.create(Bolum3.this,R.raw.game));
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
        setContentView(R.layout.activity_bolum3);


        rellay3 = (RelativeLayout) findViewById(R.id.rellay3);
        imgt1 = (ImageView) findViewById(R.id.imgt1);
        imgt2 = (ImageView) findViewById(R.id.imgt2);
        imgt3 = (ImageView) findViewById(R.id.imgt3);
        imgt4 = (ImageView) findViewById(R.id.imgt4);
        imgt5 = (ImageView) findViewById(R.id.imgt5);
        imgt6 = (ImageView) findViewById(R.id.imgt6);
        imgt7 = (ImageView) findViewById(R.id.imgt7);
        imgt8 = (ImageView) findViewById(R.id.imgt8);
        imgt9 = (ImageView) findViewById(R.id.imgt9);

        imgArr = new ImageView[]{imgt1, imgt2, imgt3, imgt4, imgt5, imgt6, imgt7, imgt8, imgt9};

        bombt1 = (ImageView) findViewById(R.id.bombt1);
        bombt2 = (ImageView) findViewById(R.id.bombt2);
        bombt3 = (ImageView) findViewById(R.id.bombt3);
        bombt4 = (ImageView) findViewById(R.id.bombt4);
        bombt5 = (ImageView) findViewById(R.id.bombt5);
        bombt6 = (ImageView) findViewById(R.id.bombt6);
        bombt7 = (ImageView) findViewById(R.id.bombt7);
        bombt8 = (ImageView) findViewById(R.id.bombt8);
        bombt9 = (ImageView) findViewById(R.id.bombt9);

        bombArr = new ImageView[]{bombt1,bombt2,bombt3,bombt4,bombt5,bombt6,bombt7,bombt8,bombt9};

        txtDesign=(TextView) findViewById(R.id.txtDesign);

        flag = true;
        rellay3.setOnClickListener(new View.OnClickListener() {
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
                int i = r.nextInt(8 - 0);
                imgArr[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 500);
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
                },750);


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bombArr[i].setVisibility(View.INVISIBLE);
                    }
                },1200);

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
                            Bolum3.this.finish();
                            finishAffinity();
                        }
                    })
                    .setPositiveButton("                        DEVAM ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing

                            Intent intent = new Intent(Bolum3.this, Bolum3.class);
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
        else{


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
                            Bolum3.this.finish();
                            finishAffinity();


                        }
                    })
                    .setPositiveButton("                         DİĞER BÖLÜME GEÇ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing

                            startActivity(new Intent(Bolum3.this,Bolum4.class));
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
