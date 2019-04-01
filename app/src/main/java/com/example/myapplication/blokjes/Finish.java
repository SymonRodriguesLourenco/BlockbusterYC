package com.example.myapplication.blokjes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.RelativeLayout;

import com.example.myapplication.R;

public class Finish extends Block {

    public Finish(int X, int Y, int width, int height, Resources resources) {
        super(X, Y, width, height, -1);
        this.img = BitmapFactory.decodeResource(resources, R.drawable.block2);
        this.img = Bitmap.createScaledBitmap(this.img, this.height, this.height, false);
    }


    public boolean hit(int balX,int balY, int width, int height, int per){
        per = 100 - per;
        if (minX < balX+width/100*per && balX+width/100*per < maxX &&  balY+height/100*(100-per) < minY && balY+height/100*per > maxY) {
            return true;
        }
        else{
            return false;
        }
    }
}
