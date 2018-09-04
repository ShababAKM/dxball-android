package android.dxballshabab.com.dx_ball;

import android.content.Context;
import android.content.Intent;
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
import java.util.ArrayList;
import java.util.List;

public class GameCanvas extends View implements Runnable{
    Paint paint ;
    Ball ball ;
    Bar bar;
    public static boolean gameOver,startLife,leftPos, rightPos, start = true;
    public static int life,canvasHeight,canvasWidth;
    float barWidth = 300;
    float brickX = 0,brickY=50;
    static int score = 0;
    float left, right, top, bottom;
    float downX, downY, upX, upY;
    int min_distance = 50;
    int ballSpeed,color;
    public static int checkWidth=0;
    ArrayList<Bricks> bricks=new ArrayList<Bricks>();
    public GameCanvas(Context context) {
        super(context);
        paint=new Paint();
        life = 3;
        gameOver = false;
        startLife=true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(start==true)
        {
            start=false;
            for(int i=0;i<18;i++) {
                if (brickX >= canvas.getWidth()) {
                    brickX = 0;
                    brickY += 140;
                }
                if (i % 2 == 0)
                    color = Color.GRAY;
                else color = Color.BLACK;
                bricks.add(new Bricks(brickX, brickY, brickX + canvas.getWidth() / 9, brickY + 140, color));
                brickX += canvas.getWidth() / 9;
            }
            ball=new Ball( canvas.getWidth()/2, canvas.getHeight()/2 , 30);
            ball.setDx(6);
            ball.setDy(6);

            bar=new Bar(getHeight() - 15, getHeight(),getWidth() / 2 - (barWidth / 2),getWidth() / 2 + (barWidth / 2),Color.BLUE);

        }
        for(int i=0;i<bricks.size();i++){
            canvas.drawRect(bricks.get(i).getLeft(),bricks.get(i).getTop(),bricks.get(i).getRight(),bricks.get(i).getBottom(),bricks.get(i).getPaint());
        }
        canvas.drawRect(bar.getLeft(), bar.getTop(), bar.getRight(), bar.getBottom(), bar.getPaint());
        canvas.drawCircle(ball.getX(), ball.getY(), ball.getRadius(), ball.getPaint());
    }

    @Override
    public void run() {

    }
}
