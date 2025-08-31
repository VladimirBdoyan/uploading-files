package com.example.fileuploader.util;

import com.example.fileuploader.dto.FileUploadResponseDTO;
import com.example.fileuploader.entity.FileEntity;
import com.example.fileuploader.entity.User;

import java.util.ArrayList;
import java.util.List;

public final class UploadedFileMapper {

    private UploadedFileMapper() {
    }

    public static List<FileUploadResponseDTO> mapToDTOs(List<FileEntity> entities){
        List<FileUploadResponseDTO> dtos = new ArrayList<>();
        for (FileEntity entity : entities) {
            dtos.add(mapToDTO(entity));
        }


        return dtos;


    }


    public static FileUploadResponseDTO mapToDTO(FileEntity entity){
        FileUploadResponseDTO dto = new FileUploadResponseDTO();
        dto.setId(entity.getId());
        dto.setFileName(entity.getName());
        dto.setFileSize(entity.getFileSize());
        dto.setUploadingDate(entity.getCreatedAt());
//        dto.setUserName(entity.getUser().getName());
        return dto;
    }
}
