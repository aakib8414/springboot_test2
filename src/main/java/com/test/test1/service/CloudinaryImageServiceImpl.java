package com.test.test1.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryImageServiceImpl implements CloudinaryImageService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map upload(MultipartFile file) {
        try {
            Map map = cloudinary.uploader().upload(file.getBytes(), Map.of());
            String url = (String) map.get("url");
            System.out.println("uploaded image url is :"+url);
            return map;
        } catch (IOException e) {
            throw new RuntimeException("Image Upload Failed");
        }
    }
}
