package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.blokjes.Block;
import com.example.myapplication.blokjes.Finish;
import com.example.myapplication.blokjes.Powerupblock;

import java.util.ArrayList;
import java.util.List;


class Game extends View {
//  voor het runnen van de applicatie:
    Handler handler;
    Runnable runnable;
    Display display;
    Point point;
    int dWidth, dHeight;
    MediaPlayer player;

    //voor de pogingen
    ImageView poging1, leven1, leven2, leven3;
    TextView pogingTekst, scoreLabel;
    Bitmap be, bf, he, hf;
    int level = 0;
    Level levels;

//  voor het updaten van het scherm in miliseconde:
    final long UPDATE_MILLIS = 1/3000;

//  bal
    Ball ball, extraball;
    Bitmap ballMap, ballMap1;
//  waarde die per x aantal miliseconde toegevoegt wil worden
    int speedX = 0, speedY = 0;
    List<Ball> ballList = new ArrayList<>();

    Cursor cursor;

//  Blocks
    List<Block> blocks = new ArrayList<>();
    int bHeight, bWidth;
    boolean hitBlock;

//  als de bal naar links gaat is going forward false anders true, als de bal naar boven gaat i goingup true,anders false
    Bitmap maxCursor, bigCursor, medCursor, minCursor;
//  de coordinaten van de maxCursor
    int maxCursorX, maxCursorY, bigCursorX, bigCursorY, medCursorX, medCursorY, minCursorX, minCursorY;

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

        ball = new Ball(bWidth, bHeight, "");
        extraball = new Ball(bWidth, bHeight, "");
        ball.startPosition(dHeight);
        extraball.setPos(-1000, -1000);

        this.cursor = new Cursor(50, bHeight);

        levels = new Level(dWidth,dHeight, getResources());

        ballMap = defineBitmap(R.drawable.ball_full, ball.getWidth(),ball.getHeight());
        ballMap1 = defineBitmap(R.drawable.ball_full, extraball.getWidth(),extraball.getHeight());
        ballList.add(ball);

        maxCursor = defineBitmap(R.drawable.ball, cursor.getMaxCursorSize(), cursor.getMaxCursorSize());
        maxCursorX = -1000;

        bigCursor = defineBitmap(R.drawable.ball, cursor.getBigCursorSize(), cursor.getBigCursorSize());
        bigCursorX = -1000;
        medCursor = defineBitmap(R.drawable.ball, cursor.getMedCursorSize(), cursor.getMedCursorSize());
        medCursorX = -1000;

        minCursor = defineBitmap(R.drawable.ball, cursor.getMinCursorSize(), cursor.getMinCursorSize());
        minCursorX = -1000;

        pogingTekst.setText(levels.getPogingen()+ " X");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isFinished){
            level+=1;
            ball.setBallPowerup("");
            ballList.remove(extraball);
            extraball.setUitscherm(true);
            levels.addScore((100*level + levels.getPogingen()*50));
            scoreLabel.setText("Score : " + levels.getScore());
            levels.resetPogingen();
            pogingTekst.setText(levels.getPogingen()+ " X");
            isFinished = false;
        }
        if(levels.size() > level) {
            blocks = levels.get(level);
            for (Block block : blocks){
                block.draw(canvas);
            }
            for (Ball donut: ballList) {
                for (Block block : blocks) {
                  block.bounce(donut.getSpeedX(), donut.getSpeedY(), donut.getBallX(), donut.getBallY(), donut.getWidth(), donut.getHeight(), donut.isGoingUp(), donut.isGoingForward());
                }
                if(touched && !isFinished) {
                    boolean uitkomst = donut.borderBounce(dWidth, dHeight);
                    if (ball.isSound()){
                        this.play();
                    }
                    if (uitkomst) {
                        donut.setUitscherm(true);
                    }
                }
                for (Block block : blocks) {
                    if (block instanceof Finish) {
                        hitBlock = ((Finish) block).hit(donut.getBallX(), donut.getBallY(), donut.getWidth(), donut.getHeight(), 50);
                        if (hitBlock) {
                            isFinished = true;
                        }
                    } else {
                        hitBlock = block.hit(donut.getBallX(), donut.getBallY(), donut.getWidth(), donut.getHeight());
                        if (hitBlock) {
                            boolean power = block.hit(donut.getBallPowerup());
                            if (!power) {
                                this.play();
                                if (block.isFromLeft()) {
                                    donut.setInvertX(true);
                                    donut.countXadd();
                                } else if (block.isFromRight()) {
                                    donut.setInvertX(true);
                                    donut.countXadd();
                                    }
                                if (block.isFromUp()) {
                                    donut.setInvertY(true);
                                    donut.countYadd();
                                } else if (block.isFromDown()) {
                                    donut.setInvertY(true);
                                    donut.countYadd();
                                }
                            }
                            if (block instanceof Powerupblock) {
                                Powerupblock blocky = (Powerupblock) block;
                                switch (blocky.getPowerup()) {
                                    case "pogingen":
                                        levels.addPogingen();
                                        pogingTekst.setText(levels.getPogingen()+ " X");
                                        break;
                                    case "levens":
                                        if (levels.getLevens() < 3) {
                                            levels.addLevens();
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
                            if(block.remove()) {
                                levels.subsScore(25);
                                scoreLabel.setText("Score : " + levels.getScore()   );
                            }
                        }
                    }
                }
                donut.invert();
            }
            if (isFinished){
                reset();
            } else if (ball.isUitscherm() && extraball.isUitscherm()){
                verander();
            }
        }
        canvas.drawBitmap(ballMap, ball.getBallX(), ball.getBallY(), null);
        canvas.drawBitmap(maxCursor, cursor.getMaxCursorX(), cursor.getMaxCursorY(), null);
        canvas.drawBitmap(bigCursor, cursor.getBigCursorX(), cursor.getBigCursorY(), null);
        canvas.drawBitmap(medCursor, cursor.getMedCursorX(), cursor.getMedCursorY(), null);
        canvas.drawBitmap(minCursor, cursor.getMinCursorX(), cursor.getMinCursorY(), null);
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
        this.cursor.vergroot(x, y, dHeight);
        for (int a = 0; a < ballList.size(); a++) {
            if (!ball.isFired()) {
                if (ballList.size()==2) {
                    extraball.startPosition(dHeight);
                }
                this.cursor.coords(dHeight);
            }
            if (action == MotionEvent.ACTION_UP) {
                if (!ballList.get(a).isFired()) {
                    speedX = -(int) this.cursor.getVergrootX();
                    speedY = -(int) this.cursor.getVergrootY();
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
                    this.cursor.remove();
                    if (a == 0){
                        levels.subsPogingen();
                        pogingTekst.setText(levels.getPogingen() + " X");
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

    public void verander() {
        reset();

        if (levels.getPogingen() == 0) {
            poging1.setImageResource(R.drawable.ball_full);
            levels.resetPogingen();
            pogingTekst.setText(levels.getPogingen() + " X");
            levels.subsLevens();
            levels.resetLevel();
        }
        displayLevens();
        if (levels.getLevens() == 0) {
            Intent intent = new Intent(getContext(), GameOver.class);
            Bundle bundle = new Bundle();
            bundle.putString("score", levels.getScore() + "");
            intent.putExtras(bundle);
            getContext().startActivity(intent);
            Activity activity = (Activity)getContext();
            activity.finish();
        }
    }

    public void displayLevens() {
        leven1.setImageResource(R.drawable.hearticon);
        leven2.setImageResource(R.drawable.hearticon);
        leven3.setImageResource(R.drawable.hearticon);
        switch (levels.getLevens()) {
            case 2:
                leven1.setImageResource(R.drawable.hearticon_empty);
                break;
            case 1:
                leven1.setImageResource(R.drawable.hearticon_empty);
                leven2.setImageResource(R.drawable.hearticon_empty);
                break;
            case 0:
                leven1.setImageResource(R.drawable.hearticon_empty);
                leven2.setImageResource(R.drawable.hearticon_empty);
                leven3.setImageResource(R.drawable.hearticon_empty);
                break;
        }
    }

    public void play(){
        if (player == null) {
            player = MediaPlayer.create(getContext(), R.raw.clack);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
