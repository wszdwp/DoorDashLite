//Created by Debajyoti Das
//Copyright  2012 Vormittag Associates, Inc. All rights reserved.

package com.codingpan.doordashlite.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class MyPreferences {
	
	private SharedPreferences mySharedPreferences;

	public MyPreferences(Context context) {
		this.mySharedPreferences = context.getSharedPreferences("S2kSharedPrefs", Activity.MODE_PRIVATE);
	}

	public String getServiceUrl() {
		return mySharedPreferences.getString("serviceUrl", "https://api.doordash.com/v2/");
	}

	public void setServiceUrl(String serviceUrl) {
		SharedPreferences.Editor editor = mySharedPreferences.edit();
		editor.putString("serviceUrl", serviceUrl);
		editor.commit();
	}

}
