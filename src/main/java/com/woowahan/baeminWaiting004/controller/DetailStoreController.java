package com.woowahan.baeminWaiting004.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowahan.baeminWaiting004.model.DetailStoreJsonObject;
import com.woowahan.baeminWaiting004.model.Store;
import com.woowahan.baeminWaiting004.model.StoreImage;
import com.woowahan.baeminWaiting004.model.WaitingList;
import com.woowahan.baeminWaiting004.service.StoreImageService;
import com.woowahan.baeminWaiting004.service.StoreService;
import com.woowahan.baeminWaiting004.service.WaitingListService;

@Controller
public class DetailStoreController {
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private StoreImageService storeImageService;
	
	@Autowired
	private WaitingListService waitingListService;
	
	@RequestMapping(value = "/detailStore", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String detailStore(@RequestParam("storeId")int storeId) throws JsonProcessingException{
		List<Store> stores = storeService.getAllStores();
		DetailStoreJsonObject detailStoreJsonObject = new DetailStoreJsonObject();
		
		for (int i = 0; i < stores.size(); i++) {
			Store store = stores.get(i);
			if(store.getId() == storeId) {
				detailStoreJsonObject.setStoreId(store.getId());
				detailStoreJsonObject.setStoreName(store.getTitle());
				detailStoreJsonObject.setStoreDescription(store.getDescription());
				detailStoreJsonObject.setStoreTel(store.getTel());
				detailStoreJsonObject.setStoreIsOpened(store.getOpened());
				
				StoreImage storeImage = storeImageService.findByStoreId(store.getId());
				detailStoreJsonObject.setStoreImgUrl(storeImage.getImgUrl());
				
				WaitingList waitingList = waitingListService.findByWaitingListId(store.getId());
				detailStoreJsonObject.setCurrentInLine(waitingList.getCurrentInLine());
				
				detailStoreJsonObject.setStoreLatitude(store.getLatitude());
				detailStoreJsonObject.setStoreLongitude(store.getLongitude());
			}
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(detailStoreJsonObject);
	}

}
