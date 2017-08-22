package com.woowahan.baeminWaiting004.service;

import java.util.List;

import com.woowahan.baeminWaiting004.model.Store;

public interface StoreService {

	List<Store> getAllStores();

	void addStore(String storeName, String storeTel, String storeAddress, 
			String storeDescription, String storeLatitude, String storeLongitude, int storeIsOpened, String memberId,
			String topLeftLat, String topLeftLong, String bottomLeftLat, String bottomLeftLong);
	
	//jw
	Store getStoreId(String memberId);
	
	//jw
	void addStore2(String storeName, String storeTel, String storeAddress, String storeDescription,
			String storeLatitude, String storeLongitude, String memberId);
	
	void addStore3(String storeName, String storeTel, String storeAddress, String storeDescription,
			String storeLatitude, String storeLongitude, String memberId, int storeId); 
}
