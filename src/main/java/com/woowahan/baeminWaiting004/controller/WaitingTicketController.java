package com.woowahan.baeminWaiting004.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.action.internal.EntityIdentityInsertAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowahan.baeminWaiting004.model.CheckTicketJsonType;
import com.woowahan.baeminWaiting004.model.MylineJsonObject;
import com.woowahan.baeminWaiting004.model.Store;
import com.woowahan.baeminWaiting004.model.TicketVaildJsonObject;
import com.woowahan.baeminWaiting004.model.WaitingList;
import com.woowahan.baeminWaiting004.model.WaitingTicket;
import com.woowahan.baeminWaiting004.model.WaitingTicketJsonType;
import com.woowahan.baeminWaiting004.repository.WaitingListRepository;
import com.woowahan.baeminWaiting004.model.WaitingTicketJsonObject;
import com.woowahan.baeminWaiting004.model.WaitingTicketJsonType;
import com.woowahan.baeminWaiting004.service.StoreService;
import com.woowahan.baeminWaiting004.service.WaitingListService;
import com.woowahan.baeminWaiting004.service.WaitingTicketService;

@Controller
public class WaitingTicketController {
	@Autowired
	private WaitingTicketService waitingTicketService;
	
	@Autowired
	private WaitingListService waitingListService;
	
	@Autowired
	private StoreService storeService;

	
	//처음 대기표 받을때 기능(param: fk waitingListId, memberId 꼭 필요 ) 
	@RequestMapping(value="/addWaitingTicket", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String addWaitingTicket(@RequestBody String waitingTicketJson) throws Exception{
		
		ObjectMapper objectMapper = new ObjectMapper();
		WaitingTicketJsonObject waitingTicketJsonObject = objectMapper.readValue(waitingTicketJson, WaitingTicketJsonObject.class);
		
		System.out.println(waitingTicketJsonObject);
		String name = waitingTicketJsonObject.getName();
		int storeId = waitingTicketJsonObject.getStoreId();
		int headCount = waitingTicketJsonObject.getHeadCount();
		int isStaying = waitingTicketJsonObject.getIsStaying();
		String phoneNumber = waitingTicketJsonObject.getPhoneNumber();
		String memberId = "";
		
		Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String creatingTime = dayTime.format(calendar.getTime());
		
		waitingTicketService.addWaitingTicket(name, storeId, memberId, headCount, isStaying, phoneNumber, creatingTime);
		
		List<WaitingTicket> waitingTickets = waitingTicketService.findByWaitingListId(storeId);
		List<WaitingTicket> filteredTickets = new ArrayList<WaitingTicket>();
		for(int i=0; i<waitingTickets.size(); i++) {
			if(waitingTickets.get(i).getStatus() < 10) {filteredTickets.add(waitingTickets.get(i));}
		}
		
		WaitingList waitingList = waitingListService.findByWaitingListId(storeId);
		waitingList.setCurrentInLine(filteredTickets.size());
		waitingListService.updateWaitingList(waitingList);
		
		Store store = storeService.findByid(waitingList.getStoreId());
		WaitingTicket waitingTicket = waitingTicketService.findByCreateTime(creatingTime);
		CheckTicketJsonType checkTicketJsonType = new CheckTicketJsonType();
		checkTicketJsonType.setIsSuccess(1);
		checkTicketJsonType.setTicketNumber(waitingTicket.getTicketNumber());
		checkTicketJsonType.setCurrentInLine(waitingList.getCurrentInLine());
		checkTicketJsonType.setStoreName(store.getTitle());
		

		return objectMapper.writeValueAsString(checkTicketJsonType);
	}
	
	//가게에서 티켓 조회 받을때 기능(param: fk waitingListId 꼭 필요 ) 
	@RequestMapping(value="/waitingList", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getWaitingTicketByWaitingListId(@RequestBody String waitingTicketJson) throws Exception{
		
		ObjectMapper objectMapper = new ObjectMapper();
		WaitingTicketJsonObject waitingTicketJsonObject = objectMapper.readValue(waitingTicketJson, WaitingTicketJsonObject.class);
		
		//storeId == waitingListId
		int waitingListId = waitingTicketJsonObject.getStoreId();
		
		
		List<WaitingTicket> waitingTicketList = waitingTicketService.findByWaitingListId(waitingListId);
		
		//결과값 제이슨으로 바꾸
		List<WaitingTicketJsonType> waitingTicketJsonObjectList = new ArrayList<WaitingTicketJsonType>();
		for (WaitingTicket waitingTicket : waitingTicketList) {
			//유효한 티켓만 받아오
			if(waitingTicket.getStatus() < 10) {
				WaitingTicketJsonType waitingTicketJsonObjectTemp = new WaitingTicketJsonType();
				
				waitingTicketJsonObjectTemp.setTicketNumber(waitingTicket.getTicketNumber());
				waitingTicketJsonObjectTemp.setMemberId(waitingTicket.getMemberId());
				waitingTicketJsonObjectTemp.setHeadCount(waitingTicket.getHeadCount());
				waitingTicketJsonObjectTemp.setIsStaying(waitingTicket.getIsStaying());
				waitingTicketJsonObjectTemp.setContactNumber(waitingTicket.getContactNumber());
				waitingTicketJsonObjectTemp.setName(waitingTicket.getName());
				waitingTicketJsonObjectTemp.setWaitingListId(waitingTicket.getWaitingListId());
				waitingTicketJsonObjectTemp.setCreateTime(waitingTicket.getCreateTime());
				waitingTicketJsonObjectTemp.setStatus(waitingTicket.getStatus());
				
				waitingTicketJsonObjectList.add(waitingTicketJsonObjectTemp);
			}
		}

		return objectMapper.writeValueAsString(waitingTicketJsonObjectList);
		
	}
	
	//update ticket
	@RequestMapping(value="/deleteTicket", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String updateTicketByTicketNum(@RequestBody String waitingTicketJson) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		WaitingTicketJsonObject waitingTicketJsonObject = objectMapper.readValue(waitingTicketJson, WaitingTicketJsonObject.class);

		int ticketNum = waitingTicketJsonObject.getTicketNumber();
		String status = waitingTicketJsonObject.getStatus();
					
		WaitingTicket rWaitingTicket = waitingTicketService.findByTicketNumber(ticketNum);
		if(status.equals("in")) { // 정상적으로 고객 입장 
			rWaitingTicket.setStatus(10);
		}else if(status.equals("customerCancel")) { // 고객의 취소 
			rWaitingTicket.setStatus(11);
		}else if(status.equals("cancel")) {// 가게 없주의 취소 
			rWaitingTicket.setStatus(12);
		}
		waitingTicketService.updateTicketByTicketNum(rWaitingTicket);
		
		WaitingList waitingList = waitingListService.findByWaitingListId(rWaitingTicket.getWaitingListId());
		
		List<WaitingTicket> waitingTickets = waitingTicketService.findByWaitingListId(rWaitingTicket.getWaitingListId());
		List<WaitingTicket> filteredTickets = new ArrayList<WaitingTicket>();
		for(int i=0; i<waitingTickets.size(); i++) {
			if(waitingTickets.get(i).getStatus() < 10) {filteredTickets.add(waitingTickets.get(i));}
		}
		waitingList.setCurrentInLine(filteredTickets.size());
		waitingListService.updateWaitingList(waitingList);
		
		
		int isSuccess = 1;
			
		return objectMapper.writeValueAsString(isSuccess);
	}
	
	//티켓넘버로 디테일 가져오기 
	@RequestMapping(value="/waitingPerson", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getCustomerDetailByTicketNum(@RequestBody String waitingTicketJson) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		WaitingTicketJsonObject waitingTicketJsonObject = objectMapper.readValue(waitingTicketJson, WaitingTicketJsonObject.class);
		int ticketNum = waitingTicketJsonObject.getTicketNumber();
		
		WaitingTicket rWaitingTicket = waitingTicketService.findByTicketNumber(ticketNum);		
		
		return objectMapper.writeValueAsString(rWaitingTicket);
	}
	
		
	
	@RequestMapping(value="/validCheckTicket", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String checkTicketValid(@RequestBody String validTicketJson) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		TicketVaildJsonObject ticketVaildJsonObject = objectMapper.readValue(validTicketJson, TicketVaildJsonObject.class);
		
		WaitingTicket waitingTicket = waitingTicketService.findByTicketNumber(ticketVaildJsonObject.getTicketNum());
		int status = waitingTicket.getStatus();
		
		return objectMapper.writeValueAsString(status);
	}
	
	@RequestMapping(value="/mylineCheck", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String checkMyline(@RequestBody String mylineJson) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		MylineJsonObject mylineJsonObject = objectMapper.readValue(mylineJson, MylineJsonObject.class);
		
		int storeId = mylineJsonObject.getStoreId();
		int ticketNumber = mylineJsonObject.getTicketNumber();
		
		List<WaitingTicket> waitingTickets = waitingTicketService.findByWaitingListId(storeId);
		WaitingList waitingList = waitingListService.findByWaitingListId(storeId);
		waitingList.setCurrentInLine(waitingTickets.size());
		waitingListService.updateWaitingList(waitingList);
		
		List<WaitingTicket> filteredTickets = new ArrayList<WaitingTicket>();
		for(int i=0; i<waitingTickets.size(); i++) {
			if(waitingTickets.get(i).getStatus() < 10) {filteredTickets.add(waitingTickets.get(i));}
		}
		
		Store store = storeService.findByid(waitingList.getStoreId());
		WaitingTicket waitingTicket = waitingTicketService.findByTicketNumber(ticketNumber);
		CheckTicketJsonType checkTicketJsonType = new CheckTicketJsonType();
		checkTicketJsonType.setIsSuccess(1);
		checkTicketJsonType.setTicketNumber(waitingTicket.getTicketNumber());
		int index = 1;
		for(int i=0; i<filteredTickets.size(); i++) {
			if (filteredTickets.get(i).getTicketNumber() == waitingTicket.getTicketNumber()) {break;} 
			index++;
		}
		System.out.println(index);
		checkTicketJsonType.setCurrentInLine(index);
		checkTicketJsonType.setStoreName(store.getTitle());
		
		return objectMapper.writeValueAsString(checkTicketJsonType);
	}
}
