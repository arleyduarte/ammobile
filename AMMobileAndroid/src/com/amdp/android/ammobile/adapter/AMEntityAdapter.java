package com.amdp.android.ammobile.adapter;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.admp.android.ammobile.entity.AMRoute;
import com.amdp.android.api.IStatus;
import com.amdp.android.basic.entity.APIStatus;
import com.amdp.android.basic.entity.Route;
import com.amdp.android.basic.entity.User;
import com.amdp.android.basic.entity.UserBLL;
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

		UserBLL.getInstanceBLL().setCurrentUser(user);

		return user;
	}

	@Override
	public IStatus statusFromJson(String json) {
		Gson gson = new Gson();
		APIStatus status = gson.fromJson(json, APIStatus.class);
		return status;
	}

	@Override
	public Route routeFromJson(String jsonS) {
		AMRoute aux = new AMRoute();

		try {
			final JSONObject json = new JSONObject(jsonS);
			aux.setId(json.getString("RutaID"));
			aux.setState(Route.ROUTE_PENDING);
			aux.setCode(json.getString("RutaID"));
			aux.setHora(json.getString("Hora"));
			aux.setRutaNo(json.getString("RutaNo"));
			aux.setAddress(json.getString("Direccion"));
			aux.setNeigthborhood(json.getString("Barrio"));
			aux.setCity(json.getString("Ciudad"));
			aux.setDepartamento(json.getString("Departamento"));
			aux.setRef(json.getString("Referente"));
			aux.setQuadrant(json.getString("Sector"));
			aux.setConsecutivo(json.getString("RutaConsecutivo"));
			aux.setGuide(json.getString("Guia"));
			aux.setP(json.getString("E"));
			aux.setNote(json.getString("NotaOperativa"));

		} catch (JSONException e) {

		}
		return aux;

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
