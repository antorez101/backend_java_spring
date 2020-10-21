package com.spring.angular.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.angular.entity.Cliente;
import com.spring.angular.entity.Region;
import com.spring.angular.repository.ClientRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	private final ClientRepository clientRepository;
	
	public ClienteServiceImpl(ClientRepository clientRepository) {
		this.clientRepository= clientRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {		
		return (List<Cliente>) clientRepository.findAll();
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {		
		return clientRepository.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {		
		return clientRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clientRepository.deleteById(id);;
		
	}

	@Override
	@Transactional
	public void updateCliente(Cliente cliente) {
		clientRepository.save(cliente);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		
		return clientRepository.findAll(pageable);
	}

	@Override
	public List<Region> findAllRegiones() {
		
		return clientRepository.findAllRegion();
	}

}
