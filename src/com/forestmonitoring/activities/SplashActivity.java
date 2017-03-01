package com.forestmonitoring.activities;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.forestmonitoring.R;
//import com.cyberswift.forestmonitoring.services.MyReceiver;
import com.forestmonitoring.services.VolleyTaskManager;
import com.forestmonitoring.utility.ServerResponseCallback;
import com.forestmonitoring.utility.Util;

public class SplashActivity extends Activity

{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_layout);

		initview();
		new SplashTimerTask().execute();
	}

	private void initview() {

	}

	private class SplashTimerTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(2345);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			openLoginActivity();
	}

	public void openLoginActivity() {
		Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	private void openHomeActivity() {
		
	}

	
	}
	
	}
