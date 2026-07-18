/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spring.ai.example.spring_ai_demo.service;

import java.util.Objects;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import spring.ai.example.spring_ai_demo.CustomUserDetails;
import spring.ai.example.spring_ai_demo.entity.User;
import spring.ai.example.spring_ai_demo.repository.UserRepository;

/**
 *
 * @author nanua
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    
    public CustomUserDetailsService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username);
        if(Objects.isNull(user)){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomUserDetails(user);
// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
