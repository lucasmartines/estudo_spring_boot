package br.com.martinesdev.library.api.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;

public class ApiErrors {
 
	private List<String> errors;
	
	public ApiErrors(BindingResult bindResult) {
		errors = new ArrayList<>();
		
		bindResult.getAllErrors()
			.forEach( e -> errors.add( e.getDefaultMessage() ));
	}
	
	public List<String> getErrors(){
		return errors;
	}
}
