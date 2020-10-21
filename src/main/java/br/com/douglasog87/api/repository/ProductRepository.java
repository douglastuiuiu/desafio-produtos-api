package br.com.douglasog87.api.repository;

import br.com.douglasog87.api.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

}
