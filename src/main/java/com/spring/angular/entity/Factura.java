package com.spring.angular.entity;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class Factura implements Serializable{
	
	
	private static final long serialVersionUID = 8177384917623143446L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFactura;
	private String description;
	private String observation;
	
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@JsonIgnoreProperties(value={"facturas", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "factura_id")
	private List<ItemFactura> itemFacturas;
	
	@PrePersist
	public void prePersistDate() {
		this.createAt = new Date();
	}
	
	public Factura() {
		this.itemFacturas = new ArrayList<ItemFactura>();
	}

	public double getTotal() {
		double total = 
		this.itemFacturas.stream()
				.filter(Objects::nonNull)
			.map(ItemFactura::getTotal)			
			.reduce((f1,f2) -> f1+f2)
			.orElse(null);
		return total;
	}
}
