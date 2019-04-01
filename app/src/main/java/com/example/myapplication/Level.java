package com.example.myapplication;

import com.example.myapplication.blokjes.Block;
import com.example.myapplication.blokjes.Finish;

import java.util.ArrayList;
import java.util.List;

public class Level {
    List<Block> block = new ArrayList<>();
    List<Ball> balls = new ArrayList<>();
    Finish finish;
}
