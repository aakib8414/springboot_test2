package com.test.test1.service.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class FileResponse {
    String fileName;
    String message;
}
