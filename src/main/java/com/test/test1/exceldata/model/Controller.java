package com.test.test1.exceldata.model;

import com.test.test1.exceldata.model.helper.Helper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping(path = "/user") //, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE )
    public ResponseEntity<List<UserExcel>> getAllUser() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("user/delete")
    public ResponseEntity<?> deleteAll() throws IOException {
        service.deletedAll();
        return ResponseEntity.ok(Map.of("message", "deleted successfully"));
    }

    @GetMapping("/users/export/excel")
    public ResponseEntity<InputStreamResource> exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        ByteArrayInputStream dataFromDbStream = service.getDataFromDb();
        InputStreamResource inputStreamResource = new InputStreamResource(dataFromDbStream);
  return       ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users_" + currentDateTime + ".xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(inputStreamResource);
    }
}