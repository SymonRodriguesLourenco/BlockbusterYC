package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
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
    private Handler handler;
    private Runnable runnable;
    private Display display;
    private Point point;
    private int dWidth, dHeight;
    private MediaPlayer player;

    //voor de pogingen
    private ImageView attempt, lifeOne, lifeTwo, lifeThree;
    private TextView textAttempt, textScore, textLevel;
    private Level level;

    //  voor het updaten van het scherm in miliseconde:
    private final long UPDATE_MILLIS = 1/3000;

    //  bal
    private int bHeight;
    private Ball ball, extraBall;
    private Bitmap ballOne, ballTwo;
    private List<Ball> balls = new ArrayList<>();

    private Cursor cursor;

    //  Blocks
    private List<Block> blocks = new ArrayList<>();
    private boolean hitBlock;

    //  als de bal naar links gaat is going forward false anders true, als de bal naar boven gaat i goingup true,anders false
    private Bitmap maxCursor, bigCursor, medCursor, minCursor;

    public Game(Context context, ImageView attempt, ImageView lifeOne, ImageView lifeTwo, ImageView lifeThree, TextView textAttempt, TextView textScore, TextView textLevel) {
        super(context);
        this.handler = new Handler();
        this.runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        this.display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        this.point = new Point();
        this.display.getSize(this.point);
        this.dWidth = this.point.x;
        this.dHeight = this.point.y;
        this.attempt = attempt;
        this.textAttempt = textAttempt;
        this.lifeOne = lifeOne;
        this.lifeTwo = lifeTwo;
        this.lifeThree = lifeThree;
        this.textScore = textScore;
        this.textLevel = textLevel;

        this.bHeight = this.dHeight/100*8;

        this.ball = new Ball(this.bHeight, this.bHeight, "");
        this.extraBall = new Ball(this.bHeight, this.bHeight, "");
        this.ball.startPosition(this.dHeight);
        this.extraBall.setPos(-1000, -1000);

        this.cursor = new Cursor(50, this.bHeight);

        this.level = new Level(this.dWidth,this.dHeight, getResources());

        this.ballOne = defineBitmap(R.drawable.donutfull, this.ball.getWidth(),this.ball.getHeight());
        this.ballTwo = defineBitmap(R.drawable.donutfull, this.extraBall.getWidth(),this.extraBall.getHeight());
        this.balls.add(this.ball);

        this.maxCursor = defineBitmap(R.drawable.ball, this.cursor.getMaxCursorSize(), this.cursor.getMaxCursorSize());
        this.bigCursor = defineBitmap(R.drawable.ball, this.cursor.getBigCursorSize(), this.cursor.getBigCursorSize());
        this.medCursor = defineBitmap(R.drawable.ball, this.cursor.getMedCursorSize(), this.cursor.getMedCursorSize());
        this.minCursor = defineBitmap(R.drawable.ball, this.cursor.getMinCursorSize(), this.cursor.getMinCursorSize());

        textAttempt.setText(this.level.getAttempt()+ " X");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(this.level.isFinished()){
            this.level.levelUp();
            this.ball.setBallPowerup("");
            this.balls.remove(this.extraBall);
            this.extraBall.setOutOfBounds(true);
            this.level.addScore((100* this.level.getLevel() + this.level.getAttempt()*50));
            this.textScore.setText("Score : " + this.level.getScore());
            this.textLevel.setText("Level : " + (this.level.getLevel() + 1));
            this.level.resetAttempts();
            this.textAttempt.setText(this.level.getAttempt()+ " X");
            if (this.level.getLevel() == this.level.size()){
                endGame();
            }
            this.level.setFinished(false);
        }
        if(this.level.size() > this.level.getLevel()) {
            this.blocks = this.level.get(this.level.getLevel());
            for (Block block : this.blocks){
                block.draw(canvas);
            }
            for (Ball donut: this.balls) {
                for (Block block : this.blocks) {
                    block.bounce(donut.getSpeedX(), donut.getSpeedY(), donut.getBallX(), donut.getBallY(), donut.getWidth(), donut.getHeight(), donut.isGoingUp(), donut.isGoingForward());
                }
                if(this.level.isTouched() && !this.level.isFinished()) {
                    boolean result = donut.borderBounce(this.dWidth, this.dHeight);
                    if (this.ball.isSound()){
                        this.play();
                    }
                    if (result) {
                        donut.setOutOfBounds(true);
                    }
                }
                for (Block block : this.blocks) {
                    if (block instanceof Finish) {
                        this.hitBlock = ((Finish) block).hit(donut.getBallX(), donut.getBallY(), donut.getWidth(), donut.getHeight(), 50);
                        if (this.hitBlock) {
                            this.level.setFinished(true);
                        }
                    } else {
                        this.hitBlock = block.hit(donut.getBallX(), donut.getBallY(), donut.getWidth(), donut.getHeight());
                        if (this.hitBlock) {
                            boolean power = block.hit(donut.getBallPowerup());
                            if (!power) {
                                this.play();
                                donut.bounce(block.isFromUp(), block.isFromDown(), block.isFromRight(), block.isFromLeft());
                            }
                            if (block instanceof Powerupblock) {
                                Powerupblock powerBlock = (Powerupblock) block;
                                switch (powerBlock.getPowerup()) {
                                    case "attempt":
                                        this.level.addAttempt();
                                        this.textAttempt.setText(this.level.getAttempt()+ " X");
                                        Toast.makeText(getContext(),"+1 poging",Toast.LENGTH_SHORT).show();
                                        break;
                                    case "lives":
                                        if (this.level.getLives() < 3) {
                                            this.level.addLife();
                                            displayLives();
                                            Toast.makeText(getContext(), "+1 leven", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                    case "multiball":
                                        this.ball.setBallPowerup("multiball");
                                        Toast.makeText(getContext(),"multiball",Toast.LENGTH_SHORT).show();
                                        break;
                                    case "powerball":
                                        this.ball.setBallPowerup("powerballnotactive");
                                        Toast.makeText(getContext(),"powerball",Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                            if(block.remove()) {
                                this.level.subsScore(25);
                                this.textScore.setText("Score : " + this.level.getScore()   );
                            }
                        }
                    }
                }
                donut.invert();
            }
            if (this.level.isFinished()){
                reset();
            } else if (this.ball.isOutOfBounds() && this.extraBall.isOutOfBounds()){
                change();
            }
        }
        canvas.drawBitmap(this.ballOne, this.ball.getBallX(), this.ball.getBallY(), null);
        canvas.drawBitmap(this.maxCursor, this.cursor.getMaxCursorX(), this.cursor.getMaxCursorY(), null);
        canvas.drawBitmap(this.bigCursor, this.cursor.getBigCursorX(), this.cursor.getBigCursorY(), null);
        canvas.drawBitmap(this.medCursor, this.cursor.getMedCursorX(), this.cursor.getMedCursorY(), null);
        canvas.drawBitmap(this.minCursor, this.cursor.getMinCursorX(), this.cursor.getMinCursorY(), null);
        if (this.balls.size() == 2) {
            canvas.drawBitmap(this.ballTwo, this.extraBall.getBallX(), this.extraBall.getBallY(), null);
        }
        this.handler.postDelayed(this.runnable, this.UPDATE_MILLIS);
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
        this.cursor.endXEndY(x, y, this.dHeight);
        for (Ball donut : this.balls) {
            if (!this.ball.isFired()) {
                if (this.balls.size() > 1) {
                    this.extraBall.startPosition(this.dHeight);
                }
                this.cursor.coords(this.dHeight);
            }
            if (action == MotionEvent.ACTION_UP) {
                if (!donut.isFired() && this.level.isTouched()) {
                    donut.ballSpeed(this.cursor.getFactorX(), this.cursor.getFactorY());
                    if (this.balls.size() > 1 && this.extraBall == donut ) {
                        this.extraBall.multiBall(this.ball.isGoingUp(), this.ball.getSpeedX(), this.ball.getSpeedY());
                    }
                    this.cursor.remove();
                    donut.setFired(true);
                    if (this.ball == donut){
                        this.level.subsAttempt();
                        this.textAttempt.setText(this.level.getAttempt() + " X");
                    }
                }
            }
        }
        this.attempt.setImageResource(R.drawable.donuteaten);
        this.level.setTouched(true);
        return true;
    }

    public void reset(){
        this.ball.reset(this.dHeight);
        this.level.setTouched(false);
        this.attempt.setImageResource(R.drawable.donutfull);
        if (this.ball.getBallPowerup().equals("none")){
            this.balls.remove(this.extraBall);
        }
        else if (this.ball.getBallPowerup().equals("powerballnotactive")){
            this.ball.setBallPowerup("powerball");
        }
        else if (this.ball.getBallPowerup().equals("powerball")){
            this.ball.setBallPowerup("none");

        }else if (this.ball.getBallPowerup().equals("multiball")){
            this.balls.add(this.extraBall);
            this.ball.setBallPowerup("none");
        }
    }

    public void endGame(){
        Intent intent = new Intent(getContext(), GameOver.class);
        Bundle bundle = new Bundle();
        bundle.putString("score", this.level.getScore() + "");
        bundle.putBoolean("finished", this.level.isFinished());
        intent.putExtras(bundle);
        getContext().startActivity(intent);
        Activity activity = (Activity)getContext();
        activity.finish();
    }

    public void change() {
        reset();

        if (this.level.getAttempt() == 0) {
            this.attempt.setImageResource(R.drawable.donutfull);
            this.level.resetAttempts();
            this.textAttempt.setText(this.level.getAttempt() + " X");
            this.level.subsLife();
        }
        displayLives();
        if (this.level.getLives() == 0) {
            endGame();
        }
    }

    public void displayLives() {
        this.lifeOne.setImageResource(R.drawable.heartfull);
        this.lifeTwo.setImageResource(R.drawable.heartfull);
        this.lifeThree.setImageResource(R.drawable.heartfull);
        switch (this.level.getLives()) {
            case 2:
                this.lifeOne.setImageResource(R.drawable.heartempty);
                break;
            case 1:
                this.lifeOne.setImageResource(R.drawable.heartempty);
                this.lifeTwo.setImageResource(R.drawable.heartempty);
                break;
            case 0:
                this.lifeOne.setImageResource(R.drawable.heartempty);
                this.lifeTwo.setImageResource(R.drawable.heartempty);
                this.lifeThree.setImageResource(R.drawable.heartempty);
                break;
        }
    }

    public void play(){
        if (this.player == null) {
            this.player = MediaPlayer.create(getContext(), R.raw.clack);
            this.player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        this.player.start();
    }

    public void stopPlayer() {
        if (this.player != null) {
            this.player.release();
            this.player = null;
        }
    }
}