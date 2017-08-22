package com.woowahan.baeminWaiting004.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowahan.baeminWaiting004.model.Menu;
import com.woowahan.baeminWaiting004.repository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public void addMenu(Menu menu) {
		menuRepository.save(menu);
		
	}

	@Override
	public Menu findByStoreId(int storeId) {
		return menuRepository.findByStoreId(storeId);
	}
	
	
	
}
