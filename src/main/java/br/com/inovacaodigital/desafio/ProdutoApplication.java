package br.com.inovacaodigital.desafio;

import br.com.inovacaodigital.desafio.repository.ProdutoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProdutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoApplication.class, args);
	}

	@Bean
	public ProdutoRepository repository() {
		return new ProdutoRepository();
	}

}
