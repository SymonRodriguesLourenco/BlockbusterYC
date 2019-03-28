package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import com.example.myapplication.blokjes.Block;
import com.example.myapplication.blokjes.Finish;
import com.example.myapplication.blokjes.Medium;
import com.example.myapplication.blokjes.Soft;

import java.util.ArrayList;
import java.util.List;


class Game extends View {
//  voor het runnen van de applicatie:
    Handler handler;
    Runnable runnable;
    Display display;
    Point point;
    int dWidth, dHeight;

//  voor het updaten van het scherm in miliseconde:
    final long UPDATE_MILLIS = 1/3000;

//  bal
    Ball ball;
    Bitmap ballMap;
//  als de bal naar links gaat is going forward false anders true, als de bal naar boven gaat i goingup true,anders false
    Boolean goingForward = true, goingUp = false, fired = false;
//  waarde die per x aantal miliseconde toegevoegt wil worden
    int speedX = 0, speedY = 0;
    double standardSpeed = Math.sqrt(800);
//  de coordinaten van de bal
    int ballX, ballY;

//  Blocks
    Bitmap blockStandard,finish;
    List<Block> blocks = new ArrayList<>();
    int bHeight, bWidth;
    boolean hitBlock;

//  als de bal naar links gaat is going forward false anders true, als de bal naar boven gaat i goingup true,anders false
    Bitmap maxCursor, bigCursor, medCursor, minCursor;
//  de coordinaten van de maxCursor
    int maxCursorX, maxCurosrY, bigCursorX, bigCursorY, medCursorX, medCursorY, minCursorX, minCursorY;

//  of het spel gestart is en de bal weggeschoten is
    boolean touched;


    public Game(Context context) {
        super(context);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        ball = new Ball(100, 100);
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;

        ball.startPosition(dHeight);

        bHeight = dHeight/100*18;
        bWidth = dWidth/20;

        ballMap = defineBitmap(R.drawable.ball_full, ball.getWidth(),ball.getHeight());

        maxCursor = defineBitmap(R.drawable.ball, 50, 50);
        maxCursorX = -1000;

        bigCursor = defineBitmap(R.drawable.ball, 40, 40);
        bigCursorX = -1000;
        medCursor = defineBitmap(R.drawable.ball, 30, 30);
        medCursorX = -1000;

        minCursor = defineBitmap(R.drawable.ball, 20, 20);
        minCursorX = -1000;

        blockStandard = defineBitmap(R.drawable.block, bWidth, bHeight);
        finish = defineBitmap(R.drawable.block2, 300, 300);

        blocks.add(new Soft(dWidth/2, dHeight/100*25, bWidth, bHeight));
        blocks.add(new Medium(dWidth/2, dHeight/100*50, bWidth, bHeight));
        blocks.add(new Soft(dWidth/2, dHeight/100*75, bWidth, bHeight));
        blocks.add(new Finish(dWidth-150, dHeight/2, 300, 300));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = 0; i< blocks.size(); i++) {
            blocks.get(i).bounce(ball.getSpeedX(), ball.getSpeedY(), ball.getBallX(), ball.getBallY(), ball.getWidth(), ball.getHeight(), ball.isGoingUp(), ball.isGoingForward());
        }
        super.onDraw(canvas);
        if(touched) {
            ball.borderBounce(dWidth, dHeight);
        }
        for(int i = 0; i< blocks.size(); i++){
            if(blocks.get(i) instanceof Finish){
                hitBlock = ((Finish)blocks.get(i)).hit(ball.getBallX(), ball.getBallY(), ball.getWidth(), ball.getHeight(), 50);
                if(hitBlock){
                    touched = false;
                }
                canvas.drawBitmap(finish, blocks.get(i).getMinX(), blocks.get(i).getMaxY(),null);
            }else{
                hitBlock = blocks.get(i).hit(ball.getBallX(), ball.getBallY(), ball.getWidth(), ball.getHeight());
                if(hitBlock) {
                    if (blocks.get(i).isFromLeft()){
                        if (ball.isGoingForward()) {
                            ball.setGoingForward(false);
                            blocks.get(i).setFromLeft(false);
                        }
                        else {
                            ball.setGoingForward(true);
                            blocks.get(i).setFromLeft(false);
                        }
                    }
                    else if (blocks.get(i).isFromRight()){
                        if (ball.isGoingForward()) {
                            ball.setGoingForward(false);
                            blocks.get(i).setFromRight(false);
                        }
                        else {
                            ball.setGoingForward(true);
                            blocks.get(i).setFromRight(false);
                        }
                    }
                    if (blocks.get(i).isFromUp()){
                        if (ball.isGoingUp()) {
                            ball.setGoingUp(false);
                            blocks.get(i).setFromUp(false);
                        }
                        else {
                            ball.setGoingUp(true);
                            blocks.get(i).setFromUp(false);
                        }
                    }
                    else if (blocks.get(i).isFromDown()){
                        if (ball.isGoingUp()) {
                            ball.setGoingUp(false);
                            blocks.get(i).setFromDown(false);
                        }
                        else {
                            ball.setGoingUp(true);
                            blocks.get(i).setFromDown(false);
                        }
                    }
                    blocks.get(i).remove();
                }
                canvas.drawBitmap(blockStandard, blocks.get(i).getMinX(), blocks.get(i).getMaxY(),null);
            }
        }
        canvas.drawBitmap(ballMap, ball.getBallX(), ball.getBallY(), null);
        canvas.drawBitmap(maxCursor, maxCursorX, maxCurosrY, null);
        canvas.drawBitmap(bigCursor, bigCursorX, bigCursorY, null);
        canvas.drawBitmap(medCursor, medCursorX, medCursorY, null);
        canvas.drawBitmap(minCursor, minCursorX, minCursorY, null);
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    public Bitmap defineBitmap(int shape, int width, int height) {
        Bitmap figure = BitmapFactory.decodeResource(getResources(), shape);
        figure = Bitmap.createScaledBitmap(figure, width, height, false);
        return figure;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int)event.getX();
        int y = (int)event.getY();
        int intervalx = (50 + ball.getWidth() / 2 - x);
        int intervaly = (dHeight / 2 - y);
        double slope = Math.sqrt(intervalx * intervalx + intervaly * intervaly);
        double vergroting = ball.getStandardspeed() / slope;
        double vergrootx = vergroting * intervalx;
        double vergrooty = vergroting * intervaly;
        if (!ball.isFired()) {
            bigCursorX = -(int)vergrootx *65/10 + (ball.getWidth()/2+50 - bigCursor.getWidth()/2);
            bigCursorY = -(int)vergrooty *65/10 + (dHeight/2 - bigCursor.getHeight()/2);
            medCursorX = -(int)vergrootx *4 + (ball.getWidth()/2+50 - medCursor.getWidth()/2);
            medCursorY = -(int)vergrooty *4 + (dHeight/2 - medCursor.getHeight()/2);
            minCursorX = -(int)vergrootx *2 + (ball.getWidth()/2+50 - minCursor.getWidth()/2);
            minCursorY = -(int)vergrooty *2 + (dHeight/2 - minCursor.getHeight()/2);
            maxCursorX = -(int)vergrootx *95/10 + (ball.getWidth()/2+50 - maxCursor.getWidth()/2);
            maxCurosrY = -(int)vergrooty *95/10 + (dHeight/2 - maxCursor.getHeight()/2);
        }
        if (action == MotionEvent.ACTION_UP){
            if (!ball.isFired()) {
                speedX = -(int)vergrootx;
                speedY = -(int)vergrooty;
                if (speedY < 0){
                    speedY *= -1;
                    ball.setGoingUp(true);
                }
                if (speedX < 0) {
                    speedX *= -1;
                    ball.setGoingForward(false);
                }
                ball.setSpeedY(speedY);
                ball.setSpeedX(speedX);
                maxCursorX = -80;
                bigCursorX = -80;
                medCursorX = -80;
                minCursorX = -80;
            }
            ball.setFired(true);
            touched = true;
        }
        return true;
    }
}
