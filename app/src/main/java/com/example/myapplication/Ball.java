package com.example.myapplication;

import com.example.myapplication.blokjes.Block;

public class Ball {

    private int ballX, ballY;
    private int width, height;
    private int speedX, speedY;
    private double standardspeed;
    private boolean goingForward, goingUp;
    private boolean fired, uitscherm;
    private String ballPowerup;
    private boolean invertX, invertY;
    private int countY, countX;

    public boolean isUitscherm() {
        return uitscherm;
    }

    public void setUitscherm(boolean uitscherm) {
        this.uitscherm = uitscherm;
    }

    public Ball(int width, int height, String ballPowerup) {
        this.width = width;
        this.height = height;
        this.speedX = 0;
        this.speedY = 0;
        this.standardspeed = Math.sqrt(800);
        this.fired = false;
        this.goingUp = false;
        this.goingForward = true;
        this.ballPowerup = ballPowerup;
        this.uitscherm = true;
    }

    public int getBallX() {
        return ballX;
    }

    public int getBallY() {
        return ballY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public String getBallPowerup() {
        return ballPowerup;
    }

    public void startPosition(int dheight) {
        this.uitscherm = false;
        this.ballX = 50;
        this.ballY = dheight/2 - height/2;
        this.goingForward = true;
        this.goingUp = false;
    }

    public boolean isGoingForward() {
        return goingForward;
    }

    public void setGoingForward(boolean goingForward) {
        this.goingForward = goingForward;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public double getStandardspeed(){
        return standardspeed;
    }

    public void setBallPowerup(String ballPowerup){
        this.ballPowerup = ballPowerup;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public boolean borderBounce(int dWidth, int dHeight){
//      checken of de bal uit het scherm is:
        int minX = 0;
        int minY = 0;
        int maxX = dWidth - width;
        int maxY = dHeight - height;
        boolean uitkomst = false;



//          bal gaat naar links
        if ((ballX - speedX) < minX - (this.width *2)) {
            if ((ballX - speedX) < minX) {
//                ballX = minX;
                uitkomst = true;
            }
            return uitkomst;
        }

//          bal gaat naar rechts
        if ((ballX + speedX) >= maxX) {
            if ((ballX + speedX) > maxX) {
                ballX = maxX;
            }
            goingForward = false;
        }

//          bal gaat naar boven
        if ((ballY - speedY) < minY) {
            if ((ballY - speedY) < minY) {
                ballY = minY;
            }
            goingUp = false;
        }
//          bal gaat naar onder
        if ((ballY + speedY) >= maxY) {
            if ((ballY + speedY) > maxY) {
                ballY = maxY;
            }
            goingUp = true;
        }
        if (goingForward) {
            this.ballX += speedX;
        }
        else if (!goingForward) {
            this.ballX -= speedX;
        }
        if (!goingUp) {
            this.ballY += speedY;
        }
        else if (goingUp) {
            this.ballY -= speedY;
        }
        return uitkomst;

    }

    public void setInvertX(boolean invertX) {
        this.invertX = invertX;
    }

    public void setInvertY(boolean invertY) {
        this.invertY = invertY;
    }

    public void setPos(int ballX, int ballY) {
        this.ballX = ballX;
        this.ballY = ballY;
    }

    public void countXadd(){
        this.countX ++;
    }

    public void countYadd(){
        this.countY ++;
    }

    public void invert(){
        if (invertX){
            if (countY > 1){
                invertX = false;
            } else {
                if (goingForward) {
                    goingForward = false;
                } else {
                    goingForward = true;
                }
                invertX = false;
            }
        }
        if (invertY) {
            if (countX > 1){
                invertY = false;
            } else {
                if (goingUp) {
                    goingUp = false;
                } else {
                    goingUp = true;
                }
                invertY = false;
            }
        }
        countX = 0;
        countY = 0;
    }
}