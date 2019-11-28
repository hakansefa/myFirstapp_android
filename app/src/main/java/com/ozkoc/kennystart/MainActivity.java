package com.ozkoc.kennystart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    ImageView [] loop;
    TextView time;
    TextView score;
    Handler handler;
    Runnable runnable;

    int sayac=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.image1);
        img2 = findViewById(R.id.image2);
        img3 = findViewById(R.id.image3);
        img4 = findViewById(R.id.image4);
        img5 = findViewById(R.id.image5);
        img6 = findViewById(R.id.image6);
        img7 = findViewById(R.id.image7);
        img8 = findViewById(R.id.image8);
        img9 = findViewById(R.id.image9);
        time=findViewById(R.id.textView3);
        score=findViewById(R.id.textView4);
        loop = new ImageView[]{img1, img2, img3, img4, img5, img6, img7, img8, img9};

         gerisay();
         randomimg();

    }


    public void score_up (View view){

         sayac=sayac +1 ;
        score.setText("Score : " + sayac );

    }
    public void  gerisay(){
        final CountDownTimer timer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Time : "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                time.setText("Time Off");
                handler.removeCallbacks(runnable);
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Time is Over");
                alert.setMessage("Are you sure to restart game ? ");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //score.setText("Score : 0 ");
                        //time.setText("Time : ");
                        //sayac=0;
                        //handler.post(runnable);
                        Intent intent = new Intent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over Dude", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Well Done ! Your Score is "+sayac+"points", Toast.LENGTH_SHORT).show();

                    }
                });
                alert.show();
            }
        }.start();

    }
    public void randomimg(){

         handler = new Handler();
         runnable = new Runnable() {
             @Override
             public void run() {

                     for (ImageView image : loop) {
                         image.setVisibility(View.INVISIBLE);

                     }
                   Random r = new Random();
                     int i = r.nextInt(9);
                     loop[i].setVisibility(View.VISIBLE);
                     handler.postDelayed(this,1000);

             }
         };
            handler.post(runnable);


    }
}
