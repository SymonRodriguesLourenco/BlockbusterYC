package com.example.myapplication.blokjes;

public class Powerupblock extends Block{

    String powerup;

    public Powerupblock(int X, int Y, int width, int height, String powerup) {
        super(X, Y, width, height,  1);
        this.powerup = powerup;
    }

    public String getPowerup() {
        return powerup;
    }
}
