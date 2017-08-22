package com.woowahan.baeminWaiting004.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WAITINGLIST")
public class WaitingList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="waiting_list_id")
	private int waitingListId;
	
	@Column(name="store_id")
	private int storeId;
	
	//줄 개수 
	@Column(name="current_in_line")
	private int currentInLine;

	public int getWaitingListId() {
		return waitingListId;
	}

	public void setWaitingListId(int waitingListId) {
		this.waitingListId = waitingListId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getCurrentInLine() {
		return currentInLine;
	}

	public void setCurrentInLine(int currentInLine) {
		this.currentInLine = currentInLine;
	}
}
