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
import com.woowahan.baeminWaiting004.model.SignUpJsonObject;
import com.woowahan.baeminWaiting004.service.MemberService;

@Controller
public class SignUpController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/signup", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getSignUp(@RequestBody String signUpJson) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		SignUpJsonObject signUpJsonObject = objectMapper.readValue(signUpJson, SignUpJsonObject.class);
		String userId = signUpJsonObject.getUserId();
		String userPassword = signUpJsonObject.getUserPassword();
		String userMemberTel = signUpJsonObject.getUserMemberTel();
		int userRole = signUpJsonObject.getUserMemberRole();
		memberService.addMember(userId, userPassword, userMemberTel, userRole);
		
		return "{\"is_success\": 1}";
	}
}
