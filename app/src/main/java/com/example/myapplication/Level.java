package com.example.myapplication;

import android.content.res.Resources;

import com.example.myapplication.blokjes.Block;
import com.example.myapplication.blokjes.Finish;
import com.example.myapplication.blokjes.Hard;
import com.example.myapplication.blokjes.Medium;
import com.example.myapplication.blokjes.Powerupblock;
import com.example.myapplication.blokjes.Soft;

import java.util.ArrayList;

public class Level extends ArrayList<ArrayList<Block>>{

    private int padding;
    private int dWidth, dHeight;
    private Resources resources;
    private int lives, attempt, score;
    private int level;
    private boolean finished, touched;


    public Level(int dWidth, int dHeight, Resources resources){
        this.lives = 3;
        this.attempt = 3;
        this.score = 0;
        this.dWidth = dWidth;
        this.dHeight = dHeight;
        this.resources = resources;
        this.level = 0;
        this.padding = dHeight/100*6;
        Level1();
        Level2();
        Level3();
        Level4();
        Level5();
        Level6();
        Level7();
        Level8();
        Level9();
        Level10();
    }

    public void addScore(int score){
        this.score += score;
    }

    public void subsScore(int score){
        this.score -= score;
    }

    public int getLevel() {
        return this.level;
    }

    public void levelUp(){
        this.level ++;
    }

    public int getScore() {
        return this.score;
    }

    public int getLives() {
        return this.lives;
    }

    public void resetAttempts(){
        this.attempt = 3;
    }

    public void addLife(){
        this.lives++;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void addAttempt(){
        this.attempt++;
    }

    public void subsLife(){
        this.lives -= 1;
        resetLevel();
    }

    public void subsAttempt(){
        this.attempt--;
    }

    public int getAttempt() {
        return this.attempt;
    }

    public void resetLevel(){
        this.clear();
        this.Level1();
        this.Level2();
        this.Level3();
        this.Level4();
        this.Level5();
        this.Level6();
        this.Level7();
        this.Level8();
        this.Level9();
        this.Level10();
    }

    public boolean isTouched() {
        return this.touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    private void Level1() {
        ArrayList<Block> temp = new ArrayList<>();
        int blockdim = dHeight/100*20;
        int finishWidth = blockdim*1;
        int finishHeight= blockdim*1;
        temp.add(new Soft(dWidth/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Finish(dWidth-blockdim/2, dHeight/2, finishWidth, finishHeight, resources));
        this.add(temp);
    }

    private void Level2() {
        ArrayList<Block> temp = new ArrayList<>();
        int blockdim = dHeight/100*15;
        int finishWidth = blockdim;
        int finishHeight= dHeight-padding;
        temp.add(new Finish(dWidth-blockdim/2, dHeight/2+blockdim/4, finishWidth, finishHeight, resources));
        temp.add(new Hard(dWidth-blockdim - blockdim/2, padding+blockdim/2, blockdim, blockdim, resources));
        temp.add(new Hard(dWidth-blockdim - blockdim/2, padding+blockdim/2+blockdim, blockdim, blockdim, resources));
        temp.add(new Hard(dWidth-blockdim - blockdim/2, dHeight-blockdim/2, blockdim, blockdim, resources));
        temp.add(new Hard(dWidth-blockdim - blockdim/2, dHeight-blockdim/2-blockdim, blockdim, blockdim, resources));
        temp.add(new Powerupblock(dWidth/2, dHeight/2, blockdim*2, blockdim*2, "", resources));
        this.add(temp);
    }

    private void Level3() {
        ArrayList<Block> temp = new ArrayList<>();
        int blockdim = dHeight/100*15;
        int finishWidth = blockdim;
        int finishHeight= dHeight-padding;
        temp.add(new Finish(dWidth-blockdim/2, dHeight/2+blockdim/4, finishWidth, finishHeight, resources));
        temp.add(new Hard(dWidth/2, padding+blockdim/2, blockdim, blockdim, resources));
        temp.add(new Hard(dWidth/2, dHeight - blockdim*4 - blockdim/2, blockdim, blockdim, resources));
        temp.add(new Hard(dWidth/2, dHeight - blockdim*3 - blockdim/2, blockdim, blockdim, resources));
        temp.add(new Hard(dWidth/2, dHeight - blockdim*2 - blockdim/2, blockdim, blockdim, resources));
        temp.add(new Medium(dWidth/2, dHeight - blockdim*1 - blockdim/2, blockdim, blockdim, resources));
        temp.add(new Hard(dWidth/2, dHeight - blockdim/2, blockdim, blockdim, resources));
        this.add(temp);
    }

    private void Level4() {
        ArrayList<Block> temp = new ArrayList<>();
        int blockdim = dHeight/100*20;
        temp.add(new Hard(dWidth/2, padding+blockdim/2, blockdim, blockdim, resources));
        temp.add(new Hard(dWidth/2, dHeight- padding-blockdim/2, blockdim, dHeight/100*20, resources));
        temp.add(new Finish(dWidth-150, dHeight/2, 100, 100, resources));
        this.add(temp);
    }

    private void Level5() {
        ArrayList<Block> temp = new ArrayList<>();
        int blockdim = dHeight/100*20;
        temp.add(new Hard(dWidth/2, padding+blockdim/2, blockdim, blockdim, resources));
        temp.add(new Hard(dWidth/2, dHeight- padding-blockdim/2, blockdim, dHeight/100*20, resources));
        temp.add(new Finish(dWidth-150, dHeight/100*30, 250, 250, resources));
        this.add(temp);
    }

    private void Level6() {
        ArrayList<Block> temp = new ArrayList<>();
        int blockdim = dHeight/100*20;
        int blockdimTwo = dHeight/100*15;
        int finishWidth = blockdim;
        temp.add(new Powerupblock (dWidth/4, blockdim*2, blockdimTwo, blockdimTwo, "", resources));
        temp.add(new Soft (dWidth/4, blockdim*4, blockdimTwo, blockdimTwo, resources));
        temp.add(new Hard (dWidth/2, blockdim/2+padding, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth/2, dHeight/2+padding/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth/2, dHeight-blockdim/2, blockdim, blockdim, resources));
        temp.add(new Soft(dWidth-dWidth/4, blockdim*2, blockdimTwo, blockdimTwo, resources));
        temp.add(new Powerupblock (dWidth-dWidth/4, blockdim*4, blockdimTwo, blockdimTwo, "", resources));
        temp.add(new Finish(dWidth-blockdim/2, dHeight/2+blockdim/4, finishWidth, finishWidth, resources));
        this.add(temp);
    }

    private void Level7() {
        ArrayList<Block> temp = new ArrayList<>();
        int blockdim = dHeight/100*15;
        int finishWidth = blockdim;
        temp.add(new Medium (dWidth/2-blockdim, dHeight/2-finishWidth, blockdim, blockdim, resources));
        temp.add(new Soft(dWidth/2-blockdim, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Medium (dWidth/2-blockdim, dHeight/2+finishWidth, blockdim, blockdim, resources));
        temp.add(new Soft (dWidth/2, dHeight/2-finishWidth, blockdim, blockdim, resources));
        temp.add(new Finish(dWidth/2, dHeight/2, finishWidth, finishWidth, resources));
        temp.add(new Soft (dWidth/2, dHeight/2+finishWidth, blockdim, blockdim, resources));
        this.add(temp);
    }

    private void Level8(){
        ArrayList<Block> temp = new ArrayList<>();
        int blockdim = dHeight/100*15;
        int finishWidth = blockdim;
        temp.add(new Hard(dWidth/2-blockdim, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth/2, dHeight/2-finishWidth, blockdim, blockdim, resources));
        temp.add(new Finish(dWidth/2, dHeight/2, finishWidth, finishWidth, resources));
        temp.add(new Hard (dWidth/2, dHeight/2+finishWidth, blockdim, blockdim, resources));
        temp.add(new Powerupblock(blockdim*2, dHeight-blockdim/2, blockdim, blockdim, "", resources));
        this.add(temp);
    }

    private void Level9(){
        ArrayList<Block> temp = new ArrayList<>();
        int blockdim = dHeight/100*15;
        int finishWidth = blockdim;
        int finishHeight= blockdim;
        temp.add(new Hard (dWidth-blockdim*9-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth-blockdim*8-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth-blockdim*7-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth-blockdim*6-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth-blockdim*5-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth-blockdim*4-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth-blockdim*3-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth-blockdim*2-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth-blockdim-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Finish(dWidth-blockdim/2, dHeight/2, finishWidth, finishHeight, resources));
        this.add(temp);
    }

    private void Level10(){
        ArrayList<Block> temp = new ArrayList<>();
        int blockdim = dHeight/100*15;
        int finishWidth = blockdim;
        int finishHeight= blockdim;
        temp.add(new Hard (dWidth-blockdim*9-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Soft (dWidth-blockdim*8-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth-blockdim*7-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Medium (dWidth-blockdim*6-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth-blockdim*5-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Soft (dWidth-blockdim*4-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth-blockdim*3-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Medium (dWidth-blockdim*2-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Hard (dWidth-blockdim-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        temp.add(new Finish(dWidth-blockdim/2, dHeight/2, finishWidth, finishHeight, resources));
        this.add(temp);
    }
}