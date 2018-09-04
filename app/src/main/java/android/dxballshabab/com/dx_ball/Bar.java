package android.dxballshabab.com.dx_ball;

import android.graphics.Paint;

public class Bar {
    int color;
    float top,bottom,left,right;
    Paint paint;
    public Bar(float top,float bottom,float left,float right,int color)
    {
        this.top=top;
        this.bottom=bottom;
        this.left=left;
        this.right=right;
        this.color=color;
        paint = new Paint();
        paint.setColor(color);
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
