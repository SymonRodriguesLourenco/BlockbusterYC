package com.example.myapplication;

import android.content.res.Resources;
import android.util.Log;

import com.example.myapplication.blokjes.Block;
import com.example.myapplication.blokjes.Finish;
import com.example.myapplication.blokjes.Hard;
import com.example.myapplication.blokjes.Medium;
import com.example.myapplication.blokjes.Soft;

import java.util.ArrayList;
import java.util.List;

public class Level extends ArrayList<ArrayList<Block>>{

    int dWidth, dHeight;
    int bWidth,bHeight;
    Resources resources;
    ArrayList<Block> tijdelijk;

    public Level(int dWidth, int dHeight, int bWidth, int bHeight, Resources resources){
        this.dWidth = dWidth;
        this.dHeight = dHeight;
        this.bWidth = bWidth;
        this.bHeight = bHeight;
        this.resources = resources;
        this.tijdelijk = new ArrayList<>();
        Level1();
        Level2();
    }

    private void Level1() {
        tijdelijk.clear();
        tijdelijk.add(new Hard(dWidth/2, dHeight/100*25, 300, 300, resources));
        tijdelijk.add(new Medium(dWidth/2, dHeight/100*50, bWidth, bHeight, resources));
        tijdelijk.add(new Soft(dWidth/2, dHeight/100*75, bWidth, bHeight, resources));
        tijdelijk.add(new Finish(dWidth-150, dHeight/2, 300, 300, resources));
        this.add(tijdelijk);
    }

    private void Level2() {
        tijdelijk.clear();
        tijdelijk.add(new Soft(dWidth/2, dHeight/100*25, bWidth, bHeight, resources));
        tijdelijk.add(new Medium(dWidth/2, dHeight/100*50, bWidth, bHeight, resources));
        tijdelijk.add(new Hard(dWidth/2, dHeight/100*75, bWidth, bHeight, resources));
        tijdelijk.add(new Finish(dWidth-150, dHeight/2, 300, 300, resources));
        this.add(tijdelijk);
    }

}
