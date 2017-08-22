package com.woowahan.baeminWaiting004.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woowahan.baeminWaiting004.model.WaitingList;

public interface WaitingListRepository extends JpaRepository<WaitingList, Integer>{
	WaitingList findByWaitingListId(int storeId);
}
