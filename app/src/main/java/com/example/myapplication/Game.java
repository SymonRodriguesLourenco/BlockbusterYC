package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.blokjes.Block;
import com.example.myapplication.blokjes.Finish;
import com.example.myapplication.blokjes.Hard;
import com.example.myapplication.blokjes.Medium;
import com.example.myapplication.blokjes.Powerupblock;
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

    //voor de pogingen
    public boolean endGame = false;
    public int pogingen = 3, levens = 3;
    public ImageView poging1, leven1, leven2, leven3;
    public TextView pogingTekst, scoreLabel;
    public Bitmap be, bf, he, hf;
    int level = 0;
    Level levels;

    //voor de highscore
    private int score = 0;

//  voor het updaten van het scherm in miliseconde:
    final long UPDATE_MILLIS = 1/3000;

//  bal
    Ball ball, extraball;
    Bitmap ballMap, ballMap1;
//  waarde die per x aantal miliseconde toegevoegt wil worden
    int speedX = 0, speedY = 0;
    List<Ball> ballList = new ArrayList<>();
    int uitscherm = 0;

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
    boolean touched, isFinished=false;

    public Game(Context context, ImageView poging1, TextView pogingTekst, ImageView leven1, ImageView leven2, ImageView leven3, TextView scoreLabel) {
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
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;

        this.poging1 = poging1;
        this.pogingTekst = pogingTekst;
        this.leven1 = leven1;
        this.leven2 = leven2;
        this.leven3 = leven3;
        this.scoreLabel = scoreLabel;

        be = BitmapFactory.decodeResource(getResources() ,R.drawable.ball_eaten);
        bf = BitmapFactory.decodeResource(getResources(), R.drawable.ball_full);
        he = BitmapFactory.decodeResource(getResources(), R.drawable.hearticon_empty);
        hf = BitmapFactory.decodeResource(getResources(), R.drawable.hearticon);

        bHeight = dHeight/100*8;
        bWidth = dHeight/100*8;


//        ====================================================================
        ball = new Ball(bWidth, bHeight, "");
        extraball = new Ball(bWidth, bHeight, "");
        ball.startPosition(dHeight);
        extraball.setPos(-1000, -1000);
        levels = new Level(dWidth,dHeight, getResources());
//        =====================================================================


        ballMap = defineBitmap(R.drawable.ball_full, ball.getWidth(),ball.getHeight());
        ballMap1 = defineBitmap(R.drawable.ball_full, extraball.getWidth(),extraball.getHeight());
        ballList.add(ball);

        maxCursor = defineBitmap(R.drawable.ball, 50, 50);
        maxCursorX = -1000;

        bigCursor = defineBitmap(R.drawable.ball, 40, 40);
        bigCursorX = -1000;
        medCursor = defineBitmap(R.drawable.ball, 30, 30);
        medCursorX = -1000;

        minCursor = defineBitmap(R.drawable.ball, 20, 20);
        minCursorX = -1000;

        pogingTekst.setText(pogingen+ " X");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(isFinished){
            level+=1;
            ball.setBallPowerup("");
            ballList.remove(extraball);
            extraball.setUitscherm(true);
            score += 100 * level;
            score += pogingen * 50;
            scoreLabel.setText("Score : " + score);
            pogingen = 3;
            pogingTekst.setText(pogingen+ " X");
            isFinished = false;
        }
        if(levels.size() > level) {
            blocks = levels.get(level);
            for (int a = 0; a < ballList.size(); a++) {
                for (int i = 0; i < blocks.size(); i++) {
                    blocks.get(i).bounce(ballList.get(a).getSpeedX(), ballList.get(a).getSpeedY(), ballList.get(a).getBallX(), ballList.get(a).getBallY(), ballList.get(a).getWidth(), ballList.get(a).getHeight(), ballList.get(a).isGoingUp(), ballList.get(a).isGoingForward());
                }
                super.onDraw(canvas);
                if(touched && !isFinished) {
                    boolean uitkomst = ballList.get(a).borderBounce(dWidth, dHeight);
                    if (uitkomst) {
                        ballList.get(a).setUitscherm(true);
                    }
                }
                for (int i = 0; i < blocks.size(); i++) {
                    if (blocks.get(i) instanceof Finish) {
                        hitBlock = ((Finish) blocks.get(i)).hit(ballList.get(a).getBallX(), ballList.get(a).getBallY(), ballList.get(a).getWidth(), ballList.get(a).getHeight(), 50);
                        if (hitBlock) {
                            isFinished = true;
                            reset();
                        }
                        blocks.get(i).draw(canvas);
                    } else {
                        hitBlock = blocks.get(i).hit(ballList.get(a).getBallX(), ballList.get(a).getBallY(), ballList.get(a).getWidth(), ballList.get(a).getHeight());
                        if (hitBlock) {
                            boolean power = blocks.get(i).hit(ballList.get(a).getBallPowerup());
                            if (!power) {
                                if (blocks.get(i).isFromLeft()) {
                                    if (ballList.get(a).isGoingForward()) {
                                        ballList.get(a).setGoingForward(false);
                                        blocks.get(i).setFromLeft(false);
                                    } else {
                                        ballList.get(a).setGoingForward(true);
                                        blocks.get(i).setFromLeft(false);
                                    }
                                } else if (blocks.get(i).isFromRight()) {
                                    if (ballList.get(a).isGoingForward()) {
                                        ballList.get(a).setGoingForward(false);
                                        blocks.get(i).setFromRight(false);
                                    } else {
                                        ballList.get(a).setGoingForward(true);
                                        blocks.get(i).setFromRight(false);
                                    }
                                }
                                if (blocks.get(i).isFromUp()) {
                                    if (ballList.get(a).isGoingUp()) {
                                        ballList.get(a).setGoingUp(false);
                                        blocks.get(i).setFromUp(false);
                                    } else {
                                        ballList.get(a).setGoingUp(true);
                                        blocks.get(i).setFromUp(false);
                                    }
                                } else if (blocks.get(i).isFromDown()) {
                                    if (ballList.get(a).isGoingUp()) {
                                        ballList.get(a).setGoingUp(false);
                                        blocks.get(i).setFromDown(false);
                                    } else {
                                        ballList.get(a).setGoingUp(true);
                                        blocks.get(i).setFromDown(false);
                                    }
                                }
                            }
                            if (blocks.get(i) instanceof Powerupblock) {
                                Powerupblock block = (Powerupblock) blocks.get(i);
                                switch (block.getPowerup()) {
                                    case "pogingen":
                                        pogingen++;
                                        pogingTekst.setText(pogingen+ " X");
                                        break;
                                    case "levens":
                                        if (levens < 3) {
                                            levens++;
                                            displayLevens();
                                            break;
                                        }
                                    case "multiball":
                                        ball.setBallPowerup("multiball");
                                        break;
                                    case "powerball":
                                        ball.setBallPowerup("powerballnotactive");
                                        break;
                                }
                            }
                            if(blocks.get(i).remove()) {
                                score -= 25;
                                scoreLabel.setText("Score : " + score   );
                            }
                        }
                        blocks.get(i).draw(canvas);
                    }
                }
            }
            if (ball.isUitscherm() && extraball.isUitscherm()){
                verander();
            }
        }
        canvas.drawBitmap(ballMap, ball.getBallX(), ball.getBallY(), null);
        canvas.drawBitmap(maxCursor, maxCursorX, maxCurosrY, null);
        canvas.drawBitmap(bigCursor, bigCursorX, bigCursorY, null);
        canvas.drawBitmap(medCursor, medCursorX, medCursorY, null);
        canvas.drawBitmap(minCursor, minCursorX, minCursorY, null);
        if (ballList.size() == 2) {
            canvas.drawBitmap(ballMap1, extraball.getBallX(), extraball.getBallY(), null);
        }
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
        int x = (int) event.getX();
        int y = (int) event.getY();
        int intervalx = (50 + ball.getWidth() / 2 - x);
        int intervaly = (dHeight / 2 - y);
        double slope = Math.sqrt(intervalx * intervalx + intervaly * intervaly);
        double vergroting = ball.getStandardspeed() / slope;
        double vergrootx = vergroting * intervalx;
        double vergrooty = vergroting * intervaly;
        for (int a = 0; a < ballList.size(); a++) {
            if (!ball.isFired()) {
                if (ballList.size()==2) {
                    extraball.startPosition(dHeight);
                }
                bigCursorX = -(int) vergrootx * 65 / 10 + (ball.getWidth() / 2 + 50 - bigCursor.getWidth() / 2);
                bigCursorY = -(int) vergrooty * 65 / 10 + (dHeight / 2 - bigCursor.getHeight() / 2);
                medCursorX = -(int) vergrootx * 4 + (ball.getWidth() / 2 + 50 - medCursor.getWidth() / 2);
                medCursorY = -(int) vergrooty * 4 + (dHeight / 2 - medCursor.getHeight() / 2);
                minCursorX = -(int) vergrootx * 2 + (ball.getWidth() / 2 + 50 - minCursor.getWidth() / 2);
                minCursorY = -(int) vergrooty * 2 + (dHeight / 2 - minCursor.getHeight() / 2);
                maxCursorX = -(int) vergrootx * 95 / 10 + (ball.getWidth() / 2 + 50 - maxCursor.getWidth() / 2);
                maxCurosrY = -(int) vergrooty * 95 / 10 + (dHeight / 2 - maxCursor.getHeight() / 2);
            }
            if (action == MotionEvent.ACTION_UP) {
                if (!ballList.get(a).isFired()) {
                    speedX = -(int) vergrootx;
                    speedY = -(int) vergrooty;
                    if (speedY < 0) {
                        speedY *= -1;
                        ballList.get(a).setGoingUp(true);
                    }
                    if (speedX < 0) {
                        speedX *= -1;
                        ballList.get(a).setGoingForward(false);

                    }
                    if (a == 1) {
                        if (ball.isGoingUp()) {
                            extraball.setGoingUp(false);
                        } else {
                            extraball.setGoingUp(true);
                        }
                    }
                    ballList.get(a).setSpeedY(speedY);
                    ballList.get(a).setSpeedX(speedX);
                    if (ballList.size()==2){
                        extraball.setSpeedX(ball.getSpeedX());
                        extraball.setSpeedY(ball.getSpeedY());
                    }
                    maxCursorX = -80;
                    bigCursorX = -80;
                    medCursorX = -80;
                    minCursorX = -80;
                    if (a == 0){
                        pogingen--;
                        pogingTekst.setText(pogingen + " X");
                    }
                    ballList.get(a).setFired(true);
                }
                poging1.setImageResource(R.drawable.ball_eaten);
                touched = true;
            }
        }
        return true;
    }

    public void reset(){
        ball.setSpeedX(0);
        ball.setSpeedY(0);
        ball.startPosition(dHeight);
        touched = false;
        ball.setFired(false);
        poging1.setImageResource(R.drawable.ball_full);
        if (ball.getBallPowerup().equals("none")){
            ballList.remove(extraball);
        }
        else if (ball.getBallPowerup().equals("powerballnotactive")){
            ball.setBallPowerup("powerball");
        }
        else if (ball.getBallPowerup().equals("powerball")){
            ball.setBallPowerup("none");

        }else if (ball.getBallPowerup().equals("multiball")){
            ballList.add(extraball);
            ball.setBallPowerup("none");
        }
    }

    public void resetLevel() {
        levels = new Level(dWidth,dHeight, getResources());
    }

    public void verander() {
        reset();

        if (pogingen == 0) {
            poging1.setImageResource(R.drawable.ball_full);
            pogingen = 3;
            pogingTekst.setText(pogingen + " X");
            levens--;
            resetLevel();
        }
        displayLevens();
        if (levens == 0) {
            Intent intent = new Intent(getContext(), GameOver.class);
            Bundle bundle = new Bundle();
            bundle.putString("score", score + "");
            intent.putExtras(bundle);
            getContext().startActivity(intent);
            Activity activity = (Activity)getContext();
            activity.finish();
        }
    }

    public void displayLevens() {
        if (levens == 3) {
            leven1.setImageResource(R.drawable.hearticon);
            leven2.setImageResource(R.drawable.hearticon);
            leven3.setImageResource(R.drawable.hearticon);
        } else if (levens == 2) {
            leven1.setImageResource(R.drawable.hearticon_empty);
            leven2.setImageResource(R.drawable.hearticon);
            leven3.setImageResource(R.drawable.hearticon);
        } else if (levens == 1) {
            leven1.setImageResource(R.drawable.hearticon_empty);
            leven2.setImageResource(R.drawable.hearticon_empty);
            leven3.setImageResource(R.drawable.hearticon);
        } else if (levens == 0) {
            leven1.setImageResource(R.drawable.hearticon_empty);
            leven2.setImageResource(R.drawable.hearticon_empty);
            leven3.setImageResource(R.drawable.hearticon_empty);
        }
    }

}
