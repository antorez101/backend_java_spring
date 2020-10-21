package com.spring.angular.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class ItemFactura implements Serializable{
	
	
	private static final long serialVersionUID = -617431868167465185L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemFacturaId;
	
	private Integer cantidad = 1;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	public Double getTotal() {
		return cantidad.doubleValue()*producto.getPrecio();
	}
	

}
