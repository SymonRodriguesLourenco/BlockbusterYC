package com.example.myapplication.blokjes;

import android.app.Activity;
import android.graphics.Rect;

public abstract class Block extends Activity {

    int width, height;
    int minX, minY, maxX, maxY;
    int hitsLeft;

    public Block(int X, int Y, int width, int height, int hitsLeft) {
        this.width = width;
        this.height = height;
        this.hitsLeft = hitsLeft;
        this.minX = X - width/2;
        this.maxX = minX + width;
        this.maxY = Y - height/2;
        this.minY = maxY + height;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void remove() {
        this.minX = -300;
        this.maxX = -200;
        this.minY = -200;
        this.maxY = -100;
    }

    public void hit(int balX,int balY, int width, int height){
        if (minX < balX+width && balX < maxX && minY > balY && balY+height > maxY) {
            this.hitsLeft--;
            if(this.hitsLeft == 0){
                this.remove();
            }
        }
    }



}
