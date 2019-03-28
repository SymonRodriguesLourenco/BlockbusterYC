package com.example.myapplication.blokjes;

public class Finish extends Block {

    public Finish(int X, int Y, int width, int height) {
        super(X, Y, width, height, -1);
    }


    public boolean hit(int balX,int balY, int width, int height, int per){
        per = 100 - per;
        if (minX < balX+width/100*per && balX+width/100*per < maxX &&  balY+height/100*(100-per) < minY && balY+height/100*per > maxY) {
            return true;
        }
        else{
            return false;
        }
    }
}
