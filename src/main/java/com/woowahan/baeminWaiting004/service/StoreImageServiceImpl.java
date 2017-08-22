package com.woowahan.baeminWaiting004.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowahan.baeminWaiting004.model.StoreImage;
import com.woowahan.baeminWaiting004.repository.StoreImageRepository;

@Service
public class StoreImageServiceImpl implements StoreImageService {

	@Autowired
	private StoreImageRepository storeImageRepository;

	@Override
	public List<StoreImage> getAllStoresImage() {
		List<StoreImage> storeImages = storeImageRepository.findAll();
		
		return storeImages;
	}

	@Override
	public StoreImage findByStoreId(int storeId) {
		StoreImage storeImage = storeImageRepository.findByStoreId(storeId);
		return storeImage;
	}
	
	
}
