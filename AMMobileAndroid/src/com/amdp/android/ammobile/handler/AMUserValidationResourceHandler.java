package com.amdp.android.ammobile.handler;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.amdp.android.basic.handler.UserValidationResourceHandler;
import com.amdp.android.network.NetworkContants;

public class AMUserValidationResourceHandler extends UserValidationResourceHandler {

	@Override
	public List<NameValuePair> getValueParams() {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("login", getUsername()));
		nameValuePairs.add(new BasicNameValuePair("password", getPassword()));
		return nameValuePairs;
	}

	@Override
	public String getFullServiceURL() {
		return NetworkContants.getServerURL() + "/Usuario/ValidateUser/";
	}

}