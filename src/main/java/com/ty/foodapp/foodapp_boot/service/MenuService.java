package com.ty.foodapp.foodapp_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodapp.foodapp_boot.dao.MenuDao;
import com.ty.foodapp.foodapp_boot.dto.Menu;
import com.ty.foodapp.foodapp_boot.exception.NoSuchIdFoundException;
import com.ty.foodapp.foodapp_boot.exception.UnableToDeleteException;
import com.ty.foodapp.foodapp_boot.exception.UnableToUpdateException;
import com.ty.foodapp.foodapp_boot.util.ResponseStructure;

@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;

	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu menu) {
		ResponseStructure<Menu> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Menu>> responseEntity = new ResponseEntity<ResponseStructure<Menu>>(
				responseStructure, HttpStatus.CREATED);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("saved");
		responseStructure.setData(menuDao.saveMenu(menu));
		return new ResponseEntity<ResponseStructure<Menu>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Menu>> updateUser(Menu menu, int id) {
		Menu menu2 = menuDao.getMenuById(id);
		ResponseStructure<Menu> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Menu>> responseEntity = new ResponseEntity<ResponseStructure<Menu>>(
				responseStructure, HttpStatus.OK);

		if (menu2 != null) {
			menu.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("updated");
			responseStructure.setData(menuDao.updateMenu(menu));
		} else {
//			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			responseStructure.setMessage("not found");
//			responseStructure.setData(null);
			throw new UnableToUpdateException("No Id To Update");
		}
		return new ResponseEntity<ResponseStructure<Menu>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Menu>> getMenuById(int id) {
		Menu menu = menuDao.getMenuById(id);
		ResponseEntity<ResponseStructure<Menu>> responseEntity = new ResponseEntity<ResponseStructure<Menu>>(null);
		ResponseStructure<Menu> responseStructure = new ResponseStructure<>();
		if (menu != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(menuDao.getMenuById(id));
		} else {
			throw new NoSuchIdFoundException("No Id Found");
		}
		return new ResponseEntity<ResponseStructure<Menu>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteMenuById(int id) {
		Menu menu = menuDao.getMenuById(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(
				responseStructure, HttpStatus.OK);

		if (menu != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("deleted");
			responseStructure.setData(menuDao.deleteMenuById(id));
		} else {
			throw new UnableToDeleteException("No Id To Delete");
		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}
}
