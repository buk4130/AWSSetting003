package com.woowahan.baeminWaiting004.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="WAITINGTICKET")
public class WaitingTicket {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ticket_number")
	private int ticketNumber;
	@Column(name="waiting_list_id")
	private int waitingListId;
	@Column(name="name")
	private String name;
	@Column(name="member_id")
	private String memberId;
	@Column(name="head_count")
	private int headCount;
	@Column(name="is_staying")
	private int isStaying;
	@Column(name="contact_number")
	private String contactNumber;
	@Column(name="create_time")
	private String createTime;
	@Column(name="deleted")
	private int deleted;
		
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public int getWaitingListId() {
		return waitingListId;
	}
	public void setWaitingListId(int waitingListId) {
		this.waitingListId = waitingListId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}