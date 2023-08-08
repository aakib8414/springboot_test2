package com.test.test1.controller;

import com.test.test1.service.file.FileResponse;
import com.test.test1.service.file.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam("image")MultipartFile image)  {
        String fileName = null;
        try {
            fileName = fileService.uploadImage(path, image);
        } catch (IOException e) {
            return new ResponseEntity<>(new FileResponse(fileName,"Image uploaded failed due to internal errors"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(fileName,"Uploaded successfully"), HttpStatus.OK);
    }

    //method serve image
    @GetMapping(value = "profiles/{imageName}" ,produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadFile(@PathVariable("imageName") String imgName, HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path, imgName);
           response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
