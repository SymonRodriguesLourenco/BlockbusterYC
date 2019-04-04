package com.example.myapplication;

public class Cursor {

    private int intervalX, intervalY;
    private int maxCursorX, maxCursorY, bigCursorX, bigCursorY, medCursorX, medCursorY, minCursorX, minCursorY;
    private int ballSize;
    private int maxCursorSize, bigCursorSize, medCursorSize, minCursorSize;
    private double standardspeed;
    private double vergrootX, vergrootY;

    public Cursor(int size,int ballSize){
        remove();
        this.maxCursorSize = size;
        this.bigCursorSize = size-10;
        this.medCursorSize = size-20;
        this.minCursorSize = size-30;
        this.ballSize = ballSize;
        this.standardspeed = Math.sqrt(650);
    }

    public void remove(){
        this.maxCursorX = -1000;
        this.bigCursorX = -1000;
        this.medCursorX = -1000;
        this.minCursorX = -1000;
    }

    public void vergroot(int x, int y, int dHeight){
        this.intervalX = (50 + this.ballSize / 2 - x);
        this.intervalY = (dHeight / 2 - y);
        double slope = Math.sqrt(this.intervalX * this.intervalX + this.intervalY * this.intervalY);
        double vergroting = standardspeed / slope;
        this.vergrootX = vergroting * this.intervalX;
        this.vergrootY = vergroting * this.intervalY;
    }

    public void coords(int dHeight){
        bigCursorX = -(int) this.vergrootX * 65 / 10 + (this.ballSize / 2 + 50 - bigCursorSize / 2);
        bigCursorY = -(int) this.vergrootY * 65 / 10 + (dHeight / 2 - bigCursorSize / 2);
        medCursorX = -(int) this.vergrootX * 4 + (this.ballSize / 2 + 50 - medCursorSize / 2);
        medCursorY = -(int) this.vergrootY * 4 + (dHeight / 2 - medCursorSize / 2);
        minCursorX = -(int) this.vergrootX * 2 + (this.ballSize / 2 + 50 - minCursorSize / 2);
        minCursorY = -(int) this.vergrootY * 2 + (dHeight / 2 - minCursorSize / 2);
        maxCursorX = -(int) this.vergrootX * 95 / 10 + (this.ballSize / 2 + 50 - maxCursorSize / 2);
        maxCursorY = -(int) this.vergrootY * 95 / 10 + (dHeight / 2 - maxCursorSize / 2);
    }

    public int getMaxCursorSize() {
        return maxCursorSize;
    }

    public int getBigCursorSize() {
        return bigCursorSize;
    }

    public int getMedCursorSize() {
        return medCursorSize;
    }

    public int getMinCursorSize() {
        return minCursorSize;
    }

    public int getMaxCursorX() {
        return maxCursorX;
    }

    public int getMaxCursorY() {
        return maxCursorY;
    }

    public int getBigCursorX() {
        return bigCursorX;
    }

    public int getBigCursorY() {
        return bigCursorY;
    }

    public int getMedCursorX() {
        return medCursorX;
    }

    public int getMedCursorY() {
        return medCursorY;
    }

    public int getMinCursorX() {
        return minCursorX;
    }

    public int getMinCursorY() {
        return minCursorY;
    }

    public double getVergrootX() {
        return vergrootX;
    }

    public double getVergrootY() {
        return vergrootY;
    }
}
