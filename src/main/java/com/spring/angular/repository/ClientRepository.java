package com.spring.angular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.angular.entity.Cliente;
import com.spring.angular.entity.Region;

public interface ClientRepository extends JpaRepository<Cliente, Long>{
	
	@Query("select c from Region c")
	List<Region> findAllRegion();

}
