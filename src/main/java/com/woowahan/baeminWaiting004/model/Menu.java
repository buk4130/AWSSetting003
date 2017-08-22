package com.woowahan.baeminWaiting004.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MENU")
public class Menu {
	@Id
	@Column(name="store_id")
	private int storeId;
	@Column(name="menu1")
	private String menu1;
	@Column(name="menu2")
	private String menu2;
	@Column(name="menu3")
	private String menu3;
	@Column(name="menu4")
	private String menu4;
	@Column(name="menu5")
	private String menu5;
	@Column(name="menu6")
	private String menu6;
	@Column(name="menu7")
	private String menu7;
	@Column(name="menu8")
	private String menu8;
	@Column(name="menu9")
	private String menu9;
	@Column(name="menu10")
	private String menu10;
		
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getMenu1() {
		return menu1;
	}
	public void setMenu1(String menu1) {
		this.menu1 = menu1;
	}
	public String getMenu2() {
		return menu2;
	}
	public void setMenu2(String menu2) {
		this.menu2 = menu2;
	}
	public String getMenu3() {
		return menu3;
	}
	public void setMenu3(String menu3) {
		this.menu3 = menu3;
	}
	public String getMenu4() {
		return menu4;
	}
	public void setMenu4(String menu4) {
		this.menu4 = menu4;
	}
	public String getMenu5() {
		return menu5;
	}
	public void setMenu5(String menu5) {
		this.menu5 = menu5;
	}
	public String getMenu6() {
		return menu6;
	}
	public void setMenu6(String menu6) {
		this.menu6 = menu6;
	}
	public String getMenu7() {
		return menu7;
	}
	public void setMenu7(String menu7) {
		this.menu7 = menu7;
	}
	public String getMenu8() {
		return menu8;
	}
	public void setMenu8(String menu8) {
		this.menu8 = menu8;
	}
	public String getMenu9() {
		return menu9;
	}
	public void setMenu9(String menu9) {
		this.menu9 = menu9;
	}
	public String getMenu10() {
		return menu10;
	}
	public void setMenu10(String menu10) {
		this.menu10 = menu10;
	}
	
	
	
}
