package br.com.inovacaodigital.desafio.repository;

import br.com.inovacaodigital.desafio.bean.Produto;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private List<Produto> produtos;

    public List<Produto> getProdutos() {
        return produtos;
    }

    @PostConstruct
    private void init() {
        produtos = new ArrayList<>();
        produtos.add(new Produto("123123123","TV","Eletro", "LG"));
        produtos.add(new Produto("234234234","Geladeira","Eletro", "Samsung"));
        produtos.add(new Produto("345345345","Fogão","Eletro", "Brastemp"));
        produtos.add(new Produto("456456456","Microondas","Eletro", "Philco"));
        produtos.add(new Produto("567567567","Ar-condicionado","Eletro", "Consul"));
        produtos.add(new Produto("678678678","Notebook","Eletro", "Apple"));
    }
}
