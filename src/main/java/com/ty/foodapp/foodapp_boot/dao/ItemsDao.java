package com.ty.foodapp.foodapp_boot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.foodapp.foodapp_boot.dto.Items;
import com.ty.foodapp.foodapp_boot.repository.ItemsRepository;

@Repository
public class ItemsDao {
	@Autowired
	ItemsRepository repository;

	public Items saveItems(Items items) {
		return repository.save(items);
	}

	public Items updateItems(Items items) {
		return repository.save(items);
	}

	public Items getItemsById(int id) {
		Optional<Items> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public String deleteItemsById(int id) {
		repository.deleteById(id);
		return "deleted";
	}
}
