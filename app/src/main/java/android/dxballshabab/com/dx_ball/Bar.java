package android.dxballshabab.com.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;

public class Bar {
    int color;
    float top,bottom,left,right;
    Paint paint;
    public Bar(){
        left =0;
        top=0;
        right=0;
        bottom=0;
        paint=new Paint();
        paint.setColor(Color.BLACK);

    }
    public void setBottom(float bottom) {
        this.bottom = bottom;
    }
    public void setLeft(float left) {
        this.left = left;
    }
    public void setRight(float right) {
        this.right = right;
    }
    public void setTop(float top) {
        this.top = top;
    }
    public float getTop()
    {return top;}
    public float getBottom()
    {return bottom;}
    public float getLeft()
    {return left;}
    public float getRight()
    {return right;}
    public Paint getPaint() {
        return paint;
    }
    public void moveBar(boolean leftPos,boolean rightPos){
        if(leftPos==true){
            if(GameCanvas.canvasWidth>=right) {
                left = left + 4;
                right = right + 4;
            }
        }
        else if(rightPos==true){
            if(0<=left) {
                left = left - 4;
                right = right - 4;
            }
        }
    }
}
