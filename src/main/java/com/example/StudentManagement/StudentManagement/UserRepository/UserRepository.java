package com.example.StudentManagement.StudentManagement.UserRepository;

import com.example.StudentManagement.StudentManagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User,String> {

    User findByUsername(String username);
}
