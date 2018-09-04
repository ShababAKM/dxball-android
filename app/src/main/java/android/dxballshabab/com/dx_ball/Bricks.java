package android.dxballshabab.com.dx_ball;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Bricks {
    int color;
    float top,bottom,left,right;
    Paint paint;
    public Bricks(float left,float top,float right,float bottom,int color)
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
}
