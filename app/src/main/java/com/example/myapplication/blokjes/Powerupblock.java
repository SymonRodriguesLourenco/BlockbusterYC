package com.example.myapplication.blokjes;

public class Powerupblock extends Block{

    String powerup;

    public Powerupblock(int X, int Y, int width, int height, int hitsLeft, String powerup) {
        super(X, Y, width, height, hitsLeft);
        this.powerup = powerup;
    }
}
