package com.amdp.android.ammobile.handler;

import com.amdp.android.basic.entity.APIEntity;
import com.amdp.android.basic.handler.AddEntityResourceHandler;
import com.amdp.android.network.NetworkContants;

public class AddCheckinResourceHandler extends AddEntityResourceHandler {

	public AddCheckinResourceHandler(APIEntity apiEntity) {
		super(apiEntity);

	}

	@Override
	public String getFullServiceURL() {
		return NetworkContants.getServerURL() + "/ReporteOE/Add/";
	}

}
