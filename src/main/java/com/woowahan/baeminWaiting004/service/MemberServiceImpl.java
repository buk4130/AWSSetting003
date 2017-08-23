package com.woowahan.baeminWaiting004.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.woowahan.baeminWaiting004.LoginAuthenticationProvider;
import com.woowahan.baeminWaiting004.model.Member;
import com.woowahan.baeminWaiting004.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepository memberRepository;
	
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Member findByUsername(String memberId) {

		Member member = memberRepository.findByMemberId(memberId);
		return member;
	}
	
 
	@Override
	public void addMember(String memberId, String userPassword, String userMemberTel, int userRole ,String userName) {
		Member member = new Member();
		
		member.setMemberId(memberId);
//		member.setPassword(bCryptPasswordEncoder.encode(userPassword));
		member.setPassword(userPassword);
		member.setTel(userMemberTel);
		member.setRole(userRole);
		member.setName(userName);
		
		memberRepository.save(member);
	}


	@Override
	public int countByMemberId(String memberId) {
		return memberRepository.countByMemberId(memberId);
	}
	
	
}
