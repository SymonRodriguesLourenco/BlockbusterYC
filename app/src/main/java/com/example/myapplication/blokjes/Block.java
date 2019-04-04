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
    Bitmap img;

    public Block(int X, int Y, int width, int height, int hitsLeft) {
        this.width = width;
        this.height = height;
        this.hitsLeft = hitsLeft;
        this.minX = X - width/2;
        this.maxX = minX + width;
        this.maxY = Y - height/2;
        this.minY = maxY + height;
        this.hitsLeft = hitsLeft;
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(this.img, this.minX, this.maxY, null);
    }

    public int getHitsLeft() {
        return hitsLeft;
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

    public void setHitsLeft(int hitsLeft) {
        this.hitsLeft = hitsLeft;
    }

    public boolean remove() {
        if(this.hitsLeft == 0){
            this.minX = -500;
            this.maxX = -500;
            this.minY = -500;
            this.maxY = -500;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hit(int balX, int balY, int width, int height){
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
                this.fromLeft = true;
            }
            if (c2) {
                this.fromDown = true;
            }
            if (!c1 && !c2 && c3) {
                this.fromLeft = true;
                this.fromDown = true;
            }
        }
        else if(!goingRight && goingUp){
            cballX = ballX - speedX;
            c1 = hit(cballX, ballY, width, height);
            cballY = ballY - speedY;
            c2 = hit(ballX, cballY, width, height);
            c3 = hit(cballX, cballY, width, height);
            if (c1){
                this.fromRight = true;
            }
            if (c2){
                this.fromDown = true;
            }
            if (!c1 && !c2 && c3){
                this.fromRight = true;
                this.fromDown = true;
            }
        }
        else if(goingRight && !goingUp) {
            cballX = ballX + speedX;
            c1 = hit(cballX, ballY, width, height);
            cballY = ballY + speedY;
            c2 = hit(ballX, cballY, width, height);
            c3 = hit(cballX, cballY, width, height);
            if (c1) {
                this.fromLeft = true;
            }
            if (c2) {
                this.fromUp = true;
            }
            if (!c1 && !c2 && c3) {
                this.fromLeft = true;
                this.fromUp = true;
            }
        }
        else if(!goingRight && !goingUp){
            cballX = ballX - speedX;
            c1 = hit(cballX, ballY, width, height);
            cballY = ballY + speedY;
            c2 = hit(ballX, cballY, width, height);
            c3 = hit(cballX, cballY, width, height);
            if (c1){
                this.fromRight = true;
            }
            if (c2){
                this.fromUp = true;
            }
            if (!c1 && !c2 && c3){
                this.fromRight = true;
                this.fromUp = true;
            }
        }
    }

    public void reset(){
        this.fromLeft = false;
        this.fromRight = false;
        this.fromUp = false;
        this.fromDown = false;
    }
    public boolean hit(String powerup){
        if (powerup.equals("powerball")){
            if (this.hitsLeft < 0){
                setHitsLeft(1);
                return false;
            }
            else {
               setHitsLeft(0);
               return true;
            }
        } else {
            hitsLeft --;
            return false;
        }
    }
}
