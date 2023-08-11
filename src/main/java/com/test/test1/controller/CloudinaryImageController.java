package com.test.test1.controller;

import com.test.test1.service.CloudinaryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/cloudinary/upload")
public class CloudinaryImageController {

    @Autowired
    private CloudinaryImageService imageService;

    @PostMapping
    public ResponseEntity<Map> uploadImage(@RequestParam("image") MultipartFile file) {
        Map map = this.imageService.upload(file);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
