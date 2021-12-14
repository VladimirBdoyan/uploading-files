package com.example.fileuploader.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/group1")
    @PreAuthorize("hasRole('ROLE_group1')")
    public String group1(Principal principal){
        return "Hello Group1 users " + principal.getName();
    }
    @GetMapping("/group2")
    @PreAuthorize("hasRole('ROLE_group2')")
    public String group2(){
        return "Hello Group2 users ";
    }

    @GetMapping()
    public String hello(){
        return "Hello from security app with Azure AD";
    }
}
