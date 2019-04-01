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


public class StartGame extends AppCompatActivity {
    ImageView poging1, poging2, poging3, leven1, leven2, leven3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);





    }

    protected void onStart(){
        super.onStart();
        poging1 = findViewById(R.id.poging1);
        poging2 = findViewById(R.id.poging2);
        poging3 = findViewById(R.id.poging3);
        leven1 = findViewById(R.id.leven1);
        leven2 = findViewById(R.id.leven2);
        leven3 = findViewById(R.id.leven3);
        Game game = new Game(this, poging1, poging2, poging3, leven1, leven2, leven3);
        addContentView(game, new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


    }


}
