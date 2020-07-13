package br.com.martinesdev.library.services.impl;

import org.springframework.stereotype.Service;

import br.com.martinesdev.library.exceptions.BusinessException;
import br.com.martinesdev.library.model.entity.Book;
import br.com.martinesdev.library.repository.BookRepository;
import br.com.martinesdev.library.services.BookService;

@Service
public class BookServiceInpl implements BookService{
	private BookRepository repository;
	
	
	public BookServiceInpl(BookRepository repository) {
		this.repository = repository;
	}


	@Override
	public Book save(Book book) {
		
		if( repository.existsByIsbn ( book.getIsbn() ) ) {
			throw new BusinessException("ISBN já foi cadastrado");
		}
		return repository.save(book);
		 
	}

}
