package com.example.myapplication;

public class Ball {

    private int ballX, ballY;
    private int width;
    private int height;
    private int speedX, speedY;
    private double standardspeed;
    private boolean goingForward, goingUp;
    private boolean fired;
    private String ballPowerup;

    public Ball(int width, int height, String ballPowerup) {
        this.width = width;
        this.height = height;
        this.speedX = 0;
        this.speedY = 0;
        this.standardspeed = Math.sqrt(800);
        this.fired = false;
        this.goingUp = false;
        this.goingForward = true;
        this.ballPowerup = "none";
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
        return getBallPowerup();
    }

    public void startPosition(int dheight) {
        this.ballX = 50;
        this.ballY = dheight/2 - height/2;
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

    public void setBallPowerball(String ballPowerup){
        this.ballPowerup = ballPowerup;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void borderBounce(int dWidth, int dHeight){
//      checken of de bal uit het scherm is:
        int minX = 0;
        int minY = 0;
        int maxX = dWidth - width;
        int maxY = dHeight - height;

//          bal gaat naar links
        if ((ballX - speedX) < minX) {
            if ((ballX - speedX) < minX) {
                ballX = minX;
            }
            goingForward = true;
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
    }

}