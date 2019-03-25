package com.example.myapplication.blokjes;

import android.app.Activity;

public class Blokje extends Activity {

    int width, height;
    int minX, minY, maxX, maxY;

    public Blokje(int X, int Y, int width, int height) {
        this.width = width;
        this.height = height;
        this.minX = X - width/2;
        this.maxX = minX + width;
        this.maxY = Y - height/2;
        this.minY = maxY + height;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxX() {
        return maxX;
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

    public boolean hit(int balX,int balY, int width, int height){
        if (minX < balX+width && balX < maxX && minY > balY && balY+height > maxY) {
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean hitFinish(int balX, int balY, int finishX, int finishY, int width, int height, int ballwidth, int ballheight){
        if (finishX+ballwidth < balX+ballwidth && balX < finishX+width-ballwidth && finishY+ballheight < balY+ballheight && balY < finishY+height-ballheight){
            return true;
        }
        else{
            return false;
        }
    }



}
