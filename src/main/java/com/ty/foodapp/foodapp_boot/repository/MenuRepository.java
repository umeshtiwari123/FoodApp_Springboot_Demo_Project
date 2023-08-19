package com.ty.foodapp.foodapp_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.foodapp.foodapp_boot.dto.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
