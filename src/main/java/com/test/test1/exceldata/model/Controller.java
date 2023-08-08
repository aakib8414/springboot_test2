package com.test.test1.exceldata.model;

import com.test.test1.exceldata.model.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class Controller {

    @Autowired
    private UserService service;

    @PostMapping("user/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (Helper.isExcel(file)) {
            service.saveAll(file);
            return ResponseEntity.ok(Map.of("message", "Uploaded successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Need excel file format");
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserExcel>> getAllUser() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("user/delete")
    public ResponseEntity<?> deleteAll() throws IOException {
        service.deletedAll();
        return ResponseEntity.ok(Map.of("message", "deleted successfully"));
    }
}