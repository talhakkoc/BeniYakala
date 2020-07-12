package com.cygnus.yakala;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rellay;
    TextView txtSkor;
    TextView txtSure;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;
    ImageView img9;
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
        mp2=(MediaPlayer.create(MainActivity.this,R.raw.pimpoy));
        mp2.setLooping(true);
        mp2.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rellay = (RelativeLayout) findViewById(R.id.rellay);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);
        img6 = (ImageView) findViewById(R.id.img6);
        img7 = (ImageView) findViewById(R.id.img7);
        img8 = (ImageView) findViewById(R.id.img8);
        img9 = (ImageView) findViewById(R.id.img9);

        imgArr = new ImageView[]{img1, img2, img3, img4, img5, img6, img7, img8, img9};

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
        rellay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deneme();
            }
        });

        resimSakla();
        bombaSakla();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
     //   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
       //     v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
        //}else{
          //  //deprecated in API 26
           // v.vibrate(500);
       // }

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
try{
    MyDialog();
}
    catch (Exception e){

    }

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
                handler.postDelayed(this, 700);
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
                },800);
//bombanın çıkma sıklıgı

               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       bombArr[i].setVisibility(View.INVISIBLE);
                   }
               },1600);
//bombanın ekranda kalma süresi
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

        if (score <= 9) {
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
                            MainActivity.this.finish();
                            finishAffinity();
                        }
                    })
                    .setPositiveButton("                        DEVAM", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing

                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                            resimSakla();
                            dialog.cancel();

                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
          //  alertDialog.getWindow().setGravity(Gravity.TOP);
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
                alertDialogBuilder.setTitle("TEBRİKLER SKORUNUZ : " + score  );

                // set dialog message
                alertDialogBuilder
                        .setMessage("Diğer Bölüme Geçebilirsin")
                        .setCancelable(false)
                        .setNegativeButton("ÇIKIŞ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                                MainActivity.this.finish();
                                finishAffinity();


                            }
                        })
                        .setPositiveButton("                         DİĞER BÖLÜME GEÇ ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing

                               startActivity(new Intent(MainActivity.this,Bolum2.class));
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
            try{
                alertDialog.show();
            }
              catch (Exception e ) {

              }







        }
    }

}







