package com.test.test1.exceldata.model.repo;

import com.test.test1.exceldata.model.UserExcel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserExcel, Integer> {

    @Procedure(value = "get_priority_profession")
    List<UserExcel> getUserByProfession(String Pname);
}
