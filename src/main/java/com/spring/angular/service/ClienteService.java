package com.spring.angular.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.angular.entity.Cliente;
import com.spring.angular.entity.Region;

public interface ClienteService {
	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public Cliente save(Cliente cliente);

	public Cliente findById(Long id);
	
	public void delete(Long id);
	
	public void updateCliente(Cliente cliente);
	
	public List<Region> findAllRegiones();
}
