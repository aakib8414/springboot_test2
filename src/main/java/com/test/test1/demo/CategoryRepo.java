package com.test.test1.demo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category, String> {
}
