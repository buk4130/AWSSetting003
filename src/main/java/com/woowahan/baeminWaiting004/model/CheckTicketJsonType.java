package com.woowahan.baeminWaiting004.model;

public class CheckTicketJsonType {
	private String storeName;
	private int currentInLine;
	private int ticketNumber;
	private int isSuccess;
	
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public int getCurrentInLine() {
		return currentInLine;
	}
	public void setCurrentInLine(int currentInLine) {
		this.currentInLine = currentInLine;
	}
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public int getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}
}
