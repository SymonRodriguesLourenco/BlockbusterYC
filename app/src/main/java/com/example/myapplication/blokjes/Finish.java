package com.example.myapplication.blokjes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;

public class Finish extends Block {

    public Finish(int X, int Y, int width, int height, Resources resources) {
        super(X, Y, width, height, -1);
        this.img = BitmapFactory.decodeResource(resources, R.drawable.finish);
        this.img = Bitmap.createScaledBitmap(this.img, height, height, false);
    }


    public boolean hit(int balX,int balY, int width, int height, int percentage){
        percentage = 100 - percentage;
        if (this.minX < balX+width/100*percentage && balX+width/100*percentage < this.maxX &&  balY+height/100*(100-percentage) < this.minY && balY+height/100*percentage > this.maxY) {
            return true;
        }
        else{
            return false;
        }
    }
}
