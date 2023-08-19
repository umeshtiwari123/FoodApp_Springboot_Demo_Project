package com.ty.foodapp.foodapp_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodapp.foodapp_boot.dao.FoodOrderDao;
import com.ty.foodapp.foodapp_boot.dto.FoodOrder;
import com.ty.foodapp.foodapp_boot.dto.Product;
import com.ty.foodapp.foodapp_boot.exception.NoSuchIdFoundException;
import com.ty.foodapp.foodapp_boot.exception.UnableToDeleteException;
import com.ty.foodapp.foodapp_boot.exception.UnableToUpdateException;
import com.ty.foodapp.foodapp_boot.util.ResponseStructure;

@Service
public class FoodOrderService {

	@Autowired
	private FoodOrderDao foodOrderDao;

	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder foodOrder) {

		List<Product> list = foodOrder.getProducts();
		double totalCost = 0;
		for (Product product : list) {
			totalCost = totalCost + (product.getPrice() * product.getQuantity());
		}
		totalCost = (totalCost * 0.18) + totalCost;
		foodOrder.setTotalCost(totalCost);
		ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<FoodOrder>> responseEntity = new ResponseEntity<ResponseStructure<FoodOrder>>(
				responseStructure, HttpStatus.CREATED);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("saved");
		responseStructure.setData(foodOrderDao.saveFoodOrder(foodOrder));
		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> updateUser(FoodOrder foodOrder, int id) {
		FoodOrder foodOrder2 = foodOrderDao.getFoodOrderById(id);
		ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<FoodOrder>> responseEntity = new ResponseEntity<ResponseStructure<FoodOrder>>(
				responseStructure, HttpStatus.OK);

		if (foodOrder2 != null) {
			foodOrder.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("updated");
			responseStructure.setData(foodOrderDao.updateFoodOrder(foodOrder));
		} else {
//			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			responseStructure.setMessage("not found");
//			responseStructure.setData(null);
			throw new UnableToUpdateException("No Id To Update");
		}
		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrderById(int id) {
		FoodOrder foodOrder = foodOrderDao.getFoodOrderById(id);
		ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<FoodOrder>> responseEntity = new ResponseEntity<ResponseStructure<FoodOrder>>(
				responseStructure, HttpStatus.OK);

		if (foodOrder != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(foodOrderDao.getFoodOrderById(id));
		} else {
			throw new NoSuchIdFoundException("No Id Found");
		}
		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteFoodOrderById(int id) {
		FoodOrder foodOrder = foodOrderDao.getFoodOrderById(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(
				responseStructure, HttpStatus.OK);

		if (foodOrder != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("deleted");
			responseStructure.setData(foodOrderDao.deleteFoodOrderById(id));
		} else {
			throw new UnableToDeleteException("No Id To Delete");
		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

}
