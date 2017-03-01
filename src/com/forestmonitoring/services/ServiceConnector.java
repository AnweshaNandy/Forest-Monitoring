package com.forestmonitoring.services;

import org.json.JSONObject;

abstract class ServiceConnector {
	
	protected static String baseURL ="http://192.168.1.47/forest-monitoring/webservice/";//"; // // <--local Server 
	

	
	
	
	protected JSONObject outputJson;

	public static String getBaseURL() {
		return baseURL;
	}

//	public void setBaseURL(String baseURL) {
//		this.baseURL = baseURL;
//	}

	public JSONObject getOutputJson() {
		
		return outputJson;
	}

	public void setOutputJson(JSONObject outputJson) {
		this.outputJson = outputJson;
	}

}
