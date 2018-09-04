package android.dxballshabab.com.dx_ball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    private int x,y,radius,dx,dy,gameOver=0;
    private Paint paint;
    public  Ball(int x,int y,int radius){
        this.x=x;
        this.y=y;
        this.radius=radius;
        paint=new Paint();
        paint.setColor(Color.RED);
        dx=0;
        dy=0;
    }
    public int getX(){
        return x;
    }

    public int getGameOver(){
        return gameOver;
    }
    public int getY() {
        return y;
    }
    public int getRadius() {
        return radius;
    }

    public Paint getPaint() {
        return paint;
    }

    public int getDx() {
        return dx;
    }
    public int getDy() {
        return dy;
    }
    public void setDx(int dx) { this.dx = dx; }
    public void setDy(int dy) {
        this.dy = dy;
    }
    public void move(){
        x=x+dx;
        y=y+dy;
    }
    public void ballBoundaryChech(Canvas canvas) {

        if((this.y-this.radius)>=canvas.getHeight()){

            GameCanvas.life-=1;
            GameCanvas.startLife=true;
            this.gameOver=1;
        }

        if(GameCanvas.life==0)
            GameCanvas.gameOver = true;

        if((this.x+this.radius)>=canvas.getWidth()
                || (this.x-this.radius)<=0){
            this.dx = -this.dx;
        }
        if( (this.y-this.radius)<=0){
            this.dy = -this.dy;
        }
    }
    public void bounce(Canvas canvas){
        move();
        if(x == canvas.getWidth()|| x < 0){
            x=0;
            y=0;
        }
        if(y == canvas.getWidth() || y < 0){
            x=0;
        }
    }
}
