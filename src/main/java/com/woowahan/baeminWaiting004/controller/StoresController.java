package com.woowahan.baeminWaiting004.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowahan.baeminWaiting004.model.Menu;
import com.woowahan.baeminWaiting004.model.Store;
import com.woowahan.baeminWaiting004.model.StoreIdJsonType;
import com.woowahan.baeminWaiting004.model.StoreImage;
import com.woowahan.baeminWaiting004.model.StoreInfoJsonObject;
import com.woowahan.baeminWaiting004.model.StoreJsonObject;
import com.woowahan.baeminWaiting004.model.StoreJsonType;
import com.woowahan.baeminWaiting004.model.WaitingList;
import com.woowahan.baeminWaiting004.service.MenuService;
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
	
	@Autowired
	private MenuService menuService;
	
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
	
	//jw
	@RequestMapping(value = "/store", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String addStore(@RequestBody String storeJson) throws Exception{
		
		ObjectMapper objectMapper = new ObjectMapper();
		StoreJsonType storeJsonType = objectMapper.readValue(storeJson, StoreJsonType.class);
		
		String storeName = storeJsonType.getStoreName();
		String storeAddress = storeJsonType.getStoreAddress();
		String storeTel = storeJsonType.getStoreTel();
		String storeDescription = storeJsonType.getStoreDesc();
		String storeLatitude = storeJsonType.getStoreLatitude();
		String storeLongitude = storeJsonType.getStoreLongitude();
		String memberId = storeJsonType.getMemberId();
		
		String storeIsOpened = "0";
		//String storeMenu1, storeMenu2, storeMenu3, storeMenu4, storeMenu5, storeMenu6, storeMenu7, storeMenu8, storeMenu9, storeMenu10 ="";
		
		Menu menu = new Menu();
		if(storeJsonType.getStoreMenu1() != null) {
			menu.setMenu1(storeJsonType.getStoreMenu1());
		}
		if(storeJsonType.getStoreMenu2() != null) {
			menu.setMenu2(storeJsonType.getStoreMenu2());
		}
		if(storeJsonType.getStoreMenu3() != null) {
			menu.setMenu3(storeJsonType.getStoreMenu3());
		}
		if(storeJsonType.getStoreMenu4() != null) {
			menu.setMenu4(storeJsonType.getStoreMenu4());
		}
		if(storeJsonType.getStoreMenu5() != null) {
			menu.setMenu5(storeJsonType.getStoreMenu5());
		}
		if(storeJsonType.getStoreMenu6() != null) {
			menu.setMenu6(storeJsonType.getStoreMenu6());
		}
		if(storeJsonType.getStoreMenu7() != null) {
			menu.setMenu7(storeJsonType.getStoreMenu7());
		}
		if(storeJsonType.getStoreMenu8() != null) {
			menu.setMenu8(storeJsonType.getStoreMenu8());
		}
		if(storeJsonType.getStoreMenu9() != null) {
			menu.setMenu9(storeJsonType.getStoreMenu9());
		}
		if(storeJsonType.getStoreMenu10() != null) {
			menu.setMenu10(storeJsonType.getStoreMenu10());
		}
		
		
		
		storeService.addStore2(storeName, storeTel, storeAddress, storeDescription, storeLatitude, storeLongitude, memberId);
		//System.out.println("done123");
		
		Store rStore = storeService.getStoreId(memberId);
		StoreIdJsonType storeIdJsonType = new StoreIdJsonType();
		int rStoreId = rStore.getId();
		
		menu.setStoreId(rStoreId);
		menuService.addMenu(menu);
		
		storeIdJsonType.setStoreId(rStoreId);
		return objectMapper.writeValueAsString(storeIdJsonType);
	}
	
	//get one store info
	@RequestMapping(value = "/storeInfo", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getStoreInfo(@RequestBody String storeJson) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		StoreJsonType storeJsonType = objectMapper.readValue(storeJson, StoreJsonType.class);
		
		String memberId = storeJsonType.getMemberId();
		Store rStore = storeService.getStoreId(memberId);
		Menu rMenu = menuService.findByStoreId(rStore.getId());
		
		StoreInfoJsonObject storeInfoJsonObject = new StoreInfoJsonObject();
		storeInfoJsonObject.setTitle(rStore.getTitle());
		storeInfoJsonObject.setDesc(rStore.getDescription());
		storeInfoJsonObject.setAddr(rStore.getAddress());
		storeInfoJsonObject.setLatitude(rStore.getLatitude());
		storeInfoJsonObject.setLongitude(rStore.getLongitude());
		storeInfoJsonObject.setTel(rStore.getTel());
		if(rMenu.getMenu1()!=null) {			
			storeInfoJsonObject.setMenu1(rMenu.getMenu1());
		}
		if(rMenu.getMenu2()!=null) {			
			storeInfoJsonObject.setMenu2(rMenu.getMenu2());
		}
		if(rMenu.getMenu3()!=null) {			
			storeInfoJsonObject.setMenu3(rMenu.getMenu3());
		}
		if(rMenu.getMenu4()!=null) {			
			storeInfoJsonObject.setMenu4(rMenu.getMenu4());
		}
		if(rMenu.getMenu5()!=null) {			
			storeInfoJsonObject.setMenu5(rMenu.getMenu5());
		}
		if(rMenu.getMenu6()!=null) {			
			storeInfoJsonObject.setMenu6(rMenu.getMenu6());
		}
		if(rMenu.getMenu7()!=null) {			
			storeInfoJsonObject.setMenu7(rMenu.getMenu7());
		}
		if(rMenu.getMenu8()!=null) {			
			storeInfoJsonObject.setMenu8(rMenu.getMenu8());
		}
		if(rMenu.getMenu9()!=null) {			
			storeInfoJsonObject.setMenu9(rMenu.getMenu9());
		}
		if(rMenu.getMenu10()!=null) {			
			storeInfoJsonObject.setMenu10(rMenu.getMenu10());
		}
		
		
		return objectMapper.writeValueAsString(storeInfoJsonObject);
	}
	
	//jw
	@RequestMapping(value = "/updateStore", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String updateStore(@RequestBody String storeJson) throws Exception{
			
			ObjectMapper objectMapper = new ObjectMapper();
			StoreJsonType storeJsonType = objectMapper.readValue(storeJson, StoreJsonType.class);
			
			int storeId = storeJsonType.getStoreId();
			String storeName = storeJsonType.getStoreName();
			String storeAddress = storeJsonType.getStoreAddress();
			String storeTel = storeJsonType.getStoreTel();
			String storeDescription = storeJsonType.getStoreDesc();
			String storeLatitude = storeJsonType.getStoreLatitude();
			String storeLongitude = storeJsonType.getStoreLongitude();
			String memberId = storeJsonType.getMemberId();
			
			String storeIsOpened = "0";
			//String storeMenu1, storeMenu2, storeMenu3, storeMenu4, storeMenu5, storeMenu6, storeMenu7, storeMenu8, storeMenu9, storeMenu10 ="";
			
			Menu menu = new Menu();
			if(storeJsonType.getStoreMenu1() != null) {
				menu.setMenu1(storeJsonType.getStoreMenu1());
			}
			if(storeJsonType.getStoreMenu2() != null) {
				menu.setMenu2(storeJsonType.getStoreMenu2());
			}
			if(storeJsonType.getStoreMenu3() != null) {
				menu.setMenu3(storeJsonType.getStoreMenu3());
			}
			if(storeJsonType.getStoreMenu4() != null) {
				menu.setMenu4(storeJsonType.getStoreMenu4());
			}
			if(storeJsonType.getStoreMenu5() != null) {
				menu.setMenu5(storeJsonType.getStoreMenu5());
			}
			if(storeJsonType.getStoreMenu6() != null) {
				menu.setMenu6(storeJsonType.getStoreMenu6());
			}
			if(storeJsonType.getStoreMenu7() != null) {
				menu.setMenu7(storeJsonType.getStoreMenu7());
			}
			if(storeJsonType.getStoreMenu8() != null) {
				menu.setMenu8(storeJsonType.getStoreMenu8());
			}
			if(storeJsonType.getStoreMenu9() != null) {
				menu.setMenu9(storeJsonType.getStoreMenu9());
			}
			if(storeJsonType.getStoreMenu10() != null) {
				menu.setMenu10(storeJsonType.getStoreMenu10());
			}
			
			
			
			storeService.addStore3(storeName, storeTel, storeAddress, storeDescription, storeLatitude, storeLongitude, memberId, storeId);
			//System.out.println("done123");
			
			Store rStore = storeService.getStoreId(memberId);
			StoreIdJsonType storeIdJsonType = new StoreIdJsonType();
			int rStoreId = rStore.getId();
			
			menu.setStoreId(rStoreId);
			menuService.addMenu(menu);
			
			storeIdJsonType.setStoreId(rStoreId);
			return objectMapper.writeValueAsString(storeIdJsonType);
	}
	
	//onoff store
	@RequestMapping(value = "/onoff", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String turnOnOffStore(@RequestBody String storeJson) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		StoreJsonType storeJsonType = objectMapper.readValue(storeJson, StoreJsonType.class);
		
		String memberId = storeJsonType.getMemberId();
		Store rStore = storeService.getStoreId(memberId);
		int isOpen = rStore.getOpened();
		
		if(isOpen == 1) { //열렸으면 
			rStore.setOpened(0);
		}else if(isOpen == 0) {//닫혔으면 
			rStore.setOpened(1);
		}
		
		storeService.addStore3(rStore.getTitle(), rStore.getTel(), rStore.getAddress(), rStore.getDescription(), rStore.getLatitude(), rStore.getLongitude(), memberId, rStore.getId());
		
		return null;
	}
	
}
