package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    private TextView textScore, textHighScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("score");
        boolean finished = bundle.getBoolean("finished");
        int val = Integer.parseInt(value);
        if (finished){
            setContentView(R.layout.activity_game_finish);
        } else {
            setContentView(R.layout.activity_game_over);
        }
        this.textScore = findViewById(R.id.textScore);
        this.textHighScore = findViewById(R.id.textHighScore);

        this.textScore.setText(value);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);
        if (val > highScore) {
            this.textHighScore.setText("High score : " + val);

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", val);
            editor.commit();
        }

        else {
            this.textHighScore.setText("High Score : " + highScore);
        }

    }

    public void tryAgain(View view) {
        startActivity(new Intent(getApplicationContext(), StartGame.class));
    }

    public void goToStart(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}