package com.woowahan.baeminWaiting004.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woowahan.baeminWaiting004.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	Member findByMemberId(String memberId);
}
