package com.woowahan.baeminWaiting004.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowahan.baeminWaiting004.model.WaitingList;
import com.woowahan.baeminWaiting004.model.WaitingTicket;
import com.woowahan.baeminWaiting004.model.WaitingTicketJsonObject;
import com.woowahan.baeminWaiting004.model.WaitingTicketJsonType;
import com.woowahan.baeminWaiting004.service.WaitingListService;
import com.woowahan.baeminWaiting004.service.WaitingTicketService;

@Controller
public class WaitingTicketController {
	@Autowired
	private WaitingTicketService waitingTicketService;
	
	@Autowired
	private WaitingListService waitingListService;
	
	//처음 대기표 받을때 기능(param: fk waitingListId, memberId 꼭 필요 ) 
	@RequestMapping(value="/addWaitingTicket", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String addWaitingTicket(@RequestBody String waitingTicketJson) throws Exception{
		
		ObjectMapper objectMapper = new ObjectMapper();
		WaitingTicketJsonObject waitingTicketJsonObject = objectMapper.readValue(waitingTicketJson, WaitingTicketJsonObject.class);
		
		String name = waitingTicketJsonObject.getName();
		int storeId = waitingTicketJsonObject.getStoreId();
		int headCount = waitingTicketJsonObject.getHeadCount();
		int isStaying = waitingTicketJsonObject.getIsStaying();
		String phoneNumber = waitingTicketJsonObject.getPhoneNumber();
		String memberId = "";
		waitingTicketService.addWaitingTicket(name, storeId, memberId, headCount, isStaying, phoneNumber);
		
		List<WaitingTicket> waitingTickets = waitingTicketService.findByWaitingListId(storeId);
		WaitingList waitingList = waitingListService.findByWaitingListId(storeId);
		System.out.println(waitingList.getCurrentInLine());
		
		waitingList.setCurrentInLine(waitingTickets.size());
		System.out.println(waitingList.getCurrentInLine());
		
		return "{\"is_success\": 1}";
	}
	
	//가게에서 티켓 조회 받을때 기능(param: fk waitingListId 꼭 필요 ) 
	@RequestMapping(value="/getWaitingTicketByWaitingListId", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getWaitingTicketByWaitingListId(@RequestBody String waitingTicketJson) throws Exception{
		
		ObjectMapper objectMapper = new ObjectMapper();
		WaitingTicketJsonObject waitingTicketJsonObject = objectMapper.readValue(waitingTicketJson, WaitingTicketJsonObject.class);
		
		//storeId == waitingListId
		int waitingListId = waitingTicketJsonObject.getStoreId();
		int deleted = 0;//유효한 티켓만 받아오
		
		List<WaitingTicket> waitingTicketList = waitingTicketService.findByWaitingListIdAndDeleted(waitingListId, deleted);
		
		//결과값 제이슨으로 바꾸
		List<WaitingTicketJsonType> waitingTicketJsonObjectList = new ArrayList<WaitingTicketJsonType>();
		for (WaitingTicket waitingTicket : waitingTicketList) {
			WaitingTicketJsonType waitingTicketJsonObjectTemp = new WaitingTicketJsonType();
			
			waitingTicketJsonObjectTemp.setTicketNumber(waitingTicket.getTicketNumber());
			waitingTicketJsonObjectTemp.setMemberId(waitingTicket.getMemberId());
			waitingTicketJsonObjectTemp.setHeadCount(waitingTicket.getHeadCount());
			waitingTicketJsonObjectTemp.setIsStaying(waitingTicket.getIsStaying());
			waitingTicketJsonObjectTemp.setContactNumber(waitingTicket.getContactNumber());
			waitingTicketJsonObjectTemp.setName(waitingTicket.getName());
			waitingTicketJsonObjectTemp.setWaitingListId(waitingTicket.getWaitingListId());
			waitingTicketJsonObjectTemp.setCreateTime(waitingTicket.getCreateTime());
			
			waitingTicketJsonObjectList.add(waitingTicketJsonObjectTemp);
		}

		return objectMapper.writeValueAsString(waitingTicketJsonObjectList);
		
	}
	
//	@RequestMapping(value="/findByTicketNumber", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
//	@ResponseBody
//	public String findByTicketNumber(@RequestBody String waitingTicketJson) throws Exception{
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		WaitingTicketJsonObject waitingTicketJsonObject = objectMapper.readValue(waitingTicketJson, WaitingTicketJsonObject.class);
//		
//		int ticketNumber = waitingTicketJsonObject.getTicketNumber();
//		WaitingTicket waitingTicket = waitingTicketService.findByTicketNumber(ticketNumber);
//		
//		waitingTicketJsonObject.setWaitingListId(waitingTicket.getWaitingListId());
//		waitingTicketJsonObject.setMemberId(waitingTicket.getMemberId());
//		waitingTicketJsonObject.setHeadCount(waitingTicket.getHeadCount());
//		waitingTicketJsonObject.setIsStaying(waitingTicket.getIsStaying());
//		waitingTicketJsonObject.setContactNumber(waitingTicket.getContactNumber());
//		
//		return objectMapper.writeValueAsString(waitingTicketJsonObject);
//	}
//	
//	@RequestMapping(value="/findByMemberId", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
//	@ResponseBody
//	public String findByMemberId(@RequestBody String waitingTicketJson) throws Exception{
//		//파라미터 받기 
//		ObjectMapper objectMapper = new ObjectMapper();
//		WaitingTicketJsonObject waitingTicketJsonObject = objectMapper.readValue(waitingTicketJson, WaitingTicketJsonObject.class);		
//		String memberId = waitingTicketJsonObject.getMemberId();
//		//디비 갔다오기 
//		List<WaitingTicket> waitingTicketList = waitingTicketService.findByMemberId(memberId);
//		
//		//결과값 제이슨으로 바꾸
//		List<WaitingTicketJsonObject> waitingTicketJsonObjectList = new ArrayList<WaitingTicketJsonObject>();
//		for(WaitingTicket waitingTicket : waitingTicketList) {
//			WaitingTicketJsonObject waitingTicketJsonObjectTemp = new WaitingTicketJsonObject();
//			waitingTicketJsonObjectTemp.setTicketNumber(waitingTicket.getTicketNumber());
//			waitingTicketJsonObjectTemp.setMemberId(waitingTicket.getMemberId());
//			waitingTicketJsonObjectTemp.setHeadCount(waitingTicket.getHeadCount());
//			waitingTicketJsonObjectTemp.setIsStaying(waitingTicket.getIsStaying());
//			waitingTicketJsonObjectTemp.setContactNumber(waitingTicket.getContactNumber());
//			waitingTicketJsonObjectTemp.setMemberId(waitingTicket.getMemberId());
//			waitingTicketJsonObjectTemp.setWaitingListId(waitingTicket.getWaitingListId());
//			waitingTicketJsonObjectList.add(waitingTicketJsonObjectTemp);
//		}
//		
//		return objectMapper.writeValueAsString(waitingTicketJsonObjectList);
//	}
//	
//	@RequestMapping(value="/findByWaitingListId", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
//	@ResponseBody
//	public String findByWaitingListId(@RequestBody String waitingTicketJson) throws Exception{
//		//파라미터 받기 
//		ObjectMapper objectMapper = new ObjectMapper();
//		WaitingTicketJsonObject waitingTicketJsonObject = objectMapper.readValue(waitingTicketJson,
//				WaitingTicketJsonObject.class);
//		int waitingListId= waitingTicketJsonObject.getWaitingListId();
//		// 디비 갔다오기
//		List<WaitingTicket> waitingTicketList = waitingTicketService.findByWaitingListId(waitingListId);
//
//		// 결과값 제이슨으로 바꾸
//		List<WaitingTicketJsonObject> waitingTicketJsonObjectList = new ArrayList<WaitingTicketJsonObject>();
//		for (WaitingTicket waitingTicket : waitingTicketList) {
//			WaitingTicketJsonObject waitingTicketJsonObjectTemp = new WaitingTicketJsonObject();
//			waitingTicketJsonObjectTemp.setTicketNumber(waitingTicket.getTicketNumber());
//			waitingTicketJsonObjectTemp.setMemberId(waitingTicket.getMemberId());
//			waitingTicketJsonObjectTemp.setHeadCount(waitingTicket.getHeadCount());
//			waitingTicketJsonObjectTemp.setIsStaying(waitingTicket.getIsStaying());
//			waitingTicketJsonObjectTemp.setContactNumber(waitingTicket.getContactNumber());
//			waitingTicketJsonObjectTemp.setMemberId(waitingTicket.getMemberId());
//			waitingTicketJsonObjectTemp.setWaitingListId(waitingTicket.getWaitingListId());
//			waitingTicketJsonObjectList.add(waitingTicketJsonObjectTemp);
//		}
//
//		return objectMapper.writeValueAsString(waitingTicketJsonObjectList);
//	}
//	
//	@RequestMapping(value="/getAllWaitingTicket", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
//	@ResponseBody
//	public String getAllWaitingTicket(@RequestBody String waitingTicketJson) throws Exception{
//	//to do		
//		return null;
//	}
	
}