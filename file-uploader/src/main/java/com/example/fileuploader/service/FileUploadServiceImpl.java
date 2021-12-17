package com.example.fileuploader.service;

import com.example.fileuploader.dto.FileUploadResponseDTO;
import com.example.fileuploader.entity.FileEntity;
import com.example.fileuploader.repository.FileRepository;
import com.example.fileuploader.util.UploadedFileMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private final FileRepository fileRepository;


    public FileUploadServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    @Override
    public void saveUploadedFile(MultipartFile file) throws IOException {
        Date createdAt = new Date();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setFileSize(file.getSize());
        fileEntity.setCreatedAt(createdAt);

        fileEntity = fileRepository.save(fileEntity);
        System.out.println(fileEntity);
    }

    @Override
    public List<FileUploadResponseDTO> search(String keyword) {
        List<FileUploadResponseDTO> filesDTOs;
        List<FileEntity> fileEntities = new ArrayList<>();
        fileRepository.findAllByNameContains(keyword)
                .forEach(fileEntities::add);
        filesDTOs = UploadedFileMapper.mapToDTOs(fileEntities);
        return filesDTOs;
    }

    public List<FileEntity> getAllFiles(){
        return fileRepository.findAll();
    }


}
