package br.com.martinesdev.library.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.martinesdev.library.api.dto.BookDTO;
import br.com.martinesdev.library.api.exceptions.ApiErrors;
import br.com.martinesdev.library.model.entity.Book;
import br.com.martinesdev.library.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController 
{
	
	private BookService service;
	private ModelMapper modelMapper;
	

	public BookController(BookService service , ModelMapper modelMapper) {
		 
		this.service = service;
		this.modelMapper = modelMapper;
	}



	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public BookDTO create( @RequestBody @Valid BookDTO dto ) {
		
		Book bookEntity = convertBookDTOintoBook(dto);
 		
		bookEntity = service.save( bookEntity ) ; 
						
		return  revertFromBookToBookDTO(bookEntity);
		
 
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationException( MethodArgumentNotValidException e  ) {
		
		BindingResult bindResult = e.getBindingResult();
				
		return new ApiErrors( bindResult );
	}
	



	private BookDTO revertFromBookToBookDTO(Book bookEntity) {
		return modelMapper.map( bookEntity , BookDTO.class );
	}



	private Book convertBookDTOintoBook(BookDTO dto) {
		return modelMapper.map( dto , Book.class);
	}

}
