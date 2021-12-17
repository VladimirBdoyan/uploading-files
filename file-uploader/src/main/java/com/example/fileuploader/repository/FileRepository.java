package com.example.fileuploader.repository;

import com.example.fileuploader.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long>, JpaSpecificationExecutor<FileEntity> {
    public List<FileEntity> getAllByCreatedAtLessThanEqual(Date createdAt);
    Iterable<FileEntity> findAllByNameContains(String key);
}
