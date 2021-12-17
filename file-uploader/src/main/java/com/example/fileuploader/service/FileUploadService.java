package com.example.fileuploader.service;

import com.example.fileuploader.dto.FileUploadResponseDTO;
import com.example.fileuploader.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileUploadService {
    void saveUploadedFile(MultipartFile file) throws IOException;
    List<FileUploadResponseDTO> search(String keyword);
    Optional<FileEntity> getFileById (Long id);
    List<FileEntity> getAllFiles();

}
