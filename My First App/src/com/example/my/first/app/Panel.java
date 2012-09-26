package com.example.my.first.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {

	private CanvasThread canvasthread;
	Paint paint;
	Bitmap kangoo;
	Bitmap wskznik;
	private int degree = 0;
	private int start = 0;
	private int end = 260;
	private int current = 50;
	private double finalDegree;

	public void setData(int start, int end, int current){
		this.start = start;
		this.end = end;
		this.current = current;
		count();
	}
	private void count(){
		double skala = (end - start)/260;
		finalDegree = current/skala;
	}
	
	public Panel(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		getHolder().addCallback(this);
		canvasthread = new CanvasThread(getHolder(), this);
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		count();
		paint = new Paint();
		kangoo = BitmapFactory.decodeResource(getResources(),
				R.drawable.licznik);
		wskznik = BitmapFactory.decodeResource(getResources(),
				R.drawable.wskaznik);
		canvasthread.setRunning(true);
		canvasthread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		canvasthread.setRunning(false);
		while (retry) {
			try {
				canvasthread.join();
				retry = false;
			} catch (InterruptedException e) {
				// we will try it again and again...
			}
		}

	}

	@Override
	public void onDraw(Canvas canvas) {

		canvas.drawColor(Color.BLACK);

		canvas.save();
		canvas.drawBitmap(kangoo, 0, 0, null);

		canvas.rotate(degree, 200, 200);
		if (degree < finalDegree) {
			degree += 1;
		}
		canvas.drawBitmap(wskznik, 0, 0, null);
		canvas.restore();

		// Matrix transform = new Matrix();
		// transform.setTranslate(xOfCentre, yOfCentre);
		// transform.preRotate(1.1f);
		// canvas.drawBitmap(wskznik, transform, null);

		// invalidate();

	}

}
