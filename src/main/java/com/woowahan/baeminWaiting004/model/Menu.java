package com.woowahan.baeminWaiting004.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MENU")
public class Menu {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto increment일 경우
	@Column(name="menu_id")
	private int menuId;
	
	@Column(name="store_id")
	private int storeId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private int price;
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	
	
	
}
