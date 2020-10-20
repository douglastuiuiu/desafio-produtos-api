package br.com.douglasog87.products.api.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

import br.com.douglasog87.commonsevent.event.DomainEvent;
import br.com.douglasog87.products.api.model.Product;
import br.com.douglasog87.products.api.repository.ProductRepository;
import br.com.douglasog87.products.event.strategy.ProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

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

        DomainEvent event = ProductEvent.CREATE.newInstance(br.com.douglasog87.products.event.domain.Product.parseProduct(product));
        product = repository.save(product);
        applicationEventPublisher.publishEvent(event);

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

        DomainEvent event = ProductEvent.UPDATE.newInstance(br.com.douglasog87.products.event.domain.Product.parseProduct(product));
        product = repository.save(product);
        applicationEventPublisher.publishEvent(event);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        Optional<Product> productBD = repository.findById(id);
        if(productBD.isPresent()) {
            DomainEvent event = ProductEvent.DELETE.newInstance(br.com.douglasog87.products.event.domain.Product.parseProduct(productBD.get()));
            applicationEventPublisher.publishEvent(event);
            repository.deleteById(id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
