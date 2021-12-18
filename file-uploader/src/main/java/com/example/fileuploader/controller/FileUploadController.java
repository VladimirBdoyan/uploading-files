package com.example.fileuploader.controller;

import java.security.Principal;
import java.util.List;

import com.example.fileuploader.dto.FileUploadResponseDTO;
import com.example.fileuploader.entity.FileEntity;
import com.example.fileuploader.service.FileUploadServiceImpl;
import com.example.fileuploader.service.UserServiceImpl;
import com.example.fileuploader.util.UploadedFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/files")
public class FileUploadController {

    private final FileUploadServiceImpl fileUploadService;
    private final UserServiceImpl userService;

    @Autowired
    public FileUploadController(FileUploadServiceImpl fileUploadService, UserServiceImpl userService) {
        this.fileUploadService = fileUploadService;
        this.userService = userService;
    }

    @GetMapping()
    public String welcome(Principal principal) {
        userService.saveUser(principal);
        return "redirect:/files/home";
    }

    @GetMapping("/home")
    public ModelAndView listUploadedFiles1(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uploadForm");

        List<FileUploadResponseDTO> filesDTOs = UploadedFileMapper.mapToDTOs(fileUploadService.getAllFiles());
        model.addAttribute("filesDTOs", filesDTOs);

        return modelAndView;
    }

    @PostMapping("/upload")
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
        return "redirect:/files/home";
    }

    @GetMapping("/search/{keyword}")
    public ModelAndView search(@PathVariable("keyword") String keyword, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("downloadForm");
        //TODO must handle not found exception
        List<FileUploadResponseDTO> findDTOs = fileUploadService.search(keyword);
        model.addAttribute("findDTOs", findDTOs);
        return modelAndView;
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
        FileEntity fileEntity = fileUploadService.getFileById(id).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .body(new ByteArrayResource(fileEntity.getData()));
    }
}


