package com.spring.angular.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable{
	
	
	private static final long serialVersionUID = 6244524057716976166L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRole;
	
	@Column(unique = true)
	private String roleName;
	
	//@ManyToMany(mappedBy = "roles") // we use this annotation to implement bidirectional relation
	//private List<Usuario> usuarios;
	

}
