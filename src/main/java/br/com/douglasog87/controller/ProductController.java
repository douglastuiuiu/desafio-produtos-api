package br.com.douglasog87.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.douglasog87.model.Product;
import br.com.douglasog87.repository.ProductRepository;

@RestController(value = "/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findOne(@RequestParam String id) {
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
    public ResponseEntity<?> update(@RequestParam String id, @Valid @RequestBody Product product) {
        product.setUpdatedAt(LocalDateTime.now());
        product = repository.save(product);
        //send update msg
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@RequestParam String id) {
        repository.deleteById(id);
        //send delete msg
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
