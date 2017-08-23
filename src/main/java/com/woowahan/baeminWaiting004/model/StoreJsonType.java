package com.woowahan.baeminWaiting004.model;

import java.util.ArrayList;

public class StoreJsonType {
	
	private String storeName, storeLatitude, storeLongitude, storeAddress, storeImgUrl;
	private String storeTel, storeDesc, memberId;
	private int storeId, storeIsOpened;
	private ArrayList<InnerMenu> menus;
	
	
	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStoreImgUrl() {
		return storeImgUrl;
	}

	public void setStoreImgUrl(String storeImgUrl) {
		this.storeImgUrl = storeImgUrl;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreLatitude() {
		return storeLatitude;
	}

	public void setStoreLatitude(String storeLatitude) {
		this.storeLatitude = storeLatitude;
	}

	public String getStoreLongitude() {
		return storeLongitude;
	}

	public void setStoreLongitude(String storeLongitude) {
		this.storeLongitude = storeLongitude;
	}

	public int getStoreIsOpened() {
		return storeIsOpened;
	}

	public void setStoreIsOpened(int storeIsOpened) {
		this.storeIsOpened = storeIsOpened;
	}

	public String getStoreTel() {
		return storeTel;
	}

	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}

	public String getStoreDesc() {
		return storeDesc;
	}

	public void setStoreDesc(String storeDesc) {
		this.storeDesc = storeDesc;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public ArrayList<InnerMenu> getMenus() {
		return menus;
	}

	public void setMenus(ArrayList<InnerMenu> menus) {
		this.menus = menus;
	}
	
	
	
}
