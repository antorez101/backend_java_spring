package com.spring.angular.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;



@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{
	
	
	private static final long serialVersionUID = 4282600387916847343L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Length(min = 4, max = 50)
	@Column(name = "client_name", nullable = false )	
	private String clientName;
	
	@NotEmpty
	@Column(name = "last_name")
	private String lastName;
	
	@NotEmpty
	@Email
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	@Column(name = "picture")
	private String picture;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Region region;
	
	@JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Factura> facturas;
	
	public Cliente() {
		this.facturas = new ArrayList<Factura>();
	}
	
	
}
