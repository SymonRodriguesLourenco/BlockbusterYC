package com.example.myapplication.blokjes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;

public class Powerupblock extends Block{

    String powerup;

    public Powerupblock(int X, int Y, int width, int height, String powerup, Resources resources) {
        super(X, Y, width, height,  1);
        this.powerup = powerup;
        this.img = BitmapFactory.decodeResource(resources, R.drawable.tomaat);
        this.img = Bitmap.createScaledBitmap(this.img, this.height, this.height, false);
    }

    public String getPowerup() {
        return powerup;
    }
}
