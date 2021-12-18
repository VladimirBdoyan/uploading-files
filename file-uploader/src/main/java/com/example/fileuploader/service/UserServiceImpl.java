package com.example.fileuploader.service;

import com.example.fileuploader.entity.User;
import com.example.fileuploader.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Long saveUser(Principal principal) {
        User user = new User();
        user.setName(principal.getName());
        user = userRepository.save(user);
        return user.getId();
    }
}
