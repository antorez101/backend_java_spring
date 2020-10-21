package com.spring.angular.service;

import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;

import com.spring.angular.entity.Cliente;

public interface UploadFileService {
	
	public void removeFile(Path pathPreviousFile);
	
	public Path retrivePathFile(String path, String pictureName);
	
	public Resource retriveResource(Path pathFile)throws MalformedURLException;
	
}
