package com.woowahan.baeminWaiting004.model;

public class WaitingTicketJsonType {
	
	private String memberId;
	private int headCount;
	private int isStaying;
	private String contactNumber;
	private String name;
	private int waitingListId;
	private String createTime;
	private int ticketNumber;
	private int isSuccess;	

	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWaitingListId() {
		return waitingListId;
	}
	public void setWaitingListId(int waitingListId) {
		this.waitingListId = waitingListId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

}
