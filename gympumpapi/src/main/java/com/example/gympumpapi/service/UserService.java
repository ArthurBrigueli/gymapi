package com.example.gympumpapi.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.gympumpapi.entity.User;
import com.example.gympumpapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserService {
    
    UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User registerUser(User user){

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User user){

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String deleteUserById(Long id){
        userRepository.deleteById(id);
        return "Deletado";
    }


    public Boolean loginUser(String name, String password) {
        Optional<User> optionalUser = userRepository.findByName(name);
        System.out.println(optionalUser);
    
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return encoder.matches(password, user.getPassword());
        }
    
        return false;
    }
    



}
