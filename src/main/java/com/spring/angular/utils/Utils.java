package com.spring.angular.utils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class Utils {
	
	public static List<String> getErrors(BindingResult result){
		
		return Optional.ofNullable(result)
				.map(field -> field.getFieldErrors().stream()
						.filter(Objects::nonNull)
						.map(campo -> "El campo ".concat(campo.getField()).concat(" ").concat(campo.getDefaultMessage()))
						.collect(Collectors.toList())
						).orElse(null);
				
	}

}
