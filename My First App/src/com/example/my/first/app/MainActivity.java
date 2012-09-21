package com.example.my.first.app;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	static final int PHOTO_REQUEST = 1;
	public final static String EXTRA_THUMB_PHOTO = "com.example.myfirstapp.EXTRA_THUMB_PHOTO";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * called when the user click http button. open new activity to connect to
	 * internet
	 * 
	 * @param view
	 */
	public void openBrowser(View view) {
		Intent intent = new Intent(this, HttpExampleActivity.class);
		startActivity(intent);
	}

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
		/**
		 * uruchominie wewnętrznej aplikacji (pobranie numetu telefonu z pola
		 * tekstowego i wuruchomienie telefonuz nim
		 */
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		System.out.println(message);
		Uri number = Uri.parse("tel:" + message);
		Intent mapIntent = new Intent(Intent.ACTION_DIAL, number);
		PackageManager packageManager = getPackageManager();
		List<ResolveInfo> activities = packageManager.queryIntentActivities(
				mapIntent, 0);
		boolean isIntentSafe = activities.size() > 0;
		if (isIntentSafe) {
			startActivity(mapIntent);
		}
	}
	/**
	 * Method that take a picture
	 * @param view
	 */
	public void takePicture(View view) {

//		/**
//		 * uruchomienie domyślnej akcji dla przycisku
//		 */
//		Intent intent = new Intent(this, DisplayMetssageActivity.class);
//		startActivity(intent);
		
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(takePictureIntent, PHOTO_REQUEST);

		// /**
		// * uruchomienie domyślnej akcji dla przycisku
		// */
		// Intent intent = new Intent(this, DisplayMetssageActivity.class);
		// EditText editText = (EditText) findViewById(R.id.edit_message);
		// String message = editText.getText().toString();
		// intent.putExtra(EXTRA_MESSAGE, message);
		// startActivity(intent);

		// /**
		// * uruchomienie akcji która pobierze strone intermnetową
		// */
		// Intent intent = new Intent(this, HttpExampleActivity.class);
		// EditText editText = (EditText) findViewById(R.id.edit_message);
		// String message = editText.getText().toString();
		// intent.putExtra(EXTRA_MESSAGE, message);
		// startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PHOTO_REQUEST) {
			if (resultCode == RESULT_OK){
				/*
				 * Method that present picture just taken
				 */
				Intent intent = new Intent(this, PhotoActivity.class);
				intent.putExtra(EXTRA_THUMB_PHOTO, data.getExtras());
				startActivity(intent);
				
				
			}
		}
	}
	
}
