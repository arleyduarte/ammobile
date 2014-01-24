package com.amdp.android.ammobile.adapter;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import com.amdp.android.api.IStatus;
import com.amdp.android.basic.entity.APIStatus;
import com.amdp.android.basic.entity.Route;
import com.amdp.android.basic.entity.User;
import com.amdp.android.basic.entity.WrapperViewItem;
import com.amdp.android.basic.gui.utils.EntityAdapter;

import com.google.gson.Gson;

public class AMEntityAdapter implements EntityAdapter {


	@Override
	public User userFromJson(String json) {
		Gson gson = new Gson();
		APIStatus status = gson.fromJson(json, APIStatus.class);
		User user = new User();
		user.setUserId(status.getCode());
		user.setName(status.getDescription());
		return user;
	}

	@Override
	public IStatus statusFromJson(String json) {
		Gson gson = new Gson();
		APIStatus status = gson.fromJson(json, APIStatus.class);
		return status;
	}

	@Override
	public Route routeFromJson(String json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NameValuePair> getNewRouteParams(Route route) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignWrapperViewItems(ArrayList<WrapperViewItem> wrapperViewItems) {
		// TODO Auto-generated method stub
		
	}







}
