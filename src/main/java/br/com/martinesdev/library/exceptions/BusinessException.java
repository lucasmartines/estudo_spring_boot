package br.com.martinesdev.library.exceptions;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {

	public BusinessException(String msg) {
		 super( msg );
	}

}
