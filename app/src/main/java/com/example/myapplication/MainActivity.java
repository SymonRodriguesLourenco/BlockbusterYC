package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import tyrantgit.explosionfield.ExplosionField;


public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private ImageView donut;
    private ExplosionField explosionField;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.explosionField=ExplosionField.attach2Window(this);
        this.startButton = findViewById(R.id.startButton);

        this.donut = findViewById(R.id.donut);
        this.startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                explosionField.explode(donut);
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
        TextView highscoreLabel = findViewById(R.id.textHighScore);
        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);
        highscoreLabel.setText("High score : " + highScore);
    }

    public void goToStart(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_main);
        finish();
    }
}
