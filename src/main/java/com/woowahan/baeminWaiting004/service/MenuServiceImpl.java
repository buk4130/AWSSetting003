package com.woowahan.baeminWaiting004.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowahan.baeminWaiting004.model.Menu;
import com.woowahan.baeminWaiting004.repository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public void addMenu(ArrayList<Menu> menus, int storeId) {
		for(Menu m : menus) {
			menuRepository.save(m);
		}		
	}

	@Override
	public ArrayList<Menu> findByStoreId(int storeId) {
		return menuRepository.findByStoreId(storeId);
	}

	@Override
	public void removeMenuByStoreId(int storeId) {
		menuRepository.delete(storeId);
		
	}

	@Override
	public void removeMenuOneByOne(int id) {
		menuRepository.delete(id);
		
	}
	
	
	
	
	
	
}
