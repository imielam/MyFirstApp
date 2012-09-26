package com.example.my.first.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class RotateActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canvas_layout);
        Intent intent = getIntent();
		String message2 = intent.getStringExtra("startPoint");
		String message = intent.getStringExtra("endPoint");
		String message3 = intent.getStringExtra("currentPosition");
		System.err.println(message + " " + message2 + " " + message3);
        Panel editText = (Panel) findViewById(R.id.SurfaceView01);
//        editText.setData(Integer.parseInt(message), Integer.parseInt(message2), Integer.parseInt(message3));
//        editText.setData(0,260,250);
        
    }
    

}
