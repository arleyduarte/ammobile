package com.amdp.android.ammobile;

import com.amdp.android.ammobile.adapter.AMEntityAdapter;
import com.amdp.android.ammobile.handler.AMSearchRouteResourceHandler;
import com.amdp.android.ammobile.handler.AMUserValidationResourceHandler;
import com.amdp.android.basic.gui.R;
import com.amdp.android.basic.gui.RouteActivity;
import com.amdp.android.basic.gui.SplashActivity;
import com.amdp.android.basic.gui.utils.DependencyInjector;
import com.amdp.android.network.NetworkContants;


public class AMSplashActivity extends SplashActivity {

	@Override
	public void configApp() {
        final String serverURL = getString(R.string.server_URL);
        NetworkContants.setServer(serverURL);
        
        //TODO: 
        DependencyInjector.setEntityAdapter(new AMEntityAdapter());
        DependencyInjector.setUserValidationResourceHandler(new AMUserValidationResourceHandler());
        DependencyInjector.setSearchRouteResourceHandler(new AMSearchRouteResourceHandler());
        DependencyInjector.setAfterLoginActivity(new RouteActivity());
        DependencyInjector.setAfterRouteActivity(new AMReportVisitActivity());

	}

}
