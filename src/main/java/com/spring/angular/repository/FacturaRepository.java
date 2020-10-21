package com.spring.angular.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.angular.entity.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
