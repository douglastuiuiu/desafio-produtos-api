package br.com.douglasog87.controller;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.douglasog87.model.Product;
import br.com.douglasog87.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") String id) {
        Optional<Product> product = repository.findById(id);
        if(!product.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product = repository.save(product);
        //send insert msg
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody Product product) {
        Optional<Product> productBD = repository.findById(id);
        product.setId(id);
        if(productBD.isPresent()) {
            product.setUpdatedAt(LocalDateTime.now());
            product.setCreatedAt(productBD.get().getCreatedAt());
        }

        product = repository.save(product);
        //send update msg
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        repository.deleteById(id);
        //send delete msg
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
