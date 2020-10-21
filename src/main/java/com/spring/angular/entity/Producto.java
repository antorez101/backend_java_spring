package com.spring.angular.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Producto implements Serializable{
	
	private static final long serialVersionUID = 6787642329296805478L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productoId;
	
	private String description;
	
	private double precio;
	
	

}
