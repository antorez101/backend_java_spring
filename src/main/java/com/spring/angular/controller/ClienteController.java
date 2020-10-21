package com.spring.angular.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.angular.entity.Cliente;
import com.spring.angular.entity.Region;
import com.spring.angular.service.ClienteService;
import com.spring.angular.service.UploadFileService;
import com.spring.angular.utils.Utils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	
	private ClienteService clienteService;
	UploadFileService uploadFileService;
	private final static String PATH_UPLOAD = "uploads";
	private final static String PATH_UPLOAD_DEFAULT = "src/main/resources/static/img/";
	private final static String USER_DEFAULT = "no-user.png";
	
	public ClienteController(ClienteService clienteService, UploadFileService uploadFileService) {
		this.clienteService = clienteService;
		this.uploadFileService= uploadFileService;
	}
	
	@GetMapping(path = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?> findAll(){
		List<Cliente> clientes = clienteService.findAll();
		if (CollectionUtils.isNotEmpty(clientes)) {
			return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@GetMapping(path = "/clientes/page/{page}", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?> findAll(@PathVariable Integer page){
		log.info("Parameter page value {} ", page);
		Pageable pageable = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteService.findAll(pageable);
		if (null != clientes) {
			return new ResponseEntity<Page<Cliente>>(clientes, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping(path = "/clientes/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
	
		System.out.println("Find cliente with id "+id);
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = null;
		try {
			cliente = clienteService.findById(id);
		}catch(DataAccessException ex) {
			response.put("mensaje", "Error al recuperar el usuario");
			response.put("error", ex.getMessage().concat(":").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (!Objects.nonNull(cliente)) {			
			response.put("mensaje", "El cliente id ".concat(id.toString()).concat(" no existe en la bd"));			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	
	@Secured("ROLE_USER")
	@PostMapping(path = "/clientes")
	public ResponseEntity<Map<String, Object>> saveCliente(@Valid @RequestBody Cliente cliente, BindingResult resultBinding) {
		System.out.println("Cliente recibido >> "+ cliente);
		Map<String, Object> response = new HashMap<>();
		Cliente clienteSaved = null;
		List<String> errors = Utils.getErrors(resultBinding);
		if (CollectionUtils.isNotEmpty(errors)) {
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {			
			clienteSaved = clienteService.save(cliente);
			response.put("mensaje", "El cliente se creo con exito");
			response.put("client", clienteSaved);	
			
		}catch(DataAccessException ex) {
			response.put("mensaje", "Error al crear el cliente");
			response.put("error", ex.getMessage().concat(":").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping(path = "/clientes/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = clienteService.findById(id);
		try{			
			clienteService.delete(id);
			response.put("mensaje", "Cliente eliminado con éxito");
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cliente.getPicture() != null) {
			Path pathPreviousFile = uploadFileService.retrivePathFile(PATH_UPLOAD, cliente.getPicture());
			uploadFileService.removeFile(pathPreviousFile);			
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping(path = "/clientes/{id}")
	public ResponseEntity<Map<String, Object>> updateCliente(@Valid @RequestBody Cliente cliente, BindingResult bindingResult,  @PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		List<String> errors = Utils.getErrors(bindingResult);
		if (CollectionUtils.isNotEmpty(errors)) {
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			Cliente clienteUpdated = clienteService.findById(id);
			clienteUpdated.setClientName(cliente.getClientName());
			clienteUpdated.setCreatedAt(cliente.getCreatedAt());
			clienteUpdated.setEmail(cliente.getEmail());
			clienteUpdated.setLastName(cliente.getLastName());
			clienteUpdated.setRegion(cliente.getRegion());
			clienteService.updateCliente(clienteUpdated);
			response.put("mensaje", "Se actualizo correctamente el cliente");
			response.put("client", clienteUpdated);
		}catch(DataAccessException ex) {
			response.put("mensaje", "Error al actualizar el cliente");
			response.put("error", ex.getMessage().concat(":").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	
	@PostMapping(path = "/clientes/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("archivo") MultipartFile file, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<String, Object>();
		Cliente cliente = clienteService.findById(id);
		String fileName = null;
		if (Objects.nonNull(cliente) && Objects.nonNull(file)) {
			fileName = cliente.getId().toString().concat("_").concat(file.getOriginalFilename()).replace(" ", "");
			Path pathFile = uploadFileService.retrivePathFile(PATH_UPLOAD, fileName);
			try {
				if (cliente.getPicture() != null) {
					Path pathPreviousFile = uploadFileService.retrivePathFile(PATH_UPLOAD, cliente.getPicture());					
					uploadFileService.removeFile(pathPreviousFile);
				}
				
				Files.copy(file.getInputStream(), pathFile);				
			} catch (IOException e) {				
				response.put("mensaje", "Error al cargar la imagen");
				response.put("error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		cliente.setPicture(fileName);
		Cliente clienteUpdated = clienteService.save(cliente);
		response.put("mensaje", "Picture uploaded in database");
		response.put("client", clienteUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping(path = "/clientes/download/img/{pictureName:.+}")
	public ResponseEntity<Resource> downloadPicture(@PathVariable String pictureName){
		Path pathFile = uploadFileService.retrivePathFile(PATH_UPLOAD, pictureName);
		Resource resource = null;
		
		try {
			resource = uploadFileService.retriveResource(pathFile);
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		}
		if (!resource.exists() && !resource.isReadable()) {
			pathFile = uploadFileService.retrivePathFile(PATH_UPLOAD_DEFAULT, USER_DEFAULT); 
			try {
				resource = uploadFileService.retriveResource(pathFile);
			} catch (MalformedURLException e) {			
				e.printStackTrace();
			}
			log.info("The image can´t be loaded");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"");
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping(path = "/clientes/regiones")
	public ResponseEntity<List<Region>> getAllRegiones(){
		
		return new ResponseEntity<List<Region>>(clienteService.findAllRegiones(), HttpStatus.OK);
	}
}
