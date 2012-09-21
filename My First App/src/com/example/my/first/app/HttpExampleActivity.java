package com.example.my.first.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HttpExampleActivity extends Activity {
	private static final String DEBUG_TAG = "HttpExample";
	private EditText urlText;
	private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_example);
        urlText = (EditText) findViewById(R.id.myUrl);
        textView = (TextView) findViewById(R.id.myText);
    }

    public void downloadData(View view) {
    	String stringUrl ="http://www." + urlText.getText().toString();
    	ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo  = connMgr.getActiveNetworkInfo();
    	if (networkInfo != null && networkInfo.isConnected()) {
    		new DownloadWebpageText().execute(stringUrl);
    	} else {
    		textView.setText("No network connected available.");
    	}
    }
    
    private class DownloadWebpageText extends AsyncTask {
    	
		@Override
		protected String doInBackground(Object... urls) {
			System.out.println(urls[0]);
			try {
				return downloadUrl((String)urls[0]);
			} catch (IOException e) {
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}

		private String downloadUrl(String myurl) throws IOException{
			InputStream is = null;
			int len = 500;
			
			try {
				URL url = new URL(myurl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(1000);
				conn.setConnectTimeout(15000);
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.connect();
				int response = conn.getResponseCode();
				Log.d(DEBUG_TAG, "The responce is: " + response);
				is = conn.getInputStream();
				
				String contentAsString = readIt(is, len);
				return contentAsString;
			} finally {
				if (is != null) {
					is.close();
				}
			}
		}
		
		private String readIt(InputStream is, int len) throws IOException, UnsupportedEncodingException {
			Reader reader = new InputStreamReader(is, "UTF-8");
			char[] buffer = new char[len];
			StringBuilder sb = new StringBuilder();
			while (reader.read(buffer) != -1) {
				sb.append(buffer);
			}
			return sb.toString();
		}

		@Override
		protected void onPostExecute(Object result) {
			System.out.println(((String)result).length());
			textView.setText((String)result);
		}

    }
}
