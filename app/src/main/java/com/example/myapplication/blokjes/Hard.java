package com.example.myapplication.blokjes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;

public class Hard extends Block {

    public Hard(int X, int Y, int width, int height, Resources resources) {
        super(X, Y, width, height, 10);
        this.img = BitmapFactory.decodeResource(resources, R.drawable.pompoen);
        this.img = Bitmap.createScaledBitmap(this.img, this.height, this.height, false);
    }

}
