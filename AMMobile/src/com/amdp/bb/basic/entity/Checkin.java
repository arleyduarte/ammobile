package com.amdp.bb.basic.entity;

public class Checkin {
	
	public static String EFECTIVA = "1";
	public static String NO_EFECTIVA = "2";
	
	private String routeCode = "";
	private String ino = "";
	private String eg = "";
	private String datos = "";
	private String gestionO  = "";
	private String causal = "";
	private String EstadoVisitaID = "";
	
	public Checkin(RouteEntity currentRoute) {
		setRouteCode(currentRoute.getCode());
	}
	public String getIno() {
		return ino;
	}
	public void setIno(String ino) {
		this.ino = ino;
	}
	public String getEg() {
		return eg;
	}
	public void setEg(String eg) {
		this.eg = eg;
	}
	public String getDatos() {
		return datos;
	}
	public void setDatos(String datos) {
		this.datos = datos;
	}
	public String getGestionOE() {
		return gestionO;
	}
	public void setGestionOE(String gestionO) {
		this.gestionO = gestionO;
	}
	public String getRouteCode() {
		return routeCode;
	}
	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}
	public String getEstadoVisitaID() {
		return EstadoVisitaID;
	}
	public void setEstadoVisitaID(String estadoVisitaID) {
		EstadoVisitaID = estadoVisitaID;
	}
	public String getCausal() {
		return causal;
	}
	public void setCausal(String causal) {
		this.causal = causal;
	}
	
	

}
