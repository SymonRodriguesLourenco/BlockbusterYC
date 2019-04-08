package com.example.myapplication;

public class Cursor {

    private int intervalX, intervalY;
    private int maxCursorX, maxCursorY, bigCursorX, bigCursorY, medCursorX, medCursorY, minCursorX, minCursorY;
    private int ballSize;
    private int maxCursorSize, bigCursorSize, medCursorSize, minCursorSize;
    private double standardspeed;
    private double factorX, factorY;

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

    public void endXEndY(int x, int y, int dHeight){
        this.intervalX = (50 + this.ballSize / 2 - x);
        this.intervalY = (dHeight / 2 - y);
        double slope = Math.sqrt(this.intervalX * this.intervalX + this.intervalY * this.intervalY);
        double factor = standardspeed / slope;
        this.factorX = factor * this.intervalX;
        this.factorY = factor * this.intervalY;
    }

    public void coords(int dHeight){
        this.bigCursorX = -(int) this.factorX * 65 / 10 + (this.ballSize / 2 + 50 - this.bigCursorSize / 2);
        this.bigCursorY = -(int) this.factorY * 65 / 10 + (dHeight / 2 - this.bigCursorSize / 2);
        this.medCursorX = -(int) this.factorX * 4 + (this.ballSize / 2 + 50 - this.medCursorSize / 2);
        this.medCursorY = -(int) this.factorY * 4 + (dHeight / 2 - this.medCursorSize / 2);
        this.minCursorX = -(int) this.factorX * 2 + (this.ballSize / 2 + 50 - this.minCursorSize / 2);
        this.minCursorY = -(int) this.factorY * 2 + (dHeight / 2 - this.minCursorSize / 2);
        this.maxCursorX = -(int) this.factorX * 95 / 10 + (this.ballSize / 2 + 50 - this.maxCursorSize / 2);
        this.maxCursorY = -(int) this.factorY * 95 / 10 + (dHeight / 2 - this.maxCursorSize / 2);
    }

    public int getMaxCursorSize() {
        return this.maxCursorSize;
    }

    public int getBigCursorSize() {
        return this.bigCursorSize;
    }

    public int getMedCursorSize() {
        return this.medCursorSize;
    }

    public int getMinCursorSize() {
        return this.minCursorSize;
    }

    public int getMaxCursorX() {
        return this.maxCursorX;
    }

    public int getMaxCursorY() {
        return this.maxCursorY;
    }

    public int getBigCursorX() {
        return this.bigCursorX;
    }

    public int getBigCursorY() {
        return this.bigCursorY;
    }

    public int getMedCursorX() {
        return this.medCursorX;
    }

    public int getMedCursorY() {
        return this.medCursorY;
    }

    public int getMinCursorX() {
        return this.minCursorX;
    }

    public int getMinCursorY() {
        return this.minCursorY;
    }

    public double getFactorX() {
        return this.factorX;
    }

    public double getFactorY() {
        return this.factorY;
    }
}
