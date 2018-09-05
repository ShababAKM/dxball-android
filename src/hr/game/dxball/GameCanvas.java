package hr.game.dxball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

public class GameCanvas extends View {

	Paint paint;
	float x=0,y=0;
	boolean firstTime=true;
	protected void calculateNextPos(){
		y++;
	}
	protected void onDraw(Canvas canvas) {
		if(firstTime)
		{
			firstTime=false;
			x=canvas.getWidth() / 2;
			y=canvas.getHeight() / 2;
		}
		calculateNextPos();
		canvas.drawRGB(255, 255, 255);
		paint.setColor(Color.RED);
		paint.setStyle(Style.FILL);
		canvas.drawCircle(x,y, 40, paint);
		canvas.drawRect(100, 100, 200, 200, paint);
		invalidate();
	}
	
	public GameCanvas(Context context) {
		super(context);
		paint = new Paint();
	}

}
