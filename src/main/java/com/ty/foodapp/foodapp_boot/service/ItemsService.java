package com.ty.foodapp.foodapp_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodapp.foodapp_boot.dao.ItemsDao;
import com.ty.foodapp.foodapp_boot.dto.Items;
import com.ty.foodapp.foodapp_boot.exception.NoSuchIdFoundException;
import com.ty.foodapp.foodapp_boot.exception.UnableToDeleteException;
import com.ty.foodapp.foodapp_boot.exception.UnableToUpdateException;
import com.ty.foodapp.foodapp_boot.util.ResponseStructure;

@Service
public class ItemsService {

	@Autowired
	private ItemsDao itemsDao;

	public ResponseEntity<ResponseStructure<Items>> saveItems(Items items) {
		ResponseStructure<Items> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Items>> responseEntity = new ResponseEntity<ResponseStructure<Items>>(
				responseStructure, HttpStatus.CREATED);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("saved");
		responseStructure.setData(itemsDao.saveItems(items));
		return new ResponseEntity<ResponseStructure<Items>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Items>> updateItems(Items items, int id) {
		Items items2 = itemsDao.getItemsById(id);
		ResponseStructure<Items> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Items>> responseEntity = new ResponseEntity<ResponseStructure<Items>>(
				responseStructure, HttpStatus.OK);

		if (items2 != null) {
			items.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("updated");
			responseStructure.setData(itemsDao.updateItems(items));
		} else {
//			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			responseStructure.setMessage("not found");
//			responseStructure.setData(null);
			throw new UnableToUpdateException("No Id To Update");
		}
		return new ResponseEntity<ResponseStructure<Items>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Items>> getItemsById(int id) {
		Items items = itemsDao.getItemsById(id);
		ResponseStructure<Items> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Items>> responseEntity = new ResponseEntity<ResponseStructure<Items>>(
				responseStructure, HttpStatus.OK);

		if (items != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(itemsDao.getItemsById(id));
		} else {
			throw new NoSuchIdFoundException("No Id Found");
		}
		return new ResponseEntity<ResponseStructure<Items>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteItemsById(int id) {
		Items items = itemsDao.getItemsById(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(
				responseStructure, HttpStatus.OK);

		if (items != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("deleted");
			responseStructure.setData(itemsDao.deleteItemsById(id));
		} else {
			throw new UnableToDeleteException("No Id To Delete");
		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

}
