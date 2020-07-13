package br.com.martinesdev.library.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.martinesdev.library.exceptions.BusinessException;
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
		
		Book book = createValidBook();
		
		// execução 
		
		Mockito
		   .when( repository.existsByIsbn( Mockito.anyString() ) )
		   .thenReturn( false );
		
		
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
		
		assertThat( savedBook.getId() ).isNotNull();
		assertThat( savedBook.getIsbn()).isEqualTo("123");
		assertThat( savedBook.getAuthor() ).isEqualTo("Fulano de Tal");
		assertThat( savedBook.getTitle() ).isEqualTo("As aventuras");
		
	}
	
	@Test
	@DisplayName("Deve lançar um erro de negócio quando um livro com o mesmo isbn já foi "
			+ "cadastrado")
	void shoudNotSaveBookWithSameISBN() {
		
		//cenário 
		Book book = createValidBook();
		Mockito
		   .when( repository.existsByIsbn( Mockito.anyString() ) )
		   .thenReturn( true );
		
		// execução
		Throwable exception = Assertions.catchThrowable(  () -> service.save(book) );
		
		// verificação
		assertThat( exception)
		   .isInstanceOf( BusinessException.class )
		   .hasMessage("ISBN já foi cadastrado");
		
		Mockito.verify( repository , Mockito.never() ).save(book);
		
	}
	private Book createValidBook() {
		return Book
				  .builder()
				  .isbn("123")
				  .author("Fulano de Tal")
				  .title("As aventuras")
				  .build();
	}
}
