package com.example.StudentManagement.StudentManagement.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Data
@Table(name="GradStudent")
public class User {

    @Id
    private String rollNo;
    @Column(unique = true)
    private String username;
    @Column(name = "Password")
    private String password;
    private String roles;

    public String getUsername() {
        return  this.username;
    }

    public String getPassword() {

        return  this.password;
    }

    public void setPassword(String encode) {
        this.password=encode;
    }

    public String getRoles() {
        return this.roles;
    }
}
