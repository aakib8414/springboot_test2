package com.test.test1.jpamappings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;
    public Student add (Student student){
        Student save = repo.save(student);
        return save;
    }

    public List<Student> getAll(){
        return repo.findAll();
    }

    public void delete(int id){
        repo.deleteById(id);
    }
}
