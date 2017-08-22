package com.woowahan.baeminWaiting004.controller;

import java.io.IOException;

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
import com.woowahan.baeminWaiting004.model.PushAlarmJsonObject;


@Controller
public class PushAlamiOSController {

	@RequestMapping(value="/push", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void pushAlarm(@RequestBody String postJson) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("1111");
		PushAlarmJsonObject pushAlarmJsonObject = objectMapper.readValue(postJson, PushAlarmJsonObject.class);
		System.out.println(pushAlarmJsonObject.getPushAlarm());
		
		ApnsService apnsService = APNS.newService()
		.withCert("/Users/woowabrothers/Workspace/Techfile/wender.p12", "12345678")
		.withSandboxDestination()
		.build();
		
		System.out.println("{ \"aps\": { \"alert\": \"Breaking News!\", \"sound\": \"default\", \"link_url\": \"https://raywenderlich.com\"}");
		//String payload =APNS.newPayload().alertBody("breaking News!").build();
		String payload = APNS.newPayload().alertBody("breaking News!").sound("default").build();
		String token = "60647f0d700606e6a97c2648c9fc9f26c37268081fceea4f3d867968dad660b1";
		
		apnsService.push(token, payload);
	}
}
