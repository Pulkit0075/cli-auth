package com.practice.auth_cli.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("User not found")
        );
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> 
            new RuntimeException("User not found")
        );
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> 
            new RuntimeException("User not found")
        );
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    
}
