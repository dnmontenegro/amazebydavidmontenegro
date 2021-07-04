package edu.wm.cs.cs301.DavidMontenegro.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;


public class MazePanel extends View implements P5Panel {
	private Paint paint;
	private Bitmap bitmap;
	private Canvas canvas;

	public MazePanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		bitmap = Bitmap.createBitmap(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
	}

	@Override
	public void commit() {
		invalidate();
	}

	@Override
	public boolean isOperational() {
		return paint != null;
	}

	@Override
	public void setColor(int rgb) {
		paint.setColor(rgb);
	}

	@Override
	public int getColor() {
		return paint.getColor();
	}

	public static int getColorEncoding(int red, int green, int blue) {
		return Color.argb(255, red, green, blue);
	}

	@Override
	public void addBackground(float percentToExit) {

	}

	@Override
	public void addFilledRectangle(int x, int y, int width, int height) {
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(x, y, x+width, y+height, paint);
	}

	@Override
	public void addFilledPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		paint.setStyle(Paint.Style.FILL);

	}

	@Override
	public void addPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		Path path = new Path();
		path.reset();
		path.moveTo(xPoints[0], yPoints[0]);
		for(int i = 1; i < nPoints; i ++) {
			path.lineTo(xPoints[i], yPoints[i]);
		}
		path.close();
		canvas.drawPath(path, paint);
	}

	@Override
	public void addLine(int startX, int startY, int endX, int endY) {
		canvas.drawLine(startX, startY, endX, endY, paint);
	}

	@Override
	public void addFilledOval(int x, int y, int width, int height) {
		paint.setStyle(Paint.Style.FILL);
		canvas.drawOval(x, y, x+width, y+height, paint);
	}

	@Override
	public void addArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		canvas.drawArc(x, y, x+width, y+height, startAngle, arcAngle, true, paint);
	}

	@Override
	public void addMarker(float x, float y, String str) {

	}

	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	public void onDraw(Canvas c) {
		c.drawBitmap(bitmap, 0, 0, paint);
	}
}
