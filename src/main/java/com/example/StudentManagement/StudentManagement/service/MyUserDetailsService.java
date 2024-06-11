package com.example.StudentManagement.StudentManagement.service;

import com.example.StudentManagement.StudentManagement.Model.User;
import com.example.StudentManagement.StudentManagement.Model.UserPrincipal;
import com.example.StudentManagement.StudentManagement.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
     private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=repo.findByUsername(username);
        if(user==null){
            System.out.println("User404");
            throw  new UsernameNotFoundException("User not Found");
        }
        return new UserPrincipal(user);
    }
}
