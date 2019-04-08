package com.example.myapplication.blokjes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;

public class Hard extends Block {

    public Hard(int X, int Y, int width, int height, Resources resources) {
        super(X, Y, width, height, -1);
        this.img = BitmapFactory.decodeResource(resources, R.drawable.pumpkin);
        this.img = Bitmap.createScaledBitmap(this.img, height, height, false);
    }

}
