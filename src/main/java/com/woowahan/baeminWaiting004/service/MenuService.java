package com.woowahan.baeminWaiting004.service;

import java.util.ArrayList;

import com.woowahan.baeminWaiting004.model.Menu;

public interface MenuService {

	void addMenu(ArrayList<Menu> menus, int storeId);
	ArrayList<Menu> findByStoreId(int storeId);
	void removeMenuByStoreId(int storeId);
	void removeMenuOneByOne(int id);
	
}
