package com.woowahan.baeminWaiting004.model;

public class StoreJsonObject {
	
	private String storeName, storeLatitude, storeLongitude, storeAddress, storeImgUrl;
	private int currentInLine;
	
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

	private int storeId, storeIsOpened;

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

	public int getCurrentInLine() {
		return currentInLine;
	}

	public void setCurrentInLine(int currentInLine) {
		this.currentInLine = currentInLine;
	}
	
	
}
