package com.amdp.android.ammobile.handler;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import com.amdp.android.basic.entity.APIStatus;
import com.amdp.android.basic.entity.Route;
import com.amdp.android.basic.entity.RouteBLL;
import com.amdp.android.basic.entity.UserBLL;
import com.amdp.android.basic.gui.utils.DependencyInjector;
import com.amdp.android.basic.handler.ResourceHandler;
import com.amdp.android.basic.handler.SearchRouteResourceHandler;
import com.amdp.android.network.NetworkContants;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class AMSearchRouteResourceHandler extends SearchRouteResourceHandler {

	private static String TAG = "AMSearchRouteResourceHandler";

	@Override
	public List<NameValuePair> getValueParams() {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("UserId", UserBLL.getInstanceBLL().getCurrentUser().getUserId()));
		return nameValuePairs;
	}

	@Override
	public String getFullServiceURL() {
		return NetworkContants.getServerURL() + "/Ruta/Find";
	}

	@Override
	public int getHttpVerb() {
		return ResourceHandler.GET;
	}

	@Override
	public void handlerAPIResponse(String json) {
		RouteBLL.getInstance().clear();
		JsonArray array = null;
		APIStatus status = new APIStatus();
		JsonParser parser = new JsonParser();
		try {
			array = parser.parse(json).getAsJsonArray();
			if (array != null) {
				for (int i = 0; i < array.size(); i++) {
					Route itemAux = DependencyInjector.getEntityAdapter().routeFromJson(array.get(i).toString());
					RouteBLL.getInstance().add(itemAux);
				}
				status.setDescription("" + array.size());
				getResponseActionDelegate().didSuccessfully(status);
			}

		} catch (IllegalStateException e) {
			Log.e(TAG, e.getMessage());
			status.setDescription(e.getMessage());
			getResponseActionDelegate().didNotSuccessfully(status);
		}
	}

}
