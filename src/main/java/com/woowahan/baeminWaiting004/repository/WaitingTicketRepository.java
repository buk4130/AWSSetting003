package com.woowahan.baeminWaiting004.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.woowahan.baeminWaiting004.model.WaitingTicket;

public interface WaitingTicketRepository extends JpaRepository<WaitingTicket, Integer>{
	WaitingTicket findByTicketNumber(int ticketNumber);
	List<WaitingTicket> findByMemberId(String memberId);
	List<WaitingTicket> findByWaitingListId(int waitingListId);
	List<WaitingTicket> findByWaitingListIdAndStatus(int waitingListId, int status);
	WaitingTicket findByCreateTime(String creatTime);
}
