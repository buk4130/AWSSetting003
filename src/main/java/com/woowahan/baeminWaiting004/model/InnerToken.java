package com.woowahan.baeminWaiting004.model;

public class InnerToken {
	private String token;
	private String memberId;
	private int storeId;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	@Override
	public String toString() {
		return "InnerToken [token=" + token + ", memberId=" + memberId + ", storeId=" + storeId + "]";
	}
	
	
}
