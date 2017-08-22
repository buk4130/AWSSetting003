package com.woowahan.baeminWaiting004.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woowahan.baeminWaiting004.model.StoreImage;

public interface StoreImageRepository extends JpaRepository<StoreImage, Integer> {

	StoreImage findByStoreId(int storeId);
}
