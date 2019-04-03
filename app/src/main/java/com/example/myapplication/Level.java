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

    int padding;
    int dWidth, dHeight;
    Resources resources;

    public Level(int dWidth, int dHeight, Resources resources){
        this.dWidth = dWidth;
        this.dHeight = dHeight;
        this.resources = resources;
        padding = dHeight/100*6;
        Level1();
        Level2();
        Level3();
        Level4();
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
        tijdelijk.add(new Powerupblock(dWidth-blockdim - blockdim/2, padding+blockdim/2+blockdim, blockdim, blockdim, "", resources));
        tijdelijk.add(new Hard(dWidth-blockdim - blockdim/2, dHeight-blockdim/2, blockdim, blockdim, resources));
        tijdelijk.add(new Hard(dWidth-blockdim - blockdim/2, dHeight-blockdim/2-blockdim, blockdim, blockdim, resources));
        tijdelijk.add(new Hard(dWidth/2, dHeight/2, blockdim*2, blockdim*2, resources));
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
        tijdelijk.add(new Finish(dWidth-150, dHeight/2, 150, 150, resources));
        this.add(tijdelijk);
    }

}
