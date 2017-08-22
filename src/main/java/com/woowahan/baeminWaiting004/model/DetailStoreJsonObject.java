package com.woowahan.baeminWaiting004.model;

public class DetailStoreJsonObject {

	private String storeName, storeDescription, storeTel, storeImgUrl;
	private String storeLatitude, storeLongitude;
	private int storeId, storeIsOpened;
	private int currentInLine;
	
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreDescription() {
		return storeDescription;
	}

	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
	}

	public String getStoreTel() {
		return storeTel;
	}

	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
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

	public int getStoreIsOpened() {
		return storeIsOpened;
	}

	public void setStoreIsOpened(int storeIsOpened) {
		this.storeIsOpened = storeIsOpened;
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

	public int getCurrentInLine() {
		return currentInLine;
	}

	public void setCurrentInLine(int currentInLine) {
		this.currentInLine = currentInLine;
	}
	
	
}
