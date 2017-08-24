package com.woowahan.baeminWaiting004.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowahan.baeminWaiting004.model.Token;
import com.woowahan.baeminWaiting004.repository.TokenRepository;

@Service
public class TokenServiceImpl implements TokenService{
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public Token findByTicketNumber(int ticketNumber) {
		return tokenRepository.findByTicketNumber(ticketNumber);
	}

	@Override
	public void addToken(int ticketNumber, String tokenNum) {
		Token token = new Token();
		token.setTicketNumber(ticketNumber);
		token.setToken(tokenNum);
		tokenRepository.save(token);
	}
}
