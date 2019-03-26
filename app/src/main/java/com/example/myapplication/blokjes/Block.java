package com.example.myapplication.blokjes;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.myapplication.R;

public abstract class Block extends Activity{

    int width, height;
    int minX, minY, maxX, maxY;
    int hitsLeft;
    boolean fromUp, fromLeft, fromDown, fromRight;
    Bitmap blockStandard;

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

    public boolean isFromUp() {
        return fromUp;
    }

    public boolean isFromLeft() {
        return fromLeft;
    }

    public boolean isFromDown() {
        return fromDown;
    }

    public boolean isFromRight() {
        return fromRight;
    }

    public void setFromUp(boolean fromUp) {
        this.fromUp = fromUp;
    }

    public void setFromLeft(boolean fromLeft) {
        this.fromLeft = fromLeft;
    }

    public void setFromDown(boolean fromDown) {
        this.fromDown = fromDown;
    }

    public void setFromRight(boolean fromRight) {
        this.fromRight = fromRight;
    }

    public void remove() {
        this.hitsLeft--;
        if(this.hitsLeft == 0){
            this.minX = -300;
            this.maxX = -200;
            this.minY = -200;
            this.maxY = -100;
        }
    }

    public boolean hit(int balX,int balY, int width, int height){
        if (minX < balX+width && balX < maxX && minY > balY && balY+height > maxY) {
            return true;
        }
        else{
            return false;
        }
    }

    public void bounce(int speedX, int speedY, int ballX, int ballY, int width, int height, boolean goingUp, boolean goingRight){
        reset();
        int cballX, cballY;
        boolean c1, c2, c3;
        if(goingRight && goingUp) {
            cballX = ballX + speedX;
            c1 = hit(cballX, ballY, width, height);
            cballY = ballY - speedY;
            c2 = hit(ballX, cballY, width, height);
            c3 = hit(cballX, cballY, width, height);
            if (c1) {
                fromLeft = true;
            }
            if (c2) {
                fromDown = true;
            }
            if (!c1 && !c2 && c3) {
                fromLeft = true;
                fromDown = true;
            }
        }
        else if(!goingRight && goingUp){
            cballX = ballX - speedX;
            c1 = hit(cballX, ballY, width, height);
            cballY = ballY - speedY;
            c2 = hit(ballX, cballY, width, height);
            c3 = hit(cballX, cballY, width, height);
            if (c1){
                fromRight = true;
            }
            if (c2){
                fromDown = true;
            }
            if (!c1 && !c2 && c3){
                fromRight = true;
                fromDown = true;
            }
        }
        else if(goingRight && !goingUp) {
            cballX = ballX + speedX;
            c1 = hit(cballX, ballY, width, height);
            cballY = ballY + speedY;
            c2 = hit(ballX, cballY, width, height);
            c3 = hit(cballX, cballY, width, height);
            if (c1) {
                fromLeft = true;
            }
            if (c2) {
                fromUp = true;
            }
            if (!c1 && !c2 && c3) {
                fromLeft = true;
                fromUp = true;
            }
        }
        else if(!goingRight && !goingUp){
            cballX = ballX - speedX;
            c1 = hit(cballX, ballY, width, height);
            cballY = ballY + speedY;
            c2 = hit(ballX, cballY, width, height);
            c3 = hit(cballX, cballY, width, height);
            if (c1){
                fromRight = true;
            }
            if (c2){
                fromUp = true;
            }
            if (!c1 && !c2 && c3){
                fromRight = true;
                fromUp = true;
            }
        }
    }

    public void reset(){
        fromLeft = false;
        fromRight = false;
        fromUp = false;
        fromDown = false;
    }
}
