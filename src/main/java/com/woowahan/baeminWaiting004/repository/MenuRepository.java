package com.woowahan.baeminWaiting004.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woowahan.baeminWaiting004.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, String>{
	
	Menu findByStoreId(int storeId);
}
