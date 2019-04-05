package com.example.myapplication;

public class Ball {

    private int ballX, ballY;
    private int width, height;
    private int speedX, speedY;
    private boolean goingForward, goingUp;
    private boolean fired, uitscherm;
    private String ballPowerup;
    private boolean invertX, invertY;
    private int countY, countX;
    private boolean sound;

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
        this.fired = false;
        this.goingUp = false;
        this.goingForward = true;
        this.ballPowerup = ballPowerup;
        this.uitscherm = true;
        this.sound = false;
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

    public boolean isGoingUp() {
        return goingUp;
    }

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
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
        this.sound = false;

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
                this.sound = true;
            }
            goingForward = false;
        }

//          bal gaat naar boven
        if ((ballY - speedY) < minY) {
            if ((ballY - speedY) < minY) {
                ballY = minY;
                this.sound = true;
            }
            goingUp = false;
        }
//          bal gaat naar onder
        if ((ballY + speedY) >= maxY) {
            if ((ballY + speedY) > maxY) {
                ballY = maxY;
                this.sound = true;
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

    public boolean isSound() {
        return sound;
    }

    public void setPos(int ballX, int ballY) {
        this.ballX = ballX;
        this.ballY = ballY;
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

    public void ballSpeed(double vergrootX, double vergrootY){
        int tempX = -(int) vergrootX;
        int tempY = -(int) vergrootY;
        if (tempY < 0) {
            tempY *= -1;
            this.goingUp = true;
        }
        if (tempX < 0) {
            tempX *= -1;
            this.goingForward = false;
        }
        this.speedX = tempX;
        this.speedY = tempY;
    }

    public void multiBall(boolean up, int speedX, int speedY){
        if (up){
            this.goingUp = false;
        } else {
            this.goingUp = true;
        }
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void reset(int dHeight){
        this.speedX = 0;
        this.speedY = 0;
        this.startPosition(dHeight);
        this.fired = false;
    }

    public void bounce(boolean up, boolean down, boolean right, boolean left){
        if (left | right){
            this.invertX = true;
            this.countX ++;
        }
        if (up | down){
            this.invertY = true;
            this.countY ++;
        }
    }
}