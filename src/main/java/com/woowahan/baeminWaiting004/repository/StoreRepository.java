package com.woowahan.baeminWaiting004.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woowahan.baeminWaiting004.model.Store;

public interface StoreRepository extends JpaRepository<Store, Integer>{
	
	Store findByMemberId(String memberId);
	Store findById(int storeId);
	List<Store> findByIdBetween(int firstNum, int lastNum);
	
}
