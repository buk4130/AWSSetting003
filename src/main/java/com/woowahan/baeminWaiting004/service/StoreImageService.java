package com.woowahan.baeminWaiting004.service;

import java.util.List;

import com.woowahan.baeminWaiting004.model.StoreImage;

public interface StoreImageService {
	StoreImage findByStoreId(int storeId);
	
	List<StoreImage> getAllStoresImage();
}
