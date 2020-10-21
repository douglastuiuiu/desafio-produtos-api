package br.com.douglasog87.products.api.repository;

import br.com.douglasog87.products.api.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

}
