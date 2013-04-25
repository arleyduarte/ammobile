package com.amdp.bb.basic.handler;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.json.me.JSONException;
import org.json.me.JSONObject;
import com.amdp.bb.api.ResourceHandler;
import com.amdp.bb.api.ResponseActionDelegate;
import com.amdp.bb.basic.entity.RouteBusinessLogic;
import com.amdp.bb.net.NetworkConnectorOAuth;
import com.amdp.bb.net.NetworkConstants;
import com.bb.log.Log4Device;

public class GetRoutesResourceHandler extends ResourceHandler{

	private String userId = "";
	
	public GetRoutesResourceHandler(String userId){
		this.userId = userId;
	}
	
	public void sendAPIMessage(ResponseActionDelegate responseActionDelegate) {
		super.setResponseActionDelegate(responseActionDelegate);
		NetworkConnectorOAuth nc = new NetworkConnectorOAuth(getServiceURL());
		nc.setRequestMethod(getHttpVerb());
		try {

			handlerAPIResponse(nc.getRawHttpResponse());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			
		}
	}	
	
	public void handlerAPIResponse(String rawResponse) {
		
		String initJson = "{\"rutas\":";
		String endJson = "}";
		
		rawResponse = initJson+rawResponse+endJson;
		
		try {
			String responseEncode = new String(rawResponse.getBytes("ISO-8859-1"), "UTF-8");
			rawResponse = responseEncode;
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		JSONObject json = null;
		if (rawResponse != null) {
			try {
				json = new JSONObject(rawResponse);
			} catch (JSONException e) {
				Log4Device.log("Error parsing the raw response from the server. NetworkConnector class.");
			}
		}


		if (json != null) {

			RouteBusinessLogic.getInstance().fromJson(json);
			
			this.getResponseActionDelegate().didSuccessfully("");
		}
		else{
			this.getResponseActionDelegate().didSuccessfully("");
		}
		
	}

	public String getHttpVerb() {
		return NetworkConnectorOAuth.REQUEST_METHOD_GET;
	}

	public String getServiceURL() {
		return NetworkConstants.BASE_URL + "/Ruta/Find?UserId="+userId;
	}

	public void handlerAPIResponse(JSONObject json) {
	}

}
