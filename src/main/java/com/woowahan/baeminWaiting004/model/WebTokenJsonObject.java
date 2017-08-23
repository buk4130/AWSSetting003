package com.woowahan.baeminWaiting004.model;

import java.util.ArrayList;
import java.util.List;

public class WebTokenJsonObject {

	private InnerToken token;
	private ArrayList<InnerMenu> menus;
	private String status;
	
	public InnerToken getToken() {
		return token;
	}
	public void setToken(InnerToken token) {
		this.token = token;
	}	
	public ArrayList<InnerMenu> getMenus() {
		return menus;
	}
	public void setMenus(ArrayList<InnerMenu> menus) {
		this.menus = menus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
