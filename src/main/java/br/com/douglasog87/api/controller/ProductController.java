package br.com.douglasog87.api.controller;

import br.com.douglasog87.api.repository.ProductRepository;
import br.com.douglasog87.event.DomainEvent;
import br.com.douglasog87.event.domain.Product;
import br.com.douglasog87.event.strategy.ProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/products")
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
        Optional<br.com.douglasog87.api.model.Product> product = repository.findById(id);
        if (!product.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody br.com.douglasog87.api.model.Product product) {
        product.setCreatedAt(LocalDateTime.now());

        DomainEvent event = ProductEvent.CREATE.newInstance(Product.parseProduct(product));
        product = repository.save(product);
        applicationEventPublisher.publishEvent(event);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody br.com.douglasog87.api.model.Product product) {
        Optional<br.com.douglasog87.api.model.Product> productBD = repository.findById(id);
        product.setId(id);
        if (productBD.isPresent()) {
            product.setUpdatedAt(LocalDateTime.now());
            product.setCreatedAt(productBD.get().getCreatedAt());
        }

        DomainEvent event = ProductEvent.UPDATE.newInstance(Product.parseProduct(product));
        product = repository.save(product);
        applicationEventPublisher.publishEvent(event);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        Optional<br.com.douglasog87.api.model.Product> productBD = repository.findById(id);
        if (productBD.isPresent()) {
            DomainEvent event = ProductEvent.DELETE.newInstance(Product.parseProduct(productBD.get()));
            applicationEventPublisher.publishEvent(event);
            repository.deleteById(id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
