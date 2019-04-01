package com.example.myapplication.blokjes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;

public class Medium extends Block {

    public Medium(int X, int Y, int width, int height, Resources resources) {
        super(X, Y, width, height, 2);
        this.img = BitmapFactory.decodeResource(resources, R.drawable.bloemkool);
        this.img = Bitmap.createScaledBitmap(this.img, this.height, this.height, false);
    }

}
