package edu.wm.cs.cs301.DavidMontenegro.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
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

	public static int getColorEncoding(int red, int green, int blue, int a) {
		return Color.valueOf(red, green, blue, a).toArgb();
	}
	private Color getBackgroundColor(float percentToExit, boolean top) {
		return top? blend(Color.valueOf(Color.BLACK), Color.valueOf(Color.parseColor("#916f41")), percentToExit) :
				blend(Color.valueOf(Color.LTGRAY), Color.valueOf(Color.parseColor("#115740")), percentToExit);
	}

	private Color blend(Color fstColor, Color sndColor, double weightFstColor) {
		if (weightFstColor < 0.1)
			return sndColor;
		if (weightFstColor > 0.95)
			return fstColor;
		double r = weightFstColor * fstColor.red() + (1-weightFstColor) * sndColor.red();
		double g = weightFstColor * fstColor.green() + (1-weightFstColor) * sndColor.green();
		double b = weightFstColor * fstColor.blue() + (1-weightFstColor) * sndColor.blue();
		double a = Math.max(fstColor.alpha(), sndColor.alpha());

		return Color.valueOf((float) r, (float) g, (float) b, (float) a);
	}

	@Override
	public void addBackground(float percentToExit) {
		setColor(getBackgroundColor(percentToExit, true).toArgb());
		addFilledRectangle(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT/2);
		setColor(getBackgroundColor(percentToExit, false).toArgb());
		addFilledRectangle(0, Constants.VIEW_HEIGHT/2, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT/2);
	}

	@Override
	public void addFilledRectangle(int x, int y, int width, int height) {
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(x, y, x+width, y+height, paint);
	}

	@Override
	public void addFilledPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		paint.setStyle(Paint.Style.FILL);
		Path path = new Path();
		path.reset();
		path.moveTo(xPoints[0], yPoints[0]);
		for(int i = 1; i < nPoints; i++) {
			path.lineTo(xPoints[i], yPoints[i]);
		}
		path.lineTo(xPoints[0], yPoints[0]);
		canvas.drawPath(path, paint);
	}

	@Override
	public void addPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		paint.setStyle(Paint.Style.STROKE);
		Path path = new Path();
		path.reset();
		path.moveTo(xPoints[0], yPoints[0]);
		for(int i = 1; i < nPoints; i++) {
			path.lineTo(xPoints[i], yPoints[i]);
		}
		path.lineTo(xPoints[0], yPoints[0]);
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
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawArc(x, y, x+width, y+height, startAngle, arcAngle, false, paint);
	}

	@Override
	public void addMarker(float x, float y, String str) {
		paint.setTextSize(25);
		canvas.drawText(str, x, y, paint);
	}

	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	public void onDraw(Canvas c) {
		c.drawBitmap(bitmap, null, new Rect(0,0,1130,1130), paint);
	}
}
