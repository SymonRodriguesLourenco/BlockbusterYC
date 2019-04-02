package com.example.myapplication;

import android.content.res.Resources;
import android.util.Log;

import com.example.myapplication.blokjes.Block;
import com.example.myapplication.blokjes.Finish;
import com.example.myapplication.blokjes.Hard;
import com.example.myapplication.blokjes.Medium;
import com.example.myapplication.blokjes.Soft;

import java.util.ArrayList;

public class Level extends ArrayList<ArrayList<Block>>{

    int dWidth, dHeight;
    int bWidth,bHeight;
    Resources resources;

    public Level(int dWidth, int dHeight, int bWidth, int bHeight, Resources resources){
        this.dWidth = dWidth;
        this.dHeight = dHeight;
        this.bWidth = bWidth;
        this.bHeight = bHeight;
        this.resources = resources;
        Level1();
        Level2();
        Level3();
        Level4();
    }

    private void Level1() {
        ArrayList<Block> tijdelijk = new ArrayList<>();
        tijdelijk.add(new Finish(dWidth-75, dHeight/2, 150, 150, resources));
        this.add(tijdelijk);
    }

    private void Level2() {
        ArrayList<Block> tijdelijk2 = new ArrayList<>();
        tijdelijk2.add(new Hard(dWidth/2, dHeight/100*25, dHeight/100*20, dHeight/100*20, resources));
        tijdelijk2.add(new Hard(dWidth/2, dHeight/100*75, dHeight/100*20, dHeight/100*20, resources));
        tijdelijk2.add(new Finish(dWidth-150, dHeight/2, 300, 300, resources));
        this.add(tijdelijk2);
    }
    private void Level3() {
        ArrayList<Block> tijdelijk3 = new ArrayList<>();
        tijdelijk3.add(new Hard(dWidth/2, dHeight/100*25, 300, 300, resources));
        tijdelijk3.add(new Hard(dWidth/2, dHeight/100*50, 300, 300, resources));
        tijdelijk3.add(new Hard(dWidth/2, dHeight/100*75, 300, 300, resources));
        tijdelijk3.add(new Finish(dWidth-150, dHeight/2, 300, 300, resources));
        this.add(tijdelijk3);
    }

    private void Level4() {
        ArrayList<Block> tijdelijk4 = new ArrayList<>();
        tijdelijk4.add(new Hard(dWidth/2, 150, 300, 300, resources));
        tijdelijk4.add(new Hard(dWidth/2, dHeight/2, 300, 300, resources));
        tijdelijk4.add(new Hard(dWidth/2, dHeight-150, 300, 300, resources));
        tijdelijk4.add(new Finish(dWidth-150, dHeight/2, 300, 300, resources));
        this.add(tijdelijk4);
    }

}
