package br.com.douglasog87.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.douglasog87.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    
}
