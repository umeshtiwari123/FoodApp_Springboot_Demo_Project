package com.ty.foodapp.foodapp_boot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.foodapp.foodapp_boot.dto.Menu;
import com.ty.foodapp.foodapp_boot.repository.MenuRepository;

@Repository
public class MenuDao {

	@Autowired
	MenuRepository repository;

	public Menu saveMenu(Menu menu) {
		return repository.save(menu);
	}

	public Menu updateMenu(Menu menu) {
		return repository.save(menu);
	}

	public Menu getMenuById(int id) {
		Optional<Menu> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public String deleteMenuById(int id) {
		repository.deleteById(id);
		return "deleted";
	}
}
