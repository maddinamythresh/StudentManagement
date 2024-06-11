package com.example.StudentManagement.StudentManagement.service;

import com.example.StudentManagement.StudentManagement.Model.User;
import com.example.StudentManagement.StudentManagement.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class myService {
    @Autowired
    private UserRepository repo;

    public void save(User user) {
        repo.save(user);
    }
}
