package com.forestmonitoring.utility;

import org.json.JSONObject;

public interface ServerResponseCallback {
	public void onSuccess(JSONObject resultJsonObject);

	public void onError();

}
