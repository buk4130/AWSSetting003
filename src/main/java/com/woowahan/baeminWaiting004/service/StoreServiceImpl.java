package com.woowahan.baeminWaiting004.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowahan.baeminWaiting004.model.Store;
import com.woowahan.baeminWaiting004.repository.StoreRepository;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	private StoreRepository storeRepository;

	@Override
	public List<Store> getAllStores() {
		
		List<Store> stores = storeRepository.findAll();
		
		return stores;
	}
	

	@Override
	public void addStore(String storeName, String storeTel, String storeAddress, String storeDescription,
			String storeLatitude, String storeLongitude, int storeIsOpened, String memberId,
			String topLeftLat, String topLeftLong, String bottomLeftLat, String bottomLeftLong) {
		
		Store store = new Store();
		store.setTitle(storeName);
		store.setTel(storeTel);
		store.setAddress(storeAddress);
		store.setDescription(storeDescription);
		store.setLatitude(storeLatitude);
		store.setLongitude(storeLongitude);
		store.setOpened(storeIsOpened);
		store.setMemberId(memberId);
		storeRepository.save(store);
	}


	@Override
	public Store getStoreInfoByMemberId(String memberId) {
		return storeRepository.findByMemberId(memberId);
	}
	
	@Override
	public void firstAddStore(String storeName, String storeTel, String storeAddress, String storeDescription,
			String storeLatitude, String storeLongitude, String memberId) {
		
		Store store = new Store();
		store.setTitle(storeName);
		store.setTel(storeTel);
		store.setAddress(storeAddress);
		store.setDescription(storeDescription);
		store.setLatitude(storeLatitude);
		store.setLongitude(storeLongitude);		
		store.setMemberId(memberId);

		storeRepository.save(store);
	}
	
	@Override
	public void updateStore(String storeName, String storeTel, String storeAddress, String storeDescription,
			String storeLatitude, String storeLongitude, String memberId, int storeId, int storeIsOpened) {
		
		Store store = new Store();
		store.setTitle(storeName);
		store.setTel(storeTel);
		store.setAddress(storeAddress);
		store.setDescription(storeDescription);
		store.setLatitude(storeLatitude);
		store.setLongitude(storeLongitude);		
		store.setMemberId(memberId);
		store.setId(storeId);
		store.setOpened(storeIsOpened);
		
		storeRepository.save(store);
	}
}
