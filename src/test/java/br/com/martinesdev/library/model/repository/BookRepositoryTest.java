package br.com.martinesdev.library.model.repository;

 
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.martinesdev.library.model.entity.Book;
import br.com.martinesdev.library.repository.BookRepository;

@ExtendWith( SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest // this is for luch h2 memory database configure it to test, then just clean database after
// each test
class BookRepositoryTest {

	@Autowired TestEntityManager entityManager;
	@Autowired BookRepository repository;
	
	@Test
	@DisplayName("Deve retornar verdadeiro quando existir um livro na base com o isbn informado")
	void returnTrueWhenIsbnExists() {
		// cenario 
		String isbn = "123";
		
		Book book = Book
				.builder()
				.author("Lucas")
				.isbn(isbn)
				.title("Do job of testing with cuteness")
				.build();
		
		// SAVE MY BOOK IN D2 DATABASE
		entityManager.persist( book );
		
		
		// execução
		// NOW MY BOOK IS IN DATABASE IF THIS FUNCTION WORKS 
		// IT MEANS THAT IT 'WORKS' hehehe
		boolean existsByIsbn = repository.existsByIsbn(isbn);
		
		// verificação -> verify if it works, what i mean
		// is existsByIsbn shoud be true 
		assertThat( existsByIsbn ).isTrue();
	}
	
	
	@Test
	@DisplayName("Deve retornar falso quando NÃO existir um livro na base com o isbn informado")
	void returnFalseWhenIsbnExists() {
		// cenario 
		String isbn = "123";		
		
		// execução
		// I DONT HAVE ANY BOOK WITH THIS ISBN IN DATABASE THEN
		// IT SHOULD RETURN FALSE, that mean IT 'WORKS'   
		boolean existsByIsbn = repository.existsByIsbn(isbn);
		
		// verificação 
		assertThat( existsByIsbn ).isFalse();
	}
}
