package com.example.fileuploader.service;

import java.security.Principal;

public interface UserService {
    Long saveUser(Principal principal);
}
