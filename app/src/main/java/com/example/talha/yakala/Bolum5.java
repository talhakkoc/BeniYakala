package com.cygnus.yakala;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
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

public class Bolum5 extends AppCompatActivity {

    RelativeLayout rellay5  ;
    TextView txtSkor;
    TextView txtSure;
    ImageView imageson1;
    ImageView imageson2;
    ImageView imageson3;
    ImageView imageson4;
    ImageView imageson5;
    ImageView imageson6;
    ImageView imageson7;
    ImageView imageson8;
    ImageView imageson9;
    int score = 0;
    boolean flag;
    ImageView[] imgArr;
    Runnable runnable;
    Handler handler;

    ImageView bombson1;
    ImageView bombson2;
    ImageView bombson3;
    ImageView bombson4;
    ImageView bombson5;
    ImageView bombson6;
    ImageView bombson7;
    ImageView bombson8;
    ImageView bombson9;
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
        mp2=(MediaPlayer.create(Bolum5.this,R.raw.rave));
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
        setContentView(R.layout.activity_bolum5);

        rellay5 = (RelativeLayout) findViewById(R.id.rellay5);
        imageson1 = (ImageView) findViewById(R.id.imageson1);
        imageson2 = (ImageView) findViewById(R.id.imageson2);
        imageson3= (ImageView) findViewById(R.id.imageson3);
        imageson4 = (ImageView) findViewById(R.id.imageson4);
        imageson5 = (ImageView) findViewById(R.id.imageson5);
        imageson6 = (ImageView) findViewById(R.id.imageson6);
        imageson7 = (ImageView) findViewById(R.id.imageson7);
        imageson8 = (ImageView) findViewById(R.id.imageson8);
        imageson9 = (ImageView) findViewById(R.id.imageson9);

        imgArr = new ImageView[]{imageson1, imageson2, imageson3, imageson4, imageson5, imageson6, imageson7, imageson8, imageson9};

        bombson1 = (ImageView) findViewById(R.id.bombson1);
        bombson2 = (ImageView) findViewById(R.id.bombson2);
        bombson3 = (ImageView) findViewById(R.id.bombson3);
        bombson4 = (ImageView) findViewById(R.id.bombson4);
        bombson5 = (ImageView) findViewById(R.id.bombson5);
        bombson6 = (ImageView) findViewById(R.id.bombson6);
        bombson7 = (ImageView) findViewById(R.id.bombson7);
        bombson8 = (ImageView) findViewById(R.id.bombson8);
        bombson9 = (ImageView) findViewById(R.id.bombson9);

        bombArr = new ImageView[]{bombson1,bombson2,bombson3,bombson4,bombson5,bombson6,bombson7,bombson8,bombson9};

        txtDesign=(TextView) findViewById(R.id.txtDesign);


        flag = true;
        rellay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deneme();
            }
        });

        resimSakla();
        bombaSakla();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

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

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)

    public void skorArttir(View view) {
        deneme();
        score++;
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
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
                },650);


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
                            Bolum5.this.finish();
                            finishAffinity();
                        }
                    })
                    .setPositiveButton("                        DEVAM ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing

                            Intent intent = new Intent(Bolum5.this, Bolum5.class);
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
                    .setMessage("Tebrikler Otun Bitti !!")
                    .setCancelable(false)
                    .setNegativeButton("OYUNDAN ÇIK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            Bolum5.this.finish();
                            finishAffinity();


                        }
                    })
                    .setPositiveButton("                        DEVAM ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing

                            startActivity(new Intent(Bolum5.this,Bolum5.class));
                            getIntent().setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            finish();
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
