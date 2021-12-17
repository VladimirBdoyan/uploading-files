package com.example.fileuploader.service;

import com.example.fileuploader.dto.FileUploadResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {
    void saveUploadedFile(MultipartFile file) throws IOException;
    List<FileUploadResponseDTO> search(String keyword);
}
