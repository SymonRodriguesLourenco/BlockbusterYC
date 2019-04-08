package com.example.myapplication.blokjes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Powerupblock extends Block{

    private String powerup;
    private List<String> listPowerUps = new ArrayList<String>();

    public Powerupblock(int X, int Y, int width, int height, String powerup, Resources resources) {
        super(X, Y, width, height,  1);
        this.powerup = powerup;
        this.img = BitmapFactory.decodeResource(resources, R.drawable.popcorn);
        this.img = Bitmap.createScaledBitmap(this.img, height, height, false);
    }

    public String getPowerup() {
        this.powerup = randomizeListPowerup();
        return this.powerup;
    }

    public String randomizeListPowerup(){
        this.listPowerUps.add("attempt");
        this.listPowerUps.add("lives");
        this.listPowerUps.add("multiball");
        this.listPowerUps.add("powerball");
        Random rand = new Random();
        String random = this.listPowerUps.get(rand.nextInt(this.listPowerUps.size()));
        return random;
    }
}
