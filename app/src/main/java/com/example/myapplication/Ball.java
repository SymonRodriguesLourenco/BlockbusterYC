package com.example.myapplication;

public class Ball {

    private int ballX, ballY;
    private int width, height;
    private int speedX, speedY;
    private boolean goingForward, goingUp;
    private boolean fired, outOfBounds;
    private String ballPowerup;
    private boolean invertX, invertY;
    private int countY, countX;
    private boolean sound;

    public boolean isOutOfBounds() {
        return this.outOfBounds;
    }

    public void setOutOfBounds(boolean outOfBounds) {
        this.outOfBounds = outOfBounds;
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
        this.outOfBounds = true;
        this.sound = false;
    }

    public int getBallX() {
        return this.ballX;
    }

    public int getBallY() {
        return this.ballY;
    }

    public int getSpeedX() {
        return this.speedX;
    }

    public int getSpeedY() {
        return this.speedY;
    }

    public String getBallPowerup() {
        return this.ballPowerup;
    }

    public void startPosition(int dheight) {
        this.outOfBounds = false;
        this.ballX = 50;
        this.ballY = dheight/2 - this.height/2;
        this.goingForward = true;
        this.goingUp = false;
    }

    public boolean isGoingForward() {
        return this.goingForward;
    }

    public boolean isGoingUp() {
        return this.goingUp;
    }

    public boolean isFired() {
        return this.fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public void setBallPowerup(String ballPowerup){
        this.ballPowerup = ballPowerup;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public boolean borderBounce(int dWidth, int dHeight){
//      checken of de bal uit het scherm is:
        int minX = 0;
        int minY = 0;
        int maxX = dWidth - this.width;
        int maxY = dHeight - this.height;
        boolean result = false;
        this.sound = false;

//          bal gaat naar links
        if ((ballX - speedX) < minX - (this.width *2)) {
            if ((ballX - speedX) < minX) {
//                ballX = minX;
                result = true;
            }
            return result;
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
        return result;

    }

    public boolean isSound() {
        return this.sound;
    }

    public void setPos(int ballX, int ballY) {
        this.ballX = ballX;
        this.ballY = ballY;
    }

    public void invert(){
        if (this.invertX){
            if (this.countY > 1){
                this.invertX = false;
            } else {
                if (this.goingForward) {
                    this.goingForward = false;
                } else {
                    this.goingForward = true;
                }
                this.invertX = false;
            }
        }
        if (this.invertY) {
            if (this.countX > 1){
                this.invertY = false;
            } else {
                if (this.goingUp) {
                    this.goingUp = false;
                } else {
                    this.goingUp = true;
                }
                this.invertY = false;
            }
        }
        this.countX = 0;
        this.countY = 0;
    }

    public void ballSpeed(double factorX, double factorY){
        int tempX = -(int) factorX;
        int tempY = -(int) factorY;
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