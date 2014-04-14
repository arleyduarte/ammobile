package com.admp.android.ammobile.entity;

import com.amdp.android.basic.entity.Route;

public class AMRoute extends Route {
	private String code = "";
	private String address = "";
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


	public String getVenueName() {
		String info = rutaNo + " " + hora + " " + consecutivo;

		return info;
	}

	public String getDescritionInfo() {
		String info = guide + "\n" + address + "\n" + neigthborhood + "\n" + city + "\n" + departamento + "\n" + ref + "\n" + quadrant + "\n" + p + "\n" + note + "\n" + "";

		return info;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getRutaNo() {
		return rutaNo;
	}

	public void setRutaNo(String rutaNo) {
		this.rutaNo = rutaNo;
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
