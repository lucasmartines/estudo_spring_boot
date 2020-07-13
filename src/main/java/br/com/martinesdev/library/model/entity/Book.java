package br.com.martinesdev.library.model.entity;

 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity // preciso ser decorado com entity assim o jpa sabe que vou parar no banco de dados
@Table // sou uma table chamado book mas posso receber o nome que quiser
public class Book {
	
	@Id
	@Column // sou obrigatório no id segundo o curso
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column
	private String title;
	@Column
	private String author;
	@Column
	private String isbn;
}
