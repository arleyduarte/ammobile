package com.amdp.bb.basic.entity;

import org.json.me.JSONException;
import org.json.me.JSONObject;

public class RouteEntity implements APIRemoteObject {
	private String code="";
	private String description="";
	private String address="Mas informacion \n hola";
	private String city = "";
	private String neigthborhood = "";
	private String ref = "";
	private String quadrant = "";
	private String guide = "";
	private String p = "";
	private String note = "";
	private String rutaNo = "";
	private String departamento = "";
	private String consecutivo = "";
	private String hora = "";
	
	
	public String getRutaNo() {
		return rutaNo;
	}
	public void setRutaNo(String rutaNo) {
		this.rutaNo = rutaNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNeigthborhood() {
		return neigthborhood;
	}
	public void setNeigthborhood(String neigthborhood) {
		this.neigthborhood = neigthborhood;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getQuadrant() {
		return quadrant;
	}
	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}
	public String getGuide() {
		return guide;
	}
	public void setGuide(String guide) {
		this.guide = guide;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public RouteEntity(String code, String description) {
		this.code = code;
		this.description = description;
		
	}
	public RouteEntity() {
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		String info = rutaNo + " "
				+ hora +" "
				+ consecutivo;
		
		return info;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescritionInfo(){
		String info = guide + "\n"
				+ address +"\n"
				+ neigthborhood +"\n"
				+ city +"\n"
				+ departamento +"\n"
				+ ref +"\n"
				+ quadrant +"\n"
				+ p +"\n"
				+ note +"\n"
				+"";
		
		return info;
	}
	
	public void fromJson(JSONObject json) {
		try {
			setCode(json.getString("RutaID"));
			setHora(json.getString("Hora"));
			setRutaNo(json.getString("RutaNo"));
			setAddress(json.getString("Direccion"));
			setNeigthborhood(json.getString("Barrio"));
			setCity(json.getString("Ciudad"));
			setDepartamento(json.getString("Departamento"));
			setRef(json.getString("Referente"));
			setQuadrant(json.getString("Sector"));
			setConsecutivo(json.getString("RutaConsecutivo"));
			setGuide(json.getString("Guia"));
			setP(json.getString("E"));
			setNote(json.getString("NotaOperativa"));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	public String getAddress() {
		return address;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
}
