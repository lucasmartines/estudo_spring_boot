package br.com.martinesdev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.martinesdev.library.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

	boolean existsByIsbn(String isbn);
	
}
