package com.example.myapplication.blokjes;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.myapplication.R;

public abstract class Block extends Activity{

    protected int width, height;
    protected int minX, minY, maxX, maxY;
    private int hitsLeft;
    private boolean fromUp, fromLeft, fromDown, fromRight;
    protected Bitmap img;

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

    public boolean isFromUp() {
        return this.fromUp;
    }

    public boolean isFromLeft() {
        return this.fromLeft;
    }

    public boolean isFromDown() {
        return this.fromDown;
    }

    public boolean isFromRight() {
        return this.fromRight;
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
        if (this.minX < balX+width && balX < this.maxX && this.minY > balY && balY+height > this.maxY) {
            return true;
        }
        else{
            return false;
        }
    }

    public void bounce(int speedX, int speedY, int ballX, int ballY, int width, int height, boolean goingUp, boolean goingRight){
        reset();
        int checkBallX, checkBallY;
        boolean checkOne, checkTwo, checkThree;
        if(goingRight && goingUp) {
            checkBallX = ballX + speedX;
            checkOne = hit(checkBallX, ballY, width, height);
            checkBallY = ballY - speedY;
            checkTwo = hit(ballX, checkBallY, width, height);
            checkThree = hit(checkBallX, checkBallY, width, height);
            if (checkOne) {
                this.fromLeft = true;
            }
            if (checkTwo) {
                this.fromDown = true;
            }
            if (!checkOne && !checkTwo && checkThree) {
                this.fromLeft = true;
                this.fromDown = true;
            }
        }
        else if(!goingRight && goingUp){
            checkBallX = ballX - speedX;
            checkOne = hit(checkBallX, ballY, width, height);
            checkBallY = ballY - speedY;
            checkTwo = hit(ballX, checkBallY, width, height);
            checkThree = hit(checkBallX, checkBallY, width, height);
            if (checkOne){
                this.fromRight = true;
            }
            if (checkTwo){
                this.fromDown = true;
            }
            if (!checkOne && !checkTwo && checkThree){
                this.fromRight = true;
                this.fromDown = true;
            }
        }
        else if(goingRight && !goingUp) {
            checkBallX = ballX + speedX;
            checkOne = hit(checkBallX, ballY, width, height);
            checkBallY = ballY + speedY;
            checkTwo = hit(ballX, checkBallY, width, height);
            checkThree = hit(checkBallX, checkBallY, width, height);
            if (checkOne) {
                this.fromLeft = true;
            }
            if (checkTwo) {
                this.fromUp = true;
            }
            if (!checkOne && !checkTwo && checkThree) {
                this.fromLeft = true;
                this.fromUp = true;
            }
        }
        else if(!goingRight && !goingUp){
            checkBallX = ballX - speedX;
            checkOne = hit(checkBallX, ballY, width, height);
            checkBallY = ballY + speedY;
            checkTwo = hit(ballX, checkBallY, width, height);
            checkThree = hit(checkBallX, checkBallY, width, height);
            if (checkOne){
                this.fromRight = true;
            }
            if (checkTwo){
                this.fromUp = true;
            }
            if (!checkOne && !checkTwo && checkThree){
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
            this.hitsLeft --;
            return false;
        }
    }
}
