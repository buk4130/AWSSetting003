package com.woowahan.baeminWaiting004.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.woowahan.baeminWaiting004.model.CommonParamObject;
import com.woowahan.baeminWaiting004.model.InnerMenu;
import com.woowahan.baeminWaiting004.model.Member;
import com.woowahan.baeminWaiting004.model.Menu;
import com.woowahan.baeminWaiting004.model.ResultJsonObject;
import com.woowahan.baeminWaiting004.model.Store;
import com.woowahan.baeminWaiting004.model.StoreIdJsonType;
import com.woowahan.baeminWaiting004.model.StoreImage;
import com.woowahan.baeminWaiting004.model.StoreInfoJsonObject;
import com.woowahan.baeminWaiting004.model.StoreJsonObject;
import com.woowahan.baeminWaiting004.model.StoreJsonType;
import com.woowahan.baeminWaiting004.model.WebTokenJsonObject;
import com.woowahan.baeminWaiting004.model.WaitingList;
import com.woowahan.baeminWaiting004.model.WaitingTicket;
import com.woowahan.baeminWaiting004.service.MemberService;
import com.woowahan.baeminWaiting004.service.MenuService;
import com.woowahan.baeminWaiting004.service.StoreImageService;
import com.woowahan.baeminWaiting004.service.StoreService;
import com.woowahan.baeminWaiting004.service.TokenService;
import com.woowahan.baeminWaiting004.service.WaitingListService;
import com.woowahan.baeminWaiting004.service.WaitingTicketService;

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
	
	@Autowired
	private WaitingTicketService waitingTicketService;
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TokenService tokenService;
	
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
	
	//web 둘러보기용 
	@RequestMapping(value = "/otherStores", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getAllOtherStores(@RequestBody String json) throws Exception{
		
		ObjectMapper objectMapper = new ObjectMapper();
		CommonParamObject param = objectMapper.readValue(json, CommonParamObject.class);
		
		List<Store> stores = storeService.findByStoreIdBetween(param.getFirstNum(), param.getLastNum());
		List<StoreJsonObject> storeJsonTypeList = new ArrayList<StoreJsonObject>();
		
		for (int i = 0; i < stores.size(); i++) {
			StoreJsonObject storeJsonObject = new StoreJsonObject();
			Store store = stores.get(i);
			storeJsonObject.setStoreAddress(store.getAddress());
			storeJsonObject.setStoreId(store.getId());

			storeJsonObject.setStoreName(store.getTitle());
			
			StoreImage storeImage = storeImageService.findByStoreId(store.getId());
			storeJsonObject.setStoreImgUrl(storeImage.getImgUrl());
			
//			WaitingList waitingList = waitingListService.findByWaitingListId(store.getId());
//			storeJsonObject.setCurrentInLine(waitingList.getCurrentInLine());
			
			storeJsonTypeList.add(storeJsonObject);
		}
		

		return objectMapper.writeValueAsString(storeJsonTypeList);
	}
	
	// 
	@RequestMapping(value = "/countStores", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String countStores() throws Exception{
		long count = storeService.countStores();
		ResultJsonObject result = new ResultJsonObject();
		result.setCount(count);
		
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(result);
	}
	
	
	//가게 등록 
	//jw 0828
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
		String imgUrl = storeJsonType.getStoreImgUrl();
		ArrayList<InnerMenu> menus = storeJsonType.getMenus();
		
		String storeIsOpened = "0";

		StoreIdJsonType storeIdJsonType = new StoreIdJsonType();
		int resultId = 0;
		
		int storeId = storeJsonType.getStoreId();
		if(storeId == 0) {//처음 가게 등록  
			//storeTB save
			storeService.firstAddStore(storeName, storeTel, storeAddress, storeDescription, storeLatitude, storeLongitude, memberId);
			
			//get storeId
			Store rStore = storeService.getStoreInfoByMemberId(memberId);
			
			int rStoreId = rStore.getId();
			
			//imgTB save by storeId
			StoreImage storeImage = new StoreImage();
			
			//jw 0828
			if(imgUrl == null) {
				System.out.println("null 이네 ");
				imgUrl = "https://dl.dropboxusercontent.com/s/q4onwflw5q7fksk/default.png";
			}
			
			storeImage.setImgUrl(imgUrl);
			storeImage.setStoreId(rStoreId);
			storeImageService.addImg(storeImage);
			
			//menuTB save by storeId
			ArrayList<Menu> pMenus = new ArrayList<Menu>();
			for(InnerMenu i : menus) {
				Menu tempMenu = new Menu();
				tempMenu.setName(i.getName());
				tempMenu.setPrice(i.getPrice());
				tempMenu.setStoreId(rStoreId);
				pMenus.add(tempMenu);
			}
			menuService.addMenu(pMenus, rStoreId);
			
			//waitingList create
			waitingListService.addWaitingList(rStoreId);
			resultId = rStoreId;
		} else if (storeId != 0) {//수정이라
			//storeInfo 
			Store rStore = storeService.getStoreInfoByMemberId(memberId);
			//img info
			StoreImage rStoreImg = storeImageService.findByStoreId(rStore.getId());
			//menu info 
			//ArrayList<Menu> rMenu = menuService.findByStoreId(rStore.getId());
			
			//rStore update
			rStore.setTitle(storeName);
			rStore.setAddress(storeAddress);
			rStore.setTel(storeTel);
			rStore.setDescription(storeDescription);
			
			if(storeLatitude != null && storeLongitude != null) {
				rStore.setLatitude(storeLatitude);
				rStore.setLongitude(storeLongitude);
			}
			storeService.updateStore(rStore.getTitle(), rStore.getTel(), rStore.getAddress(), rStore.getDescription(), rStore.getLatitude(), rStore.getLongitude(), memberId, storeId, rStore.getOpened());
			
			if(imgUrl != null) {
				rStoreImg.setImgUrl(imgUrl);			
			}
			storeImageService.updateImg(rStoreImg);
			
			
			System.out.println("이거 해얗");
			//remove all menu and save all again
			//menuService.removeMenuByStoreId(storeId);
			ArrayList<Menu> rMenu = menuService.findByStoreId(storeId);
			for(Menu m : rMenu) {
				menuService.removeMenuOneByOne(m.getMenuId());
			}
			
			System.out.println("dont be shy");
			//menuTB save by storeId
			ArrayList<Menu> pMenus = new ArrayList<Menu>();
			for(InnerMenu i : menus) {
				Menu tempMenu = new Menu();
				tempMenu.setName(i.getName());
				tempMenu.setPrice(i.getPrice());
				tempMenu.setStoreId(storeId);
				pMenus.add(tempMenu);
			}
			menuService.addMenu(pMenus, storeId);
			resultId = rStore.getId();
			
		}
		
		
		
		//return storeId
		storeIdJsonType.setStoreId(resultId);
		return objectMapper.writeValueAsString(storeIdJsonType);
	}
	
	//get my store info
	@RequestMapping(value = "/storeInfo", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getStoreInfo(@RequestBody String tokenJson) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		WebTokenJsonObject param = objectMapper.readValue(tokenJson, WebTokenJsonObject.class);
		System.out.println(param);
		//id 꺼내기 
		String memberId = param.getToken().getMemberId();
		//storeInfo 
		Store rStore = storeService.getStoreInfoByMemberId(memberId);
		//img info
		StoreImage rStoreImg = storeImageService.findByStoreId(rStore.getId());
		//menu info 
		ArrayList<Menu> rMenu = menuService.findByStoreId(rStore.getId());
		//member info
		Member rMember = memberService.findByMemberId(memberId);
		
		//making rvo
		StoreInfoJsonObject storeInfoJsonObject = new StoreInfoJsonObject();
		storeInfoJsonObject.setTitle(rStore.getTitle());
		storeInfoJsonObject.setDesc(rStore.getDescription());
		storeInfoJsonObject.setAddr(rStore.getAddress());
		storeInfoJsonObject.setLatitude(rStore.getLatitude());
		storeInfoJsonObject.setLongitude(rStore.getLongitude());
		storeInfoJsonObject.setTel(rStore.getTel());
		
		storeInfoJsonObject.setMenus(rMenu);
		storeInfoJsonObject.setImgUrl(rStoreImg.getImgUrl());
		
		storeInfoJsonObject.setMemberId(memberId);
		storeInfoJsonObject.setMemberName(rMember.getName());
		storeInfoJsonObject.setMemberTel(rMember.getTel());
		storeInfoJsonObject.setOpened(rStore.getOpened());
		storeInfoJsonObject.setStoreId(rStore.getId());
		
		return objectMapper.writeValueAsString(storeInfoJsonObject);
	}
	
	@RequestMapping(value = "/otherStoreDetail", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getOtherStoreDetail(@RequestBody String tokenJson) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		CommonParamObject param = objectMapper.readValue(tokenJson, CommonParamObject.class);
		
		//id 꺼내기 
		int storeId = param.getStoreId();
		//store info
		Store rStore = storeService.findByid(storeId);
		//img info
		StoreImage rStoreImg = storeImageService.findByStoreId(storeId);
		//menu info 
		ArrayList<Menu> rMenu = menuService.findByStoreId(storeId);

		
		//making rvo
		StoreInfoJsonObject storeInfoJsonObject = new StoreInfoJsonObject();
		storeInfoJsonObject.setTitle(rStore.getTitle());
		storeInfoJsonObject.setDesc(rStore.getDescription());
		storeInfoJsonObject.setAddr(rStore.getAddress());
		storeInfoJsonObject.setLatitude(rStore.getLatitude());
		storeInfoJsonObject.setLongitude(rStore.getLongitude());
		storeInfoJsonObject.setTel(rStore.getTel());
		
		storeInfoJsonObject.setMenus(rMenu);
		storeInfoJsonObject.setImgUrl(rStoreImg.getImgUrl());
	
		
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
		String imgUrl = storeJsonType.getStoreImgUrl();
		ArrayList<InnerMenu> menus = storeJsonType.getMenus();
		
		//storeInfo 
		Store rStore = storeService.getStoreInfoByMemberId(memberId);
		//img info
		StoreImage rStoreImg = storeImageService.findByStoreId(rStore.getId());
		//menu info 
		//ArrayList<Menu> rMenu = menuService.findByStoreId(rStore.getId());
		
		//rStore update
		rStore.setTitle(storeName);
		rStore.setAddress(storeAddress);
		rStore.setTel(storeTel);
		rStore.setDescription(storeDescription);
		rStore.setLatitude(storeLatitude);
		rStore.setLongitude(storeLongitude);
		
		if(imgUrl != null) {
			rStoreImg.setImgUrl(imgUrl);			
		}
		
		//remove all menu and save all again
		menuService.removeMenuByStoreId(storeId);
		//menuTB save by storeId
		ArrayList<Menu> pMenus = new ArrayList<Menu>();
		for(InnerMenu i : menus) {
			Menu tempMenu = new Menu();
			tempMenu.setName(i.getName());
			tempMenu.setPrice(i.getPrice());
			tempMenu.setStoreId(storeId);
			pMenus.add(tempMenu);
		}
		menuService.addMenu(pMenus, storeId);
		
		StoreIdJsonType storeIdJsonType = new StoreIdJsonType();
		storeIdJsonType.setStoreId(storeId);
		return objectMapper.writeValueAsString(storeIdJsonType);
	}
	
	//onoff store
	@RequestMapping(value = "/status", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String changeStoreStatus(@RequestBody String storeJson) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		WebTokenJsonObject param = objectMapper.readValue(storeJson, WebTokenJsonObject.class);
				
		String memberId = param.getToken().getMemberId();
		String status = param.getStatus();
		//store info get
		Store rStore = storeService.getStoreInfoByMemberId(memberId);
		

		if(rStore != null) {
			if(status.equals("on")) { //가게 오픈 대기 가능  
				rStore.setOpened(1);
			}else if(status.equals("off")) {//가게 오프, 대기줄 삭제  
				rStore.setOpened(0);
				List<WaitingTicket> waitingTicketList = waitingTicketService.findByWaitingListId(rStore.getId());
				if(!waitingTicketList.isEmpty()) {
					for(WaitingTicket w : waitingTicketList) {
						//수정필요 
						
						ApnsService apnsService = APNS.newService()
								 //.withCert("/home/ubuntu/aps/baeminWaiting.p12", "waiting1234")
								.withCert("/Users/woowabrothers/Workspace/Techfile/baeminWaiting.p12", "waiting1234")
								.withSandboxDestination()
								.build();
								
								String payload = APNS.newPayload().alertBody("죄송합니다. 식당의 사정으로 인하여 티켓을 취소하겠습니다. ").sound("default").build();
								
						if(w.getStatus() != 4 && w.getStatus() < 10) {
								String token = tokenService.findByTicketNumber(w.getTicketNumber()).getToken();
								
								apnsService.push(token, payload);
						}
						w.setStatus(12);
						waitingTicketService.updateTicketByTicketNum(w);
						WaitingList waitingList = waitingListService.findByWaitingListId(rStore.getId());
						waitingList.setCurrentInLine(0);
						waitingListService.updateWaitingList(waitingList);
					}
				}
			}else if(status.equals("deny")) {// 가게 오픈 but 대기 신청 불가능, 기존 대기줄 유효
				rStore.setOpened(2);
			}
		storeService.updateStore(rStore.getTitle(), rStore.getTel(), rStore.getAddress(), rStore.getDescription(), rStore.getLatitude(), rStore.getLongitude(), memberId, rStore.getId(), rStore.getOpened());
		}

		ResultJsonObject result =  new ResultJsonObject();
		result.setResultStatus(status);
		return objectMapper.writeValueAsString(result);
	}
	
	//로그아웃할때 턴 off 로해주는
//	@RequestMapping(value="/signout", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
//	@ResponseBody
//	public String logoutTurnOff(@RequestBody String storeJson, HttpServletRequest request) throws Exception {
//		ObjectMapper objectMapper = new ObjectMapper();
//		StoreJsonType storeJsonType = objectMapper.readValue(storeJson, StoreJsonType.class);
//		
//		String memberId = storeJsonType.getMemberId();
//		Store rStore = storeService.getStoreInfoByMemberId(memberId);
//		int isOpen = rStore.getOpened();
//		
//		if(isOpen == 1) { //열렸으면 
//			rStore.setOpened(0);
//		}else if(isOpen == 0) {//닫혔으면 
//			rStore.setOpened(0);
//		}
//		
//		storeService.addStore3(rStore.getTitle(), rStore.getTel(), rStore.getAddress(), rStore.getDescription(), rStore.getLatitude(), rStore.getLongitude(), memberId, rStore.getId());
//		String result = String.valueOf(rStore.getOpened());
//		return result;
//	}
	
	
	//json object receive test
	@RequestMapping(value="/test", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String test(@RequestBody String testJson, HttpServletRequest request) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		WebTokenJsonObject param = objectMapper.readValue(testJson, WebTokenJsonObject.class);
		
		System.out.println(param);
		int storeId = param.getToken().getStoreId();
		ArrayList arr = param.getMenus();
		
		System.out.println(storeId + "   !!!   " + param.getMenus().get(1).getName());
		
		
		return null;
	}
	
	
	
}
