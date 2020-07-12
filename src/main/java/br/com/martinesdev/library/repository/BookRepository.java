package br.com.martinesdev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.martinesdev.library.model.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
	
}
