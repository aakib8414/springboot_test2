package com.test.test1;

import com.test.test1.demo.CategoryRepo;
import com.test.test1.demo.ProductRepo;
import com.test.test1.jpamappings.Laptop;
import com.test.test1.jpamappings.Student;
import com.test.test1.jpamappings.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Optional;

@EnableCaching
@SpringBootApplication
public class Test1Application implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Test1Application.class);
    @Autowired
    private StudentRepo repo;

    public static void main(String[] args) {

        SpringApplication.run(Test1Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        Student student = new Student();
        student.setId(31);
        student.setName("Aadil");

        Laptop laptop = new Laptop();
        laptop.setId(1);
        laptop.setName("Lenovo");
        laptop.setStudent(student);

        student.setLaptop(laptop);
//        Student saved = repo.save(student);
//        logger.info("Saved Student :{}",saved);

//       Student studentdb= repo.findById(31).get();
//       logger.info("LaptopName is{}",studentdb.getLaptop().getName());
    }
}
