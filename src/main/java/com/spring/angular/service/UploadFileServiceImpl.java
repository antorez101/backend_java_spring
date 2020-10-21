package com.spring.angular.service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.spring.angular.entity.Cliente;

@Service
public class UploadFileServiceImpl implements UploadFileService{
	
	private final static String PATH_UPLOAD = "uploads";

	@Override
	public void removeFile(Path pathPreviousFile) {		
		File previousFile = pathPreviousFile.toFile();
		if (previousFile.exists()) {
			previousFile.delete();
		}
		
	}
	
	@Override
	public Path retrivePathFile(String path, String pictureName) {
		return Paths.get(path).resolve(pictureName).toAbsolutePath();
	}

	@Override
	public Resource retriveResource(Path pathFile) throws MalformedURLException {
		
		return new UrlResource(pathFile.toUri());
	}

}
