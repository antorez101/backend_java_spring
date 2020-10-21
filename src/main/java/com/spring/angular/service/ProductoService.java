package com.spring.angular.service;

import java.util.List;

import com.spring.angular.entity.Producto;

public interface ProductoService {
	
	public List<Producto> findProductosByName(String name);

}
