package android.dxballshabab.com.dx_ball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameCanvas extends View implements Runnable{
    Paint paint ;
    Ball ball ;
    Bar bar;
    SharedPreferences sharedPref;
    public static boolean gameOver,startLife,leftPos, rightPos, start = true,level2=false,gameOver2=false;
    public static int life,canvasWidth;
    float barWidth = 300;
    float brickX = 0,brickY=50;
    static int score = 0;
    float left, right, top, bottom;
    float downX, downY, upX, upY;
    int min_distance = 50;
    int ballSpeed,color;
    ArrayList<Bricks> bricks=new ArrayList<Bricks>();
    public GameCanvas(Context context) {
        super(context);
        paint=new Paint();
        life = 3;
        gameOver = false;
        startLife=true;
        start=true;
        bar = new Bar();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(start==true)
        {
            start=false;
            if(level2==false){
            for(int i=0;i<27;i++) {
                if (brickX >= canvas.getWidth()) {
                    brickX = 0;
                    brickY += 140;
                }
                if (i % 2 == 0)
                    color = Color.GRAY;
                else color = Color.BLACK;
                bricks.add(new Bricks(brickX, brickY, brickX + canvas.getWidth() / 9, brickY + 140, color));
                brickX += canvas.getWidth() / 9;
            }}
            else{
            for(int i=0;i<3;i++)
            {
                brickX = 0;
                brickY += 280;
                for(int j=0;j<9;j++)
                {
                    if (j % 2 == 0)
                        color = Color.GRAY;
                    else color = Color.BLACK;
                    bricks.add(new Bricks(brickX, brickY, brickX + canvas.getWidth() / 9, brickY + 140, color));
                    brickX += canvas.getWidth() / 9;
                }
            }}
            ball=new Ball(canvas.getWidth()/2,canvas.getHeight()-50,20);
            ball.setDx(6);
            ball.setDy(6);
            canvasWidth = canvas.getWidth();
            left = getWidth()/2-(barWidth/2);
            top = getHeight()-20;
            right = getWidth()/2+(barWidth/2);
            bottom = getHeight();
            bar.setBottom(bottom);
            bar.setLeft(left);
            bar.setRight(right);
            bar.setTop(top);
        }
        if(startLife==true){
            startLife = false;
            ballSpeed = 16;
            ball=new Ball(canvas.getWidth()/2,canvas.getHeight()-50,20);
            ball.setDx( ballSpeed );
            ball.setDy( -ballSpeed );
        }
        for(int i=0;i<bricks.size();i++){
            canvas.drawRect(bricks.get(i).getLeft(),bricks.get(i).getTop(),bricks.get(i).getRight(),bricks.get(i).getBottom(),bricks.get(i).getPaint());
        }

        if(gameOver==true||gameOver2==true){
            paint.setColor(Color.BLACK);
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            paint.setFakeBoldText(true);
            canvas.drawText("GAME OVER",canvas.getWidth()/2-110,canvas.getHeight()/2,paint);
            canvas.drawText("FINAL SCORE: "+score,canvas.getWidth()/2-150,canvas.getHeight()/2+60,paint);
            gameOver = false;


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ((DxBallActivity)getContext()).finish();
        }

        canvas.drawRect(bar.getLeft(), bar.getTop(), bar.getRight(), bar.getBottom(), bar.getPaint());
        canvas.drawCircle(ball.getX(), ball.getY(), ball.getRadius(), ball.getPaint());
        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        canvas.drawText("Score: "+score,10,30,paint);
        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        canvas.drawText("Life: "+life,canvas.getWidth()-110,40,paint);
        bar.moveBar(leftPos,rightPos);
        this.ballXbrick(bricks,ball,canvas);
        this.ballXbar(bar,ball, canvas);
        ball.ballXboundary(canvas);
        ball.move();
        this.run();
    }
    public void ballXbar(Bar myBar,Ball myBall,Canvas canvas){
        if(((ball.getY()+ball.getRadius())>=bar.getTop())&&((ball.getY()+ball.getRadius())<=bar.getBottom())&& ((ball.getX())>=bar.getLeft())&& ((ball.getX())<=bar.getRight())) {
            ball.setDy(-(ball.getDy()));
        }
    }
    public void ballXbrick(ArrayList<Bricks> brick ,Ball myBall,Canvas canvas){
        for(int i=0;i<brick.size();i++) {
            if (((ball.getY() - ball.getRadius()) <= brick.get(i).getBottom()) && ((ball.getY() + ball.getRadius()) >= brick.get(i).getTop()) && ((ball.getX()) >= brick.get(i).getLeft()) && ((ball.getX()) <= brick.get(i).getRight())) {
                brick.remove(i);
                score+=1;
                ball.setDy(-(myBall.getDy()));
                if(brick.size()==0 && level2==false)
                {
                    start=level2=true;
                    life=3;
                }
                else if(brick.size()==0 && level2==true)
                {
                    gameOver2=true;
                }
            }
        }

    }

    @Override
    public void run() {
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                downX=event.getX();
                downY=event.getY();
                return true;
            }
            case MotionEvent.ACTION_UP:{
                upX=event.getX();
                upY=event.getY();
                float deltaX=downX-upX;
                float deltaY=downY-upY;
                if(Math.abs(deltaX) > Math.abs(deltaY)){
                    if(Math.abs(deltaX) > min_distance) {
                        if (deltaX < 0) {
                            leftPos=true;
                            rightPos=false;
                            bar.moveBar(leftPos, rightPos);
                            return true;
                        }
                        if (deltaX > 0) {
                            leftPos=false;
                            rightPos=true;
                            bar.moveBar(leftPos,rightPos);
                            return true;
                        }
                    }
                    else return  false;
                }
                else{
                    if(Math.abs(deltaY) > min_distance) {
                        if (deltaY < 0) return true;
                        if (deltaY > 0) return true;
                    }
                    else return  false;
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
