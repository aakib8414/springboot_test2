package com.test.test1;

import com.test.test1.demo.CategoryRepo;
import com.test.test1.demo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Test1Application {

    public static void main(String[] args) {

        SpringApplication.run(Test1Application.class, args);

    }

}
