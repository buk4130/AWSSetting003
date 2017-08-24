package com.woowahan.baeminWaiting004.service;

import java.util.List;

import com.woowahan.baeminWaiting004.model.WaitingList;

public interface WaitingListService {
	void addWaitingList(int storeId);
	WaitingList findByWaitingListId(int storeId);
	List<WaitingList> getAllWaitingList();
	void updateWaitingList(WaitingList waitingList);
}
