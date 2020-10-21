package com.spring.angular.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.angular.entity.Producto;
import com.spring.angular.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	private ProductoRepository productoRepository;
	
	public ProductoServiceImpl(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Override
	public List<Producto> findProductosByName(String name) {
		
		return productoRepository.findProductosByNombre(name);
	}

}
