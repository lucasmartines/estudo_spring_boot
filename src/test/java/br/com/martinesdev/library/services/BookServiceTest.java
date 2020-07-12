package br.com.martinesdev.library.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.martinesdev.library.model.entity.Book;
import br.com.martinesdev.library.repository.BookRepository;
import br.com.martinesdev.library.services.impl.BookServiceInpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class BookServiceTest {
	
	BookService service;
	
	@MockBean
	private BookRepository repository;
	
	
	@BeforeEach
	void setUp() {
		service = new BookServiceInpl( repository );
		
	}
	@Test
	@DisplayName("deve salvar um livro")
	void saveBookTest() {
		// cenario
		
		Book book = Book
		  .builder()
		  .isbn("123")
		  .author("Fulano de Tal")
		  .title("As aventuras")
		  .build();
		
		// execução 
		
		
		Mockito.when( repository.save( book ) ).thenReturn( 
		   Book
		     .builder()
		     .id(1L)
		     .isbn("123")
		     .title("As aventuras")
		     .author("Fulano de Tal")
		     .build()
		);
		
		Book savedBook = service.save( book );
		
		// verificação
		
		Assertions.assertThat( savedBook.getId() ).isNotNull();
		Assertions.assertThat( savedBook.getIsbn()).isEqualTo("123");
		Assertions.assertThat( savedBook.getAuthor() ).isEqualTo("Fulano de Tal");
		Assertions.assertThat( savedBook.getTitle() ).isEqualTo("As aventuras");
		
	}
}
