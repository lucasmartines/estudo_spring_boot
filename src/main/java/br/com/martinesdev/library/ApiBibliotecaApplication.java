package br.com.martinesdev.library;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiBibliotecaApplication {

	@Bean
	/*cria uma maneira de permitir ao spring boot saber que quando um ModelMapper de nome
	 * model for requisitado por injeção de dependencia então instanciar esse model abaixo*/
	public ModelMapper model() {
		
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(ApiBibliotecaApplication.class, args);
	}

}
