package com.example.StudentManagement.StudentManagement.Contoller;

import com.example.StudentManagement.StudentManagement.Model.User;
//import com.example.StudentManagement.StudentManagement.service.JwtService;
import com.example.StudentManagement.StudentManagement.service.JwtService;
import com.example.StudentManagement.StudentManagement.service.myService;
import org.antlr.v4.runtime.misc.LogManager;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class userController {
    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    @Autowired
    private myService service;

    @Autowired
    private JwtService jwtService;

    private String secretToken;

    @Autowired
    private AuthenticationManager authmanger;

    @GetMapping("/")
    public String home(){
        return "Hello";
    }

    @PostMapping("/alogin")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication = authmanger.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(user.getUsername());
                secretToken=token;
                return ResponseEntity.ok(token +"HelloWorld"+ authentication.getAuthorities());
            } else {
                return ResponseEntity.status(401).body("Login Failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Login Failed: " + e.getMessage());
        }
    }
    @GetMapping("/confirmrole")
    public ResponseEntity<?> confirm(){


        return ResponseEntity.ok(secretToken);

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    public String register(@RequestBody  User user){
        user.setPassword(encoder.encode(user.getPassword()));
        service.save(user);
        return "Success";
    }
}
