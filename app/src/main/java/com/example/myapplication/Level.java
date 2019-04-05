package com.example.myapplication;

import android.content.res.Resources;
import android.util.Log;

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
    private int levens, pogingen, score;
    private int level;


    public Level(int dWidth, int dHeight, Resources resources){
        this.levens = 3;
        this.pogingen = 3;
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
        return level;
    }

    public void levelUp(){
        this.level ++;
    }

    public int getScore() {
        return score;
    }

    public int getLevens() {
        return levens;
    }

    public void resetPogingen(){
        this.pogingen = 3;
    }

    public void addLevens(){
        this.levens ++;
    }

    public void addPogingen(){
        this.pogingen ++;
    }

    public void subsLevens(){
        this.levens -= 1;
        resetLevel();
    }

    public void subsPogingen(){
        this.pogingen --;
    }

    public int getPogingen() {
        return pogingen;
    }

    public void resetLevel(){
        this.clear();
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

    private void Level1() {
        ArrayList<Block> tijdelijk = new ArrayList<>();
        int blockdim = dHeight/100*20;
        int finishWidth = blockdim*1;
        int finishHeight= blockdim*1;
        tijdelijk.add(new Soft(dWidth/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Finish(dWidth-blockdim/2, dHeight/2, finishWidth, finishHeight, resources));
        this.add(tijdelijk);
    }

    private void Level2() {
        ArrayList<Block> tijdelijk = new ArrayList<>();
        int blockdim = dHeight/100*15;
        int finishWidth = blockdim;
        int finishHeight= dHeight-padding;
        tijdelijk.add(new Finish(dWidth-blockdim/2, dHeight/2+blockdim/4, finishWidth, finishHeight, resources));
        tijdelijk.add(new Hard(dWidth-blockdim - blockdim/2, padding+blockdim/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard(dWidth-blockdim - blockdim/2, padding+blockdim/2+blockdim, blockdim, blockdim, resources));
        tijdelijk.add(new Hard(dWidth-blockdim - blockdim/2, dHeight-blockdim/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard(dWidth-blockdim - blockdim/2, dHeight-blockdim/2-blockdim, blockdim, blockdim, resources));
        tijdelijk.add(new Powerupblock(dWidth/2, dHeight/2, blockdim*2, blockdim*2, "", resources));
        this.add(tijdelijk);
    }
    private void Level3() {
        ArrayList<Block> tijdelijk = new ArrayList<>();
        int blockdim = dHeight/100*15;
        int finishWidth = blockdim;
        int finishHeight= dHeight-padding;
        tijdelijk.add(new Finish(dWidth-blockdim/2, dHeight/2+blockdim/4, finishWidth, finishHeight, resources));
        tijdelijk.add(new Hard(dWidth/2, padding+blockdim/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard(dWidth/2, dHeight - blockdim*4 - blockdim/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard(dWidth/2, dHeight - blockdim*3 - blockdim/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard(dWidth/2, dHeight - blockdim*2 - blockdim/2, blockdim, blockdim, resources));
        tijdelijk.add(new Medium(dWidth/2, dHeight - blockdim*1 - blockdim/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard(dWidth/2, dHeight - blockdim/2, blockdim, blockdim, resources));
        this.add(tijdelijk);
    }

    private void Level4() {
        ArrayList<Block> tijdelijk = new ArrayList<>();
        int blockdim = dHeight/100*20;
        tijdelijk.add(new Hard(dWidth/2, padding+blockdim/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard(dWidth/2, dHeight- padding-blockdim/2, blockdim, dHeight/100*20, resources));
        tijdelijk.add(new Finish(dWidth-150, dHeight/2, 100, 100, resources));
        this.add(tijdelijk);
    }

    private void Level5() {
        ArrayList<Block> tijdelijk = new ArrayList<>();
        int blockdim = dHeight/100*20;
        tijdelijk.add(new Hard(dWidth/2, padding+blockdim/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard(dWidth/2, dHeight- padding-blockdim/2, blockdim, dHeight/100*20, resources));
        tijdelijk.add(new Finish(dWidth-150, dHeight/100*30, 250, 250, resources));
        this.add(tijdelijk);
    }

    private void Level6() {
        ArrayList<Block> tijdelijk = new ArrayList<>();
        int blockdim = dHeight/100*20;
        int blockdim2 = dHeight/100*15;
        int finishWidth = blockdim;
        tijdelijk.add(new Powerupblock (dWidth/4, blockdim*2, blockdim2, blockdim2, "", resources));
        tijdelijk.add(new Soft (dWidth/4, blockdim*4, blockdim2, blockdim2, resources));
        tijdelijk.add(new Hard (dWidth/2, blockdim/2+padding, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth/2, dHeight/2+padding/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth/2, dHeight-blockdim/2, blockdim, blockdim, resources));
        tijdelijk.add(new Soft(dWidth-dWidth/4, blockdim*2, blockdim2, blockdim2, resources));
        tijdelijk.add(new Powerupblock (dWidth-dWidth/4, blockdim*4, blockdim2, blockdim2, "", resources));
        tijdelijk.add(new Finish(dWidth-blockdim/2, dHeight/2+blockdim/4, finishWidth, finishWidth, resources));
        this.add(tijdelijk);
    }

    private void Level7() {
        ArrayList<Block> tijdelijk = new ArrayList<>();
        int blockdim = dHeight/100*15;
        int finishWidth = blockdim;
        tijdelijk.add(new Medium (dWidth/2-blockdim, dHeight/2-finishWidth, blockdim, blockdim, resources));
        tijdelijk.add(new Soft(dWidth/2-blockdim, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Medium (dWidth/2-blockdim, dHeight/2+finishWidth, blockdim, blockdim, resources));
        tijdelijk.add(new Soft (dWidth/2, dHeight/2-finishWidth, blockdim, blockdim, resources));
        tijdelijk.add(new Finish(dWidth/2, dHeight/2, finishWidth, finishWidth, resources));
        tijdelijk.add(new Soft (dWidth/2, dHeight/2+finishWidth, blockdim, blockdim, resources));
        this.add(tijdelijk);
    }

    private void Level8(){
        ArrayList<Block> tijdelijk = new ArrayList<>();
        int blockdim = dHeight/100*15;
        int finishWidth = blockdim;
        tijdelijk.add(new Hard (dWidth/2-blockdim, dHeight/2-finishWidth, blockdim, blockdim, resources));
        tijdelijk.add(new Hard(dWidth/2-blockdim, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth/2-blockdim, dHeight/2+finishWidth, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth/2, dHeight/2-finishWidth, blockdim, blockdim, resources));
        tijdelijk.add(new Finish(dWidth/2, dHeight/2, finishWidth, finishWidth, resources));
        tijdelijk.add(new Hard (dWidth/2, dHeight/2+finishWidth, blockdim, blockdim, resources));
        tijdelijk.add(new Powerupblock(blockdim*2, dHeight-blockdim/2, blockdim, blockdim, "", resources));
        this.add(tijdelijk);
    }

    private void Level9(){
        ArrayList<Block> tijdelijk = new ArrayList<>();
        int blockdim = dHeight/100*15;
        int finishWidth = blockdim;
        int finishHeight= blockdim;
        tijdelijk.add(new Hard (dWidth-blockdim*9-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth-blockdim*8-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth-blockdim*7-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth-blockdim*6-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth-blockdim*5-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth-blockdim*4-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth-blockdim*3-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth-blockdim*2-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth-blockdim-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Finish(dWidth-blockdim/2, dHeight/2, finishWidth, finishHeight, resources));
        this.add(tijdelijk);
    }

    private void Level10(){
        ArrayList<Block> tijdelijk = new ArrayList<>();
        int blockdim = dHeight/100*15;
        int finishWidth = blockdim;
        int finishHeight= blockdim;
        tijdelijk.add(new Hard (dWidth-blockdim*9-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Soft (dWidth-blockdim*8-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth-blockdim*7-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Medium (dWidth-blockdim*6-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth-blockdim*5-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Soft (dWidth-blockdim*4-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth-blockdim*3-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Medium (dWidth-blockdim*2-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard (dWidth-blockdim-blockdim/2, dHeight/2, blockdim, blockdim, resources));
        tijdelijk.add(new Finish(dWidth-blockdim/2, dHeight/2, finishWidth, finishHeight, resources));
        this.add(tijdelijk);
    }
}