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
    public static ImageView poging1, poging2, poging3, leven1, leven2, leven3;
    public static Bitmap be, bf, he, hf;
    public static int pogingen = 3, levens = 3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game(this);
        setContentView(R.layout.activity_game_view);
        addContentView(game, new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        poging1 =(ImageView) findViewById(R.id.poging1);
        poging2 =(ImageView) findViewById(R.id.poging2);
        poging3 =(ImageView) findViewById(R.id.poging3);
        leven1 =(ImageView) findViewById(R.id.leven1);
        leven2 =(ImageView) findViewById(R.id.leven2);
        leven3 =(ImageView) findViewById(R.id.leven3);
        be = BitmapFactory.decodeResource(getResources() ,R.drawable.ball_eaten);
        bf = BitmapFactory.decodeResource(getResources(), R.drawable.ball_full);
        he = BitmapFactory.decodeResource(getResources(), R.drawable.hearticon_empty);
    }

    public static void verander() {
        final Handler handler = new Handler();
        switch (pogingen) {
            case 3:
                poging1.setImageBitmap(be);
                pogingen--;
                break;
            case 2:
                poging2.setImageBitmap(be);
                pogingen--;
                break;
            case 1:
                poging3.setImageBitmap(be);
                pogingen--;
                break;
        }

        if (pogingen == 0) {
            poging1.setImageBitmap(bf);
            poging2.setImageBitmap(bf);
            poging3.setImageBitmap(bf);
            pogingen = 3;
            levens--;
        }

        if (levens == 2) {
            leven1.setImageBitmap(he);
        } else if (levens == 1) {
            leven2.setImageBitmap(he);
        } else if (levens == 0) {
            leven3.setImageBitmap(he);
        }
    }


    public void restart(){
        Intent intent = new Intent(StartGame.this, MainActivity.class);
        startActivity(intent);
        finish();
    }




}
