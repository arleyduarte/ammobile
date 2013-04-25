package com.amdp.bb.basic.entity;

import java.util.Vector;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

public class RouteBusinessLogic implements APIRemoteObject {
	private static RouteBusinessLogic instance;
	private Vector categories = new Vector();

	private RouteBusinessLogic() {
	}

	public static RouteBusinessLogic getInstance() {
		if (instance == null) {
			instance = new RouteBusinessLogic();
		}
		return instance;
	}

	public Vector getCategories() {
		return categories;
	}

	public void add(RouteEntity category) {
		categories.addElement(category);
	}
	


	public void fromJson(JSONObject json) {
		JSONArray jsonArray;
		try {
			jsonArray = json.getJSONArray("rutas");
			for (int i = 0; i < jsonArray.length(); i++) {
				RouteEntity category = new RouteEntity();
				category.fromJson(new JSONObject(jsonArray.getString(i).toString()));

				add(category);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
