package com.example.myapplication;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class StartGame extends AppCompatActivity {

    private ImageView attempt, lifeOne, lifeTwo, lifeThree;
    private TextView textAttempt, textScore, textLevel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);
    }

    protected void onStart(){
        super.onStart();
        this.attempt = findViewById(R.id.attempt);
        this.lifeOne = findViewById(R.id.lifeOne);
        this.lifeTwo = findViewById(R.id.lifeTwo);
        this.lifeThree = findViewById(R.id.lifeThree);
        this.textAttempt = findViewById(R.id.textAttempt);
        this.textScore = findViewById(R.id.textScore);
        this.textLevel = findViewById(R.id.textLevel);
        Game game = new Game(this, attempt, lifeOne, lifeTwo, lifeThree, textAttempt, textScore, textLevel);
        addContentView(game, new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


    }


}