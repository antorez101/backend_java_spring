package com.spring.angular.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.angular.entity.Factura;
import com.spring.angular.repository.FacturaRepository;

@Service
public class FacturaServiceImpl implements FacturaService {
	
	
	private FacturaRepository facturaRespository;
	
	public FacturaServiceImpl(FacturaRepository facturaRespository) {
		this.facturaRespository = facturaRespository;
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Long id) {		
		return facturaRespository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Factura saveFactura(Factura factura) {
		
		return facturaRespository.save(factura);
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaRespository.deleteById(id);

	}

}
