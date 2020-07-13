package br.com.martinesdev.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


// to create a bean that will be injected you shoud anotate this class with @Configuration
// then Sprint will know that this is a Injector container configuration

@EnableSwagger2
@Configuration 
public class SwaggerConfig {
	
	@Bean 
	public Docket docket() {
		return new Docket( DocumentationType.SWAGGER_2 )
			.select()
			.apis( RequestHandlerSelectors.basePackage("br.com.martinesdev.library.api.resource") )
			.paths( PathSelectors.any() )
			.build(   )
			.apiInfo( apiInfo() );
		
		
	}
	
	
	
	/* just some configurations */
	private Contact contact() {
		return new Contact(
				"Lucas Martines",
				"https://github.com/lucasmartines",
				"lucas.alcantarilla97@gmail.com");
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Library API")
				.description("Api do projeto de controle de api de livros")
				.version("1.0")
				.contact(contact())
				.build();
				
	}
}
