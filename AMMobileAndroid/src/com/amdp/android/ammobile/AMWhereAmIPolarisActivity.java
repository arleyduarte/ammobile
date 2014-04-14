package com.amdp.android.ammobile;

import java.util.ArrayList;
import android.content.Intent;
import com.amdp.android.api.IStatus;
import com.amdp.android.basic.entity.Route;
import com.amdp.android.basic.entity.RouteBLL;
import com.amdp.android.basic.gui.WhereAmIPolarisActivity;
import com.cyrilmottier.polaris.Annotation;
import com.google.android.maps.GeoPoint;


public class AMWhereAmIPolarisActivity extends WhereAmIPolarisActivity {
	
	@Override
	public void didSuccessfully(IStatus status) {
		Intent intent = new Intent(AMWhereAmIPolarisActivity.this, AMReportVisitActivity.class);
		startActivity(intent);	
		finish();
	}
	
	@Override
	protected ArrayList<Annotation> getAnnotations() {
		ArrayList<Annotation> annotations = new ArrayList<Annotation>();
		if(RouteBLL.getInstance().getCurrentRoute().getVenueLatitude() != null && RouteBLL.getInstance().getCurrentRoute().getVenueLatitude().length() != 0){
			Annotation annotation = adapterAnnotation(RouteBLL.getInstance().getCurrentRoute());
			annotations.add(annotation);
		}
		

		return annotations;
	}
	
	public  Annotation adapterAnnotation(Route rute) {


		
		int latitude = (int) (Double.parseDouble(rute.getVenueLatitude()) * 1E6);
		int longitude = (int) (Double.parseDouble(rute.getVenueLongitude()) * 1E6);

		GeoPoint geopoint = new GeoPoint(latitude, longitude);

		Annotation annotation = new Annotation(geopoint, rute.getName(), rute.getAddress());


		return annotation;
	}

}
