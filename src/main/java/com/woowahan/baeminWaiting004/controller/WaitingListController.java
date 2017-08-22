package com.woowahan.baeminWaiting004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowahan.baeminWaiting004.model.WaitingListJsonObject;
import com.woowahan.baeminWaiting004.service.WaitingListService;

@Controller
public class WaitingListController {
	@Autowired
	private WaitingListService waitingListService;
	
	
	//처음 가게 등록하면 기본적으로 이게 돌 것 (param storeId)
	@RequestMapping(value = "/addWaitingList", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String addWaitingList(@RequestBody String waitingListJson) throws Exception{
		
		ObjectMapper objectMapper = new ObjectMapper();
		WaitingListJsonObject waitingListJsonObject = objectMapper.readValue(waitingListJson, WaitingListJsonObject.class);
		
		int storeId = waitingListJsonObject.getStoreId();
		waitingListService.addWaitingList(storeId);
		return "{\"is_success\": 1}";
	}

}
