package com.woowahan.baeminWaiting004.model;

public class WaitingTicketJsonObject {
	
	private String name;
	private int storeId;
	private int headCount;
	private int isStaying;
	private String phoneNumber;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getHeadCount() {
		return headCount;
	}
	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}
	public int getIsStaying() {
		return isStaying;
	}
	public void setIsStaying(int isStaying) {
		this.isStaying = isStaying;
	}
}
