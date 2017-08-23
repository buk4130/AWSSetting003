package com.woowahan.baeminWaiting004.service;

import com.woowahan.baeminWaiting004.model.Menu;

public interface MenuService {

	void addMenu(Menu menu);
	Menu findByStoreId(int storeId);
}
