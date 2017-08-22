package com.woowahan.baeminWaiting004.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STORE")
public class Store {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto increment일 경우
	@Column(name="store_id")
	private int id;
	
	@Column(name="store_title")
	private String title;
	
	@Column(name="store_desc")
	private String description;
	
	@Column(name="store_addr")
	private String address;
	
	@Column(name="store_latitude")
	private String latitude;
	
	@Column(name="store_longitude")
	private String longitude;
	
	@Column(name="store_tel")
	private String tel;
	
	@Column(name="store_is_opened")
	private int opened;
	
	@Column(name="member_id")
	private String memberId;
	
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getOpened() {
		return opened;
	}

	public void setOpened(int opened) {
		this.opened = opened;
	}
}
