package com.woowahan.baeminWaiting004.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.woowahan.baeminWaiting004.model.AddTokenJsonObject;
import com.woowahan.baeminWaiting004.model.PushAlarmJsonObject;
import com.woowahan.baeminWaiting004.service.TokenService;


@Controller
public class PushAlamiOSController {

	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value="/addToken", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String addToken(@RequestBody String addToken) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		AddTokenJsonObject addTokenJsonObject= objectMapper.readValue(addToken, AddTokenJsonObject.class);
		
		int ticketNumber = addTokenJsonObject.getTicketNumber();
		String tokenNum = addTokenJsonObject.getToken();
		tokenService.addToken(ticketNumber, tokenNum);
		System.out.println(tokenNum);
		
		int isSuccess = 1;
		
		return objectMapper.writeValueAsString(isSuccess);
	}
	
	@RequestMapping(value="/push", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void pushAlarm(@RequestBody String postJson) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		PushAlarmJsonObject pushAlarmJsonObject = objectMapper.readValue(postJson, PushAlarmJsonObject.class);
		
		ApnsService apnsService = APNS.newService()
		.withCert("/Users/woowabrothers/Workspace/Techfile/baeminWaiting.p12", "waiting1234")
		.withSandboxDestination()
		.build();
		
		String payload = APNS.newPayload().alertBody(pushAlarmJsonObject.getPayload()).sound("default").build();
		String token = tokenService.findByTicketNumber(pushAlarmJsonObject.getTicketNum()).getToken();
		
		apnsService.push(token, payload);
	}
}
