package com.woowahan.baeminWaiting004.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowahan.baeminWaiting004.model.WaitingTicket;
import com.woowahan.baeminWaiting004.repository.WaitingTicketRepository;

@Service
public class WaitingTicketServiceImpl implements WaitingTicketService {
	@Autowired 
	private WaitingTicketRepository waitingTicketRepository;

	@Override
	public void addWaitingTicket(String name, int waitingListId, String memberId, int headCount, int isStaying,
			String contactNumber, String creatingTime) {
		WaitingTicket waitingTicket = new WaitingTicket();
		
		waitingTicket.setName(name);
		waitingTicket.setWaitingListId(waitingListId);
		waitingTicket.setMemberId(memberId);
		waitingTicket.setHeadCount(headCount);
		waitingTicket.setIsStaying(isStaying);
		waitingTicket.setContactNumber(contactNumber);
		waitingTicket.setCreateTime(creatingTime);
		waitingTicketRepository.save(waitingTicket);
	}

	@Override
	public WaitingTicket findByTicketNumber(int ticketNumber) {
		WaitingTicket waitingTicket = waitingTicketRepository.findByTicketNumber(ticketNumber);
		return waitingTicket;
	}

	@Override
	public List<WaitingTicket> findByMemberId(String memberId) {
		return waitingTicketRepository.findByMemberId(memberId);
	}

	@Override
	public List<WaitingTicket> findByWaitingListId(int waitingListId) {
		return waitingTicketRepository.findByWaitingListId(waitingListId);
	}
	
	@Override
	public List<WaitingTicket> findByWaitingListIdAndDeleted(int waitingListId, int deleted) {
		return waitingTicketRepository.findByWaitingListIdAndDeleted(waitingListId, deleted);
	}

	@Override
	public List<WaitingTicket> getAllWaitingTicket() {
		return waitingTicketRepository.findAll();
	}

	@Override
	public WaitingTicket findByCreateTime(String creatTime) {
		return waitingTicketRepository.findByCreateTime(creatTime);
	}

	@Override
	public void updateTicketByTicketNum(WaitingTicket waitingTicket) {
		waitingTicketRepository.save(waitingTicket);
	}	

	
	
}
