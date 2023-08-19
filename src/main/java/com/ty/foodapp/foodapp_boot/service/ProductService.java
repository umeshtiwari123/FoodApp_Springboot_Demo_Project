package com.ty.foodapp.foodapp_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodapp.foodapp_boot.dao.ProductDao;
import com.ty.foodapp.foodapp_boot.dto.Product;
import com.ty.foodapp.foodapp_boot.exception.NoSuchIdFoundException;
import com.ty.foodapp.foodapp_boot.exception.UnableToDeleteException;
import com.ty.foodapp.foodapp_boot.exception.UnableToUpdateException;
import com.ty.foodapp.foodapp_boot.util.ResponseStructure;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Product>> responseEntity = new ResponseEntity<ResponseStructure<Product>>(
				responseStructure, HttpStatus.CREATED);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("saved");
		responseStructure.setData(productDao.saveProduct(product));
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product, int id) {
		Product product2 = productDao.getProductById(id);
		ResponseStructure<Product> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Product>> responseEntity = new ResponseEntity<ResponseStructure<Product>>(
				responseStructure, HttpStatus.OK);

		if (product2 != null) {
			product.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("updated");
			responseStructure.setData(productDao.updateProduct(product));
		} else {
//			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			responseStructure.setMessage("not found");
//			responseStructure.setData(null);
			throw new UnableToUpdateException("No Id To Update");

		}
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Product>> getProductById(int id) {
		Product product = productDao.getProductById(id);
		ResponseStructure<Product> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Product>> responseEntity = new ResponseEntity<ResponseStructure<Product>>(
				responseStructure, HttpStatus.OK);

		if (product != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(productDao.getProductById(id));
		} else {
			throw new NoSuchIdFoundException("No Id Found");
		}
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteProductById(int id) {
		Product product = productDao.getProductById(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(
				responseStructure, HttpStatus.OK);
		if (product != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("deleted");
			responseStructure.setData(productDao.deleteProductById(id));
		} else {
			throw new UnableToDeleteException("No Id To Delete");
		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

}