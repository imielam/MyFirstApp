package com.example.my.first.app;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

public class PhotoActivity extends Activity {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//        setContentView(R.layout.activity_photo);

		Intent intent = getIntent();
		Bundle extras = intent.getBundleExtra(MainActivity.EXTRA_THUMB_PHOTO);
		Bitmap mImageBitmap = (Bitmap) extras.get("data");
		ImageView imageView = new ImageView(this);
		imageView.setImageBitmap(mImageBitmap);

		setContentView(imageView);
	}


}
