package com.woowahan.baeminWaiting004.service;

import com.woowahan.baeminWaiting004.model.Token;

public interface TokenService {
	Token findByTicketNumber(int ticketNumber);
	void addToken(int ticketNumber, String tokenNum);
}
