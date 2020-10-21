package com.spring.angular.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.angular.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByUserName(String userName);

}
