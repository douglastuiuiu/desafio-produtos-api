package br.com.inovacaodigital.desafio.endpoint;

import br.com.inovacaodigital.desafio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoEndpoint {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping(path = "/produtos")
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(repository.getProdutos(), HttpStatus.OK);
    }

}
