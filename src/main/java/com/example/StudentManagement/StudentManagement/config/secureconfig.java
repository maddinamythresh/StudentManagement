package com.example.StudentManagement.StudentManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity

public class secureconfig {

    @Autowired
    private JwtFilter jwtFilter;


    @Autowired
     private UserDetailsService usedetailsService;




    @Bean
    public AuthenticationProvider authProvider(){
        System.out.println("Auth Provider");
        DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
        System.out.println("Set Details");
        dao.setUserDetailsService(usedetailsService);
        System.out.println("Encrypt");
        dao.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return dao;
    }

    @Bean
    public SecurityFilterChain securefilterchain(HttpSecurity http) throws Exception{

        System.out.println("Enter Filter Chain");
        http.csrf(customizer->customizer.disable());

        http.authorizeHttpRequests(request->request.requestMatchers("/alogin").permitAll().anyRequest().authenticated());
      // http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authmanger(AuthenticationConfiguration config) throws Exception {
        System.out.println("hello");
        return config.getAuthenticationManager();
    }


}
