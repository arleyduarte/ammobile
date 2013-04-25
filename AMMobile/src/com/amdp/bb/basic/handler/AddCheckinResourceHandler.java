package com.amdp.bb.basic.handler;


import net.rim.blackberry.api.browser.URLEncodedPostData;

import org.json.me.JSONObject;

import com.amdp.bb.api.ResourceHandler;
import com.amdp.bb.basic.entity.Checkin;
import com.amdp.bb.basic.entity.Status;
import com.amdp.bb.basic.entity.UserBLL;
import com.amdp.bb.net.NetworkConstants;

public class AddCheckinResourceHandler extends ResourceHandler{
	private Checkin checkin;

	public AddCheckinResourceHandler(Checkin checkin) {
		this.checkin = checkin;

	}
	public void handlerAPIResponse(JSONObject json) {
		Status status = new Status(json);

		if (status.isSuccess()) {
			this.getResponseActionDelegate().didSuccessfully(status.getDescription());
		} else {
			this.getResponseActionDelegate().didNotSuccessfully(status.getCode());
		}
		
	}
	public URLEncodedPostData getValueParams() {
		URLEncodedPostData postData = new URLEncodedPostData("UTF-8", false);
		postData.append("RutaID", checkin.getRouteCode());
		postData.append("INO", checkin.getIno());
		postData.append("EG", checkin.getEg());
		postData.append("Datos", checkin.getDatos());
		postData.append("Causal", checkin.getCausal());
		postData.append("GestionOE", checkin.getGestionOE());
		postData.append("EstadoVisitaID", checkin.getEstadoVisitaID());
		postData.append("UsuarioID", UserBLL.getInstanceBLL().getCurrentUser().getUserId());
		return postData;
	}
	public String getServiceURL() {
		return NetworkConstants.BASE_URL+"/ReporteOE/Add/";
	}

}
