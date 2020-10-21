package com.spring.angular.service;

import com.spring.angular.entity.Factura;

public interface FacturaService {
	
	public Factura findFacturaById(Long id);
	
	public Factura saveFactura(Factura factura);
	
	public void deleteFactura(Long id);

}
