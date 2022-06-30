package com.beraterdem.catchkennygame;

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

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int kenny1, johnny1, gumball1, dexter1;
    ImageView Dexter;
    ImageView Johnny;
    ImageView Gumball;
    ImageView Darwin;
    ImageView Buttercup;
    ImageView Cartman;
    ImageView Kenny;
    ImageView Jerry;
    ImageView Oggy;
    TextView time;
    TextView score;
    ImageView[] imageViews;
    Runnable runnable;
    Handler handler;
    Random random = new Random();
    int Score;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dexter = findViewById(R.id.dexter);
        Johnny = findViewById(R.id.johnny);
        Gumball = findViewById(R.id.gumball);
        Darwin = findViewById(R.id.darwin);
        Buttercup = findViewById(R.id.buttercup);
        Cartman = findViewById(R.id.cartman);
        Kenny = findViewById(R.id.kenny);
        Jerry = findViewById(R.id.jerry);
        Oggy = findViewById(R.id.oggy);
        time = findViewById(R.id.Sayac);
        score = findViewById(R.id.Score);
        alert = new AlertDialog.Builder(MainActivity.this);
        imageViews = new ImageView[]{Dexter, Johnny, Gumball, Darwin, Buttercup, Cartman, Kenny, Jerry, Oggy};
        hideImage();
        Score = 0;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Time:" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                time.setText("TimeOff");
                handler.removeCallbacks(runnable);
                for (ImageView imageView : imageViews) {
                    imageView.setVisibility(View.INVISIBLE);
                }
                alert.setTitle("GAME OVER");
                alert.setMessage("Try Again ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);


                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_LONG).show();

                    }
                });
                alert.show();
            }
        }.start();

    }


    public void hideImage() {

        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                for (ImageView image : imageViews) {
                    image.setVisibility(View.INVISIBLE);
                }
                int i = random.nextInt(9);
                imageViews[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 500);
            }
        };
        handler.post(runnable);
    }

    public void imageClick(View view) {
       Score++;
       score.setText("Score:"+Score);
    }

}
