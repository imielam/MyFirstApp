package com.example.my.first.app;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

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
	
	public MyGLSurfaceView(Context context) {
		super(context);
		setEGLContextClientVersion(2);
		setRenderer(new MyRenderer());
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
}  
