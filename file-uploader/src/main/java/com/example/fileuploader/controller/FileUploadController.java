package com.example.fileuploader.controller;

import java.io.IOException;
import java.util.List;

import com.example.fileuploader.dto.FileUploadResponseDTO;
import com.example.fileuploader.entity.FileEntity;
import com.example.fileuploader.service.FileUploadServiceImpl;
import com.example.fileuploader.util.UploadedFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class FileUploadController {

    private final FileUploadServiceImpl fileUploadService;

    @Autowired
    public FileUploadController(FileUploadServiceImpl fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/")
    public String listUploadedFiles1(Model model) throws IOException {

        List<FileUploadResponseDTO> filesDTOs = UploadedFileMapper.mapToDTOs(fileUploadService.getAllFiles());
        model.addAttribute("filesDTOs", filesDTOs);

        return "uploadForm";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try {
            fileUploadService.saveUploadedFile(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
        } catch (Exception e) {
            //TODO change response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("Could not upload the file: %s!", file.getOriginalFilename())).toString();
        }
        return "redirect:/";
    }

    @GetMapping("/search/{keyword}")
    public String search(@PathVariable("keyword") String keyword, Model model) {
        //TODO must handle not found exception
        List<FileUploadResponseDTO> findDTOs = fileUploadService.search(keyword);
        model.addAttribute("findDTOs", findDTOs);
        return "downloadForm";
    }
}


