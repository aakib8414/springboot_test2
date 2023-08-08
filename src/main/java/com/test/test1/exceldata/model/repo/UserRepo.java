package com.test.test1.exceldata.model.repo;

import com.test.test1.exceldata.model.UserExcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserExcel,Integer> {
}
