package com.example.pushsample;

import java.io.IOException;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	public static String TAG = "pushSample";
	private GoogleCloudMessaging gcm;
	private Context context;
	private String senderId = "80144562538";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gcm = GoogleCloudMessaging.getInstance(this);
		registerInBackground();
	}
	
	public void registerInBackground(){
		new AsyncTask<Void, Void, String>(){
			@Override
			protected String doInBackground(Void... params) {
				String message = "";
				try{
					if(gcm == null){
						gcm = GoogleCloudMessaging.getInstance(context);
					}
					String regid = gcm.register(senderId);
					message = "デバイス登録完了 regsterID = " + regid;
				}catch(IOException e)
				{
					message = "デバイス登録失敗 error = " + e.getMessage();
				}
				return message;
			}
			@Override
            protected void onPostExecute(String message) {
				Log.d(TAG, message);
            }
		}.execute(null, null, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
