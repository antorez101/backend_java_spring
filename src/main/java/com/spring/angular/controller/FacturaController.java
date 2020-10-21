package com.spring.angular.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.entity.Factura;
import com.spring.angular.entity.Producto;
import com.spring.angular.service.FacturaService;
import com.spring.angular.service.ProductoService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {"http://localhost:4200"})
@Slf4j
@RestController
@RequestMapping("/api")
public class FacturaController {

	private FacturaService facturaService;
	private ProductoService productoService;
	
	public FacturaController(FacturaService facturaService, ProductoService productoService) {
		this.facturaService = facturaService;
		this.productoService = productoService;
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping(path = "/facturas/{id}")
	public ResponseEntity<Factura> findFactura(@PathVariable Long id){
		Factura factura = facturaService.findFacturaById(id);
		if (factura != null) {
			System.out.println(factura.getTotal());
			return new ResponseEntity<Factura>(factura, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}		
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/facturas")
	public ResponseEntity<Factura> saveFactura(@RequestBody Factura factura) {
		Factura newFactura = facturaService.saveFactura(factura);
		if (null != newFactura) {
			return new ResponseEntity<Factura>(newFactura, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}		
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping(path = "/facturas/{id}")
	public ResponseEntity deleteFactura(@PathVariable Long id){
		try {
			facturaService.deleteFactura(id);
		}catch (Exception e) {
			log.error("Exception deleting orderId {} ", e.getCause().getMessage());
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping(path = "/facturas/productos/{name}")
	public ResponseEntity<List<Producto>> getProductos(@PathVariable String name){
		List<Producto> productos = productoService.findProductosByName(name);
		return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
	}
	
	
}
