package com.woowahan.baeminWaiting004.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woowahan.baeminWaiting004.model.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {
	
	Token findByTicketNumber(int ticketNumber);
}
