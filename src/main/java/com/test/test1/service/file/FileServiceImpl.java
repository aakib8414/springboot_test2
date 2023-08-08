package com.test.test1.service.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService{
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();
        String filePath = path+ File.separator+name;
        File f = new File(path);
        if (!f.exists()){
            f.mkdirs();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return name;
    }

    @Override
    public InputStream getResource(String path, String imageName) throws FileNotFoundException {
        String qPath = path + File.separator + imageName;
        InputStream is = new FileInputStream(qPath);
        return is;
    }
}
