package com.ty.foodapp.foodapp_boot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.foodapp.foodapp_boot.dto.FoodOrder;
import com.ty.foodapp.foodapp_boot.repository.FoodOrderRepository;

@Repository
public class FoodOrderDao {

	@Autowired
	FoodOrderRepository repository;

	public FoodOrder saveFoodOrder(FoodOrder foodOrder) {
		return repository.save(foodOrder);
	}

	public FoodOrder updateFoodOrder(FoodOrder foodOrder) {
		return repository.save(foodOrder);
	}

	public FoodOrder getFoodOrderById(int id) {
		Optional<FoodOrder> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public String deleteFoodOrderById(int id) {
		repository.deleteById(id);
		return "deleted";
	}

}
