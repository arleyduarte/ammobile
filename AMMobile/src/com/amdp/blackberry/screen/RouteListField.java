package com.amdp.blackberry.screen;

import java.util.Vector;

import net.rim.device.api.ui.component.ListField;

public class RouteListField extends ListField {
	private Vector routes = new Vector();
	
	public RouteListField(){
		
		
		
		setSize(this.routes.size());
	}
}
