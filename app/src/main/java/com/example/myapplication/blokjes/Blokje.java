package com.example.myapplication.blokjes;

import android.app.Activity;
import android.graphics.Rect;

public class Blokje extends Activity {

    int width, height;
    int minX, minY, maxX, maxY;
    boolean fromUp, fromLeft, fromDown, fromRight;

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


    public void bounce(int speedX, int speedY, int ballX, int ballY, int width, int height, boolean goingUp, boolean goingRight){
        reset();
        if (ballX + width + Math.abs(speedX) >  minX && ballX + width < minX){
            if (ballY + height > maxY && ballY < minY){
                fromLeft = true;
            }
            else if (ballY + height + speedY > maxY && goingUp) {
                fromLeft = true;
            }
            else if (ballY - speedY < minY && !goingUp) {
                fromLeft = true;
            }
        }
        else if (ballX - Math.abs(speedX) < maxX && ballX > maxX){
            if (ballY + height > maxY && ballY < minY){
                fromRight = true;
            }
            else if (ballY + height + speedY > maxY && goingUp) {
                fromRight = true;
            }
            else if (ballY - speedY < minY && !goingUp) {
                fromRight = true;
            }
        }
        if (ballY + height + Math.abs(speedY) > maxY && ballY + height < maxY && ballX + width > minX && ballX < minX) {
            fromUp = true;
        }
        else if (ballY - Math.abs(speedY) < minY && ballY > minY && ballX + width > minX && ballX < minX) {
            fromDown = true;
        }
    }

    public void reset(){
        fromLeft = false;
        fromRight = false;
        fromUp = false;
        fromDown = false;
    }



}
