package com.example.my.first.app;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

public class OpenGLESActivity extends Activity {

	private GLSurfaceView mGLView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }

}
class MyGLSurfaceView extends GLSurfaceView {
	
	private final MyRenderer mRenderer;
	
	public MyGLSurfaceView(Context context) {
		super(context);
		setEGLContextClientVersion(2);
		mRenderer = new MyRenderer();
		setRenderer(mRenderer);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
	
	private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
	private float mPreviousX;
	private float mPreviousY;
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		float x = e.getX();
		float y = e.getY();
		
		
		switch (e.getAction()) {
		case MotionEvent.ACTION_MOVE:
			mRenderer.mX = x;
			mRenderer.mY = y;
//			float dx = x - mPreviousX;
//			float dy = y - mPreviousY;
//			
//			if (y > getHeight() / 2) {
//				dx *= -1;
//			}
//			if (x < getWidth() / 2) {
//				dy *= -1;
//			}
//			mRenderer.mAngle += (dx+dy) * TOUCH_SCALE_FACTOR;
			requestRender();
		}
		mPreviousX = x;
		mPreviousY = y;
//		System.out.println(x + " " + y);
		return true;
		
		
	}
}  
