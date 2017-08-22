package com.woowahan.baeminWaiting004.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowahan.baeminWaiting004.model.NowLatLonJsonObject;
import com.woowahan.baeminWaiting004.model.Store;
import com.woowahan.baeminWaiting004.model.StoreImage;
import com.woowahan.baeminWaiting004.model.StoreJsonObject;
import com.woowahan.baeminWaiting004.model.WaitingList;
import com.woowahan.baeminWaiting004.service.StoreImageService;
import com.woowahan.baeminWaiting004.service.StoreService;
import com.woowahan.baeminWaiting004.service.WaitingListService;

@Controller
public class StoreDistanceFilterController {
	
	private static Logger logger = LoggerFactory.getLogger(StoreDistanceFilterController.class);
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private StoreImageService storeImageService;
	
	@Autowired
	private WaitingListService waitingListService;
	
	@RequestMapping(value="/storefilter", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getFilterStore(@RequestBody String nowLatLon) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		NowLatLonJsonObject nowLatLonJsonObject = objectMapper.readValue(nowLatLon, NowLatLonJsonObject.class);
		
		double nowLatitude = Double.parseDouble(nowLatLonJsonObject.getLatitude());
		double nowLongitude = Double.parseDouble(nowLatLonJsonObject.getLongitude());
		
		List<Store> stores = storeService.getAllStores();
		List<StoreJsonObject> storeJsonTypeList = new ArrayList<StoreJsonObject>();
		
		for (int i = 0; i < stores.size(); i++) {
			StoreJsonObject storeJsonType = new StoreJsonObject();
			Store store = stores.get(i);
			storeJsonType.setStoreLatitude(store.getLatitude());
			storeJsonType.setStoreLongitude(store.getLongitude());
			
			double storeLatitude = Double.parseDouble(storeJsonType.getStoreLatitude());
			double storeLongitude = Double.parseDouble(storeJsonType.getStoreLongitude());
			
			double filterDistance = 1.5;
			
			if(filterDistance > distance(nowLatitude, nowLongitude, storeLatitude, storeLongitude, "kilometer")) {
				storeJsonType.setStoreName(store.getTitle());
				storeJsonType.setStoreAddress(store.getAddress());
				storeJsonType.setStoreId(store.getId());
				storeJsonType.setStoreIsOpened(store.getOpened());
				
				StoreImage storeImage = storeImageService.findByStoreId(store.getId());
				storeJsonType.setStoreImgUrl(storeImage.getImgUrl());
				
				WaitingList waitingList = waitingListService.findByWaitingListId(store.getId());
				storeJsonType.setCurrentInLine(waitingList.getCurrentInLine());
				
				storeJsonTypeList.add(storeJsonType);
			}
		}
		
		return objectMapper.writeValueAsString(storeJsonTypeList);
	}
	
	 private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
         
	        double theta = lon1 - lon2;
	        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	         
	        dist = Math.acos(dist);
	        dist = rad2deg(dist);
	        dist = dist * 60 * 1.1515;
	         
	        if (unit == "kilometer") {
	            dist = dist * 1.609344;
	        } else if(unit == "meter"){
	            dist = dist * 1609.344;
	        }
	 
	        return (dist);
	    }
	     
	 
	    // This function converts decimal degrees to radians
	    private static double deg2rad(double deg) {
	        return (deg * Math.PI / 180.0);
	    }
	     
	    // This function converts radians to decimal degrees
	    private static double rad2deg(double rad) {
	        return (rad * 180 / Math.PI);
	    }
}
