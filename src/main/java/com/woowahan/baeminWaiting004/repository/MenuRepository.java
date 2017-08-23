package com.woowahan.baeminWaiting004.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woowahan.baeminWaiting004.model.Menu;


public interface MenuRepository extends JpaRepository<Menu, Integer>{
	
	ArrayList<Menu> findByStoreId(int storeId);
}
