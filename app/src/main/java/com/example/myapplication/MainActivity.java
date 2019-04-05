package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import tyrantgit.explosionfield.ExplosionField;


public class MainActivity extends AppCompatActivity {

    Button imageButton, highscoreButton;
    ImageView imageView;
    ExplosionField explosionField;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        explosionField=ExplosionField.attach2Window(this);
        imageButton= findViewById(R.id.imageButton3);

        imageView = findViewById(R.id.donut);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                explosionField.explode(imageView);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startGame(view);
                    }
                }, 500);

            }


        });

    }

    public void startGame(View view){
        Intent intent = new Intent(MainActivity.this, StartGame.class);
        startActivity(intent);
        finish();
    }

    public void goTohighscore(View view) {
        setContentView(R.layout.activity_game_highscore);
    }

    public void goToStart(View view) {
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
