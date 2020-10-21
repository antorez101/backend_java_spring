package com.spring.angular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.angular.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	@Query("select p from Producto p where p.description like %?1%")
	public List<Producto> findProductosByNombre(String nombre);

}
