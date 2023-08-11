package com.test.test1.service;

import com.cloudinary.Cloudinary;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudinaryImageService {

    public Map upload(MultipartFile file);
}
