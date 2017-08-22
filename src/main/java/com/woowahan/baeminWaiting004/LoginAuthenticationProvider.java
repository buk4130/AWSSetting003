package com.woowahan.baeminWaiting004;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.woowahan.baeminWaiting004.model.Member;
import com.woowahan.baeminWaiting004.service.MemberService;

public class LoginAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authToken = 
				(UsernamePasswordAuthenticationToken) authentication;
		
		Member member = memberService.findByUsername(authToken.getName());

		if(member == null)
			throw new UsernameNotFoundException(authToken.getName());
		if(!bCryptPasswordEncoder.matches(authToken.getCredentials().toString(), member.getPassword()))
			throw new BadCredentialsException("not matching email or password");
		
		List<GrantedAuthority> authorities = getAuthorities(member.getMemberId());
		return new UsernamePasswordAuthenticationToken(member, member.getPassword(), authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken
				.class.isAssignableFrom(authentication);
	}
	
	private List<GrantedAuthority> getAuthorities(String username) {
		final Member member = memberService.findByUsername(username);
		GrantedAuthority grantedAuthority = new GrantedAuthority() {
			
			private static final long serialVersionUID = -2645616362372980022L;

			@Override
			public String getAuthority() {
				
				if(member.getRole()==1){
					return "USER";
				}else if(member.getRole()==2){
					return "ADMIN";
				}
				return null;
			}
		};
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(grantedAuthority);
		
		return authorities;
	}


}
