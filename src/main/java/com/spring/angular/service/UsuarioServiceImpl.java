package com.spring.angular.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.angular.entity.Usuario;
import com.spring.angular.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioServiceImpl implements UserDetailsService{
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		log.info("En backend service usuario ");
		Usuario usuario = usuarioRepository.findByUserName(username);
		if (null == usuario) throw new UsernameNotFoundException("El usuario no se encuentro"+username);
		
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
												.filter(Objects::nonNull)
												.map(user -> new SimpleGrantedAuthority(user.getRoleName()))
												.collect(Collectors.toList());
		log.info("User {}, logged with roles {} ", usuario.getIdUsuario(), usuario.getRoles());
		return new User(usuario.getUserName(), usuario.getPassword(), usuario.isEnable(), true, true, true, authorities);
	}
}
