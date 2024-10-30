package br.com.livraria.livraria.springDoc;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguracao {
    @Bean
    public OpenAPI customOpenAPI() {
        // http://localhost:8080/swagger-ui/index.html
        return new OpenAPI()
                .info(new Info()
                        .title("Livraria API")
                        .description("API Rest da aplicação de uma Livraria, contendo as funcionalidades de CRUD de Livro e cadastro de Autor, Editora e Resumo de livro.")
                        .contact(new Contact()
                                .name("Time de desenvolvimento")
                                .email("backend@livraria.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://livraria/livro/licenca")));
    }
}

