package br.com.martinesdev.library.api.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;

import br.com.martinesdev.library.exceptions.BusinessException;

public class ApiErrors {
 
	private List<String> errors;
	
	public ApiErrors(BindingResult bindResult) {
		errors = new ArrayList<>();
		
		bindResult.getAllErrors()
			.forEach( e -> errors.add( e.getDefaultMessage() ));
	}
	
	public ApiErrors(BusinessException e) {
		errors =  Arrays.asList( e .getMessage() );
	}

	public List<String> getErrors(){
		return errors;
	}
}
