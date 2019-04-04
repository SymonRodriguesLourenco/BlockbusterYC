package com.example.myapplication.blokjes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Powerupblock extends Block{

    String powerup;
    List<String> listpowerups = new ArrayList<String>();

    public Powerupblock(int X, int Y, int width, int height, String powerup, Resources resources) {
        super(X, Y, width, height,  1);
        this.powerup = powerup;
        this.img = BitmapFactory.decodeResource(resources, R.drawable.popcorn);
        this.img = Bitmap.createScaledBitmap(this.img, this.height, this.height, false);
    }

    public String getPowerup() {
        powerup = randomizelistpowerup();
        return powerup;
    }

    public String randomizelistpowerup(){
        listpowerups.add("pogingen");
        listpowerups.add("levens");
        listpowerups.add("multiball");
        listpowerups.add("powerball");
        Random rand = new Random();
        String random = listpowerups.get(rand.nextInt(listpowerups.size()));
        return random;
    }
}
