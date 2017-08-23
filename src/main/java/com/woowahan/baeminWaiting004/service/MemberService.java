package com.woowahan.baeminWaiting004.service;

import com.woowahan.baeminWaiting004.model.Member;

public interface MemberService {

	Member findByUsername(String name);
	
	void addMember(String memberId, String userPassword, String userMemberTel, int userRole , String userName);
	
	int countByMemberId(String memberId);
}
