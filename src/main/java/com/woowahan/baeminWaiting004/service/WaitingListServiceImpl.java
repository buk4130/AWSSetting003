package com.woowahan.baeminWaiting004.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowahan.baeminWaiting004.model.WaitingList;
import com.woowahan.baeminWaiting004.repository.WaitingListRepository;

@Service
public class WaitingListServiceImpl implements WaitingListService{
	
	@Autowired
	private WaitingListRepository waitingListRepository;

	@Override
	public void addWaitingList(int storeId) {
		WaitingList waitingList = new WaitingList();
		waitingList.setStoreId(storeId);
		waitingListRepository.save(waitingList);
	}

	@Override
	public WaitingList findByWaitingListId(int storeId) {
		WaitingList waitingList = waitingListRepository.findByWaitingListId(storeId);
		return waitingList;
	}

	@Override
	public List<WaitingList> getAllWaitingList() {
		List<WaitingList> waitingLists = waitingListRepository.findAll();
		return waitingLists;
	}

	@Override
	public void updateWaitingList(WaitingList waitingList) {
		waitingListRepository.save(waitingList);
	}

}
