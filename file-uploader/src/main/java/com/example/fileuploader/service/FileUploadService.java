package com.example.fileuploader.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    void saveUploadedFile(MultipartFile file) throws IOException;
}
