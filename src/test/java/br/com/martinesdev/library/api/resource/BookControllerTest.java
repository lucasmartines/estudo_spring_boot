package br.com.martinesdev.library.api.resource;


 

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.martinesdev.library.api.dto.BookDTO;
import br.com.martinesdev.library.model.entity.Book;
import br.com.martinesdev.library.services.BookService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
class BookControllerTest {
	
	static 	String BOOK_API = "/api/books";
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	BookService service;
	
	@Test
	@DisplayName("deve criar um livro com sucesso")
	void createBookTest() throws Exception
	{
		//cenario
		BookDTO bookRequest = BookDTO
		  .builder()
		  .author("Lucas")
		  .title("Teste Titulo")
		  .isbn("123456")
		  .build() ;
		
		
		Book savedBook = Book
		  .builder()
		  .author("Lucas")
	      .title("Teste Titulo")
		  .isbn("123456")
		  .id(10L)
		  .build() ;
		
		
		BDDMockito.given(
	      service.save( Mockito.any( Book.class ))
		).willReturn( 
			savedBook
		);
		
		String json = new ObjectMapper().writeValueAsString(bookRequest);
		System.out.println("OBJECT SENT: "+json);
		
		/*execute request post
		 * i will send book Request object dto to my mvc using a json,
		 * and i will check if it will send back to me a valid
		 * response equal that json plus id */
		MockHttpServletRequestBuilder request = 
			MockMvcRequestBuilders
			  .post( BOOK_API )
			  .contentType( MediaType.APPLICATION_JSON )
			  .accept(MediaType.APPLICATION_JSON)
			  .content(json);
		
		/*verify*/
		mvc
			.perform(request)
			.andExpect( status().isCreated())
			.andExpect( jsonPath( "id").isNotEmpty() )
			.andExpect( jsonPath("title").value( bookRequest.getTitle() ))
			.andExpect( jsonPath("author").value( bookRequest.getAuthor() ))
			.andExpect( jsonPath("isbn").value( bookRequest.getIsbn() ))
			;
		
		
		
		
	}
	
	@Test
	@DisplayName("deve lançar 3 erros quando não ouver"
			+ "dados os suficiente para criar o livro")
	void createInvalidBookTest() throws Exception
	{
		String json = new ObjectMapper().writeValueAsString( new BookDTO() );
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
			  .post( BOOK_API )
			  .contentType( MediaType.APPLICATION_JSON )
			  .accept(MediaType.APPLICATION_JSON)
			  .content(json);
		
		mvc.perform( request )
		   .andExpect( status().isBadRequest() )
		   .andExpect( jsonPath("errors",  hasSize( 3 ) ) );
	}
	
	
}
