package com.forestmonitoring.activities;

import java.util.HashMap;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
//import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.forestmonitoring.R;
//import com.forestmonitoring.connection.NetworkListener;
//import com.forestmonitoring.interfaces.MyBrodcastReceiverCallback;
//import com.cyberswift.forestmonitoring.services.MyReceiver;
import com.forestmonitoring.services.VolleyTaskManager;
import com.forestmonitoring.utility.ServerResponseCallback;
import com.forestmonitoring.utility.Util;



public class LoginActivity extends Activity implements ServerResponseCallback
{
	
	private EditText et_username, et_password;
	private ImageView iv_username,iv_password;
	private VolleyTaskManager volleyTaskManager;
	private boolean isLoginService = false;
	private Context mcontext=LoginActivity.this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_layout);
	
		initview();
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		//Log.v("called", "onResume");
	}
	
	
	/*******initializing view************/
	private void initview() {
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		iv_username = (ImageView) findViewById(R.id.iv_uname);
		iv_password = (ImageView) findViewById(R.id.iv_password);
		volleyTaskManager = new VolleyTaskManager(LoginActivity.this);
		
		et_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus)
				{
					iv_username.setBackgroundResource(R.drawable.userhover);
					et_username.setTextColor(getResources().getColor(R.color.username_onfocus));
				}
				else
				{
					iv_username.setBackgroundResource(R.drawable.user);
					et_username.setTextColor(getResources().getColor(R.color.username_offfocus));
				}
				
				
			}
		});
		
		et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus)
				{
					iv_password.setBackgroundResource(R.drawable.passwordhover);
					et_password.setTextColor(getResources().getColor(R.color.username_onfocus));
				}
				else
				{
					iv_password.setBackgroundResource(R.drawable.password);
					et_password.setTextColor(getResources().getColor(R.color.username_offfocus));
				}
				
				
			}
		});
	}

	
	
	
	/**
	Method to redirect to Sign in activity
	 */
	public void onSignInClicked(View mView) {
		
		
		openHomeActivity();

		/*if(et_username.getText().toString().length() == 0 ||
				et_password.getText().toString().length() == 0)
		{
			Util.showMessageWithOk(LoginActivity.this, "Please fill all the required fields!");
		}else {
			
			if (Util.checkConnectivity(LoginActivity.this))
				loginWebserviceCalling();
			else
				Util.showMessageWithOk(LoginActivity.this,this.getString(R.string.no_internet));
			

		}*/

		}
	
	
/*********************Method to call Login WebService************************/
	private void loginWebserviceCalling()
	{
		//isLoginCalled=true;
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("USR_EMAIL", et_username.getText().toString());
		paramsMap.put("USR_PASSWORD", et_password.getText().toString());
		isLoginService = true;
		volleyTaskManager.doLogin(paramsMap, true);
	}

	
/*********************Web service on success call back ************************/	
	@Override
	public void onSuccess(JSONObject resultJsonObject) {
		
		if(isLoginService)
		{
			isLoginService = false;
			if (resultJsonObject.optString("status").equalsIgnoreCase("true"))
				//Toast.makeText(mcontext,resultJsonObject.optString("status"), Toast.LENGTH_LONG).show();
				openHomeActivity();
			else
				Toast.makeText(mcontext, "Login failed !!!", Toast.LENGTH_LONG).show();
				
			
			}
		
		}
		
    


/*********************GOTO Home ************************/	
	private void openHomeActivity() {
		Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
		startActivity(intent);
		finish();
	}


/*********************Webservice on error call back ************************/	
	
	@Override
	public void onError() {

	}
	
	@SuppressLint("Wakelock")
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//mWakeLock.release();
	}

}
