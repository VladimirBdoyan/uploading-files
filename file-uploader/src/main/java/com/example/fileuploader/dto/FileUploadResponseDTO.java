package com.example.fileuploader.dto;

import java.util.Date;

public class FileUploadResponseDTO {
    private Long id;
    private String fileName;
    private Long fileSize;
    private Date uploadingDate;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getUploadingDate() {
        return uploadingDate;
    }

    public void setUploadingDate(Date uploadingDate) {
        this.uploadingDate = uploadingDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return
                "fileName = " + fileName + '\'' +
                        ", File Size = " + fileSize +" byte" +
                        ", Uploading Date = " + uploadingDate;
    }
}
