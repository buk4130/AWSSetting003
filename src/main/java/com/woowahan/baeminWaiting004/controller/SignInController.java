package com.woowahan.baeminWaiting004.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowahan.baeminWaiting004.model.Member;
import com.woowahan.baeminWaiting004.model.SignInJsonObject;
import com.woowahan.baeminWaiting004.service.MemberService;

@Controller
public class SignInController {

	@Autowired
	private MemberService memberService;
	
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value="/signin", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getSignIn(@RequestBody String signInJson) 
			throws JsonParseException, JsonMappingException, IOException, JWTCreationException, UnsupportedEncodingException {
		ObjectMapper objectMapper = new ObjectMapper();
		SignInJsonObject signInJsonObject = objectMapper.readValue(signInJson, SignInJsonObject.class);
		String userId = signInJsonObject.getUserId();
		String userPassword = signInJsonObject.getUserPassword();
		
		Member member = memberService.findByUsername(userId);
		
		String token = "fail";
		if(userPassword == member.getPassword()) {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			token = JWT.create().withIssuer("auth0").sign(algorithm);
		}
		
		return token;
	}
}
