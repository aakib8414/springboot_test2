package com.test.test1.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/demo")
public class ControllerDemo {

    @Autowired
    private  ProductRepo productRepo;
    @Autowired
    private  CategoryRepo categoryRepo;

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        final Category cat = categoryRepo.save(category);

        return new ResponseEntity(cat, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable String id) {
        final Category category = categoryRepo.findById(id).get();
       product();
        return new ResponseEntity(category, HttpStatus.OK);
    }

    public void product(){
        final Product p2 = productRepo.findById("p2").get();
        final List<Category> categories = p2.getCategories();
        log.debug("data is: {}",categories.get(0).getTitle());
    }
}
