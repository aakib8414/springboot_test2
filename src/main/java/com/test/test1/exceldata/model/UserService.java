package com.test.test1.exceldata.model;

import com.test.test1.exceldata.model.helper.DbToExcelHelper;
import com.test.test1.exceldata.model.helper.Helper;
import com.test.test1.exceldata.model.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @CacheEvict(value = "userList", allEntries = true)
    public List<UserExcel> saveAll(MultipartFile file) throws IOException {
        List<UserExcel> userList = Helper.convert(file.getInputStream());
        return userRepo.saveAll(userList);
    }

    @Cacheable(value = "userList")
    public List<UserExcel> getAll() {
        return userRepo.findAll();
    }

    @CacheEvict(value = "userList", allEntries = true)
    public boolean deletedAll() {
        if (userRepo.findAll().size() > 0) {
            userRepo.deleteAll();
            System.out.println("Data deleted succssfully from table");
            return true;
        } else {
            System.out.println("Table Already is empty");
            return false;
        }

    }

    public ByteArrayInputStream getDataFromDb() throws IOException {
        List<UserExcel> list = userRepo.findAll();
        return DbToExcelHelper.dataToExcel(list);
    }
}
