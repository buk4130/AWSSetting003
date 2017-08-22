package com.woowahan.baeminWaiting004.service;

import java.util.List;

import com.woowahan.baeminWaiting004.model.WaitingTicket;

public interface WaitingTicketService {
	void addWaitingTicket(String name, int waitingListId, String memberId, int headCount, int isStaying, String contactNumber);
	
	WaitingTicket findByTicketNumber(int ticketNumber);
	List<WaitingTicket> findByMemberId(String memberId);
	List<WaitingTicket> findByWaitingListId(int waitingListId);
	List<WaitingTicket> getAllWaitingTicket();
}
