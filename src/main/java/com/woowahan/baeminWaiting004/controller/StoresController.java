package com.woowahan.baeminWaiting004.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowahan.baeminWaiting004.model.Store;
import com.woowahan.baeminWaiting004.model.StoreImage;
import com.woowahan.baeminWaiting004.model.StoreJsonObject;
import com.woowahan.baeminWaiting004.model.WaitingList;
import com.woowahan.baeminWaiting004.service.StoreImageService;
import com.woowahan.baeminWaiting004.service.StoreService;
import com.woowahan.baeminWaiting004.service.WaitingListService;

@Controller
public class StoresController {
	
	@Autowired
	private StoreService storeService;

	@Autowired
	private StoreImageService storeImageService;
	
	@Autowired
	private WaitingListService waitingListService;
	
	@RequestMapping(value = "/stores", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getStores() throws JsonProcessingException{
		
		List<Store> stores = storeService.getAllStores();
		List<StoreJsonObject> storeJsonTypeList = new ArrayList<StoreJsonObject>();
		
		for (int i = 0; i < stores.size(); i++) {
			StoreJsonObject storeJsonObject = new StoreJsonObject();
			Store store = stores.get(i);
			storeJsonObject.setStoreAddress(store.getAddress());
			storeJsonObject.setStoreId(store.getId());
			storeJsonObject.setStoreIsOpened(store.getOpened());
			storeJsonObject.setStoreLatitude(store.getLatitude());
			storeJsonObject.setStoreLongitude(store.getLongitude());
			storeJsonObject.setStoreName(store.getTitle());
			
			StoreImage storeImage = storeImageService.findByStoreId(store.getId());
			storeJsonObject.setStoreImgUrl(storeImage.getImgUrl());
			
			WaitingList waitingList = waitingListService.findByWaitingListId(store.getId());
			storeJsonObject.setCurrentInLine(waitingList.getCurrentInLine());
			
			storeJsonTypeList.add(storeJsonObject);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(storeJsonTypeList);
	}
}
