package com.example.myapplication;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class StartGame extends AppCompatActivity {
    ImageView poging1, leven1, leven2, leven3;
    TextView pogingTekst, scoreLabel, levelLabel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);





    }

    protected void onStart(){
        super.onStart();
        poging1 = findViewById(R.id.poging1);
        pogingTekst = findViewById(R.id.pogingTekst);
        leven1 = findViewById(R.id.leven1);
        leven2 = findViewById(R.id.leven2);
        leven3 = findViewById(R.id.leven3);
        scoreLabel = findViewById(R.id.scoreLabel);
        levelLabel = findViewById(R.id.levelLabel);
        Game game = new Game(this, poging1, pogingTekst, leven1, leven2, leven3, scoreLabel, levelLabel);
        addContentView(game, new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


    }


}
