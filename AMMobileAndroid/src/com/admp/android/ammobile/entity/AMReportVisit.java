package com.admp.android.ammobile.entity;

import com.amdp.android.basic.entity.APIEntity;

public class AMReportVisit extends APIEntity {

	public static String EFECTIVA = "1";
	public static String NO_EFECTIVA = "2";

	private String RutaID = "";
	private String INO = "";
	private String EG = "";
	private String Datos = "";
	private String Causal = "";
	private String GestionOE = "";
	private String EstadoVisitaID = "";
	private String UsuarioID = "";

	public String getRutaID() {
		return RutaID;
	}

	public void setRutaID(String rutaID) {
		RutaID = rutaID;
	}

	public String getINO() {
		return INO;
	}

	public void setINO(String iNO) {
		INO = iNO;
	}

	public String getEG() {
		return EG;
	}

	public void setEG(String eG) {
		EG = eG;
	}

	public String getDatos() {
		return Datos;
	}

	public void setDatos(String datos) {
		Datos = datos;
	}

	public String getCausal() {
		return Causal;
	}

	public void setCausal(String causal) {
		Causal = causal;
	}

	public String getGestionOE() {
		return GestionOE;
	}

	public void setGestionOE(String gestionOE) {
		GestionOE = gestionOE;
	}

	public String getEstadoVisitaID() {
		return EstadoVisitaID;
	}

	public void setEstadoVisitaID(String estadoVisitaID) {
		EstadoVisitaID = estadoVisitaID;
	}

	public String getUsuarioID() {
		return UsuarioID;
	}

	public void setUsuarioID(String usuarioID) {
		UsuarioID = usuarioID;
	}

}
