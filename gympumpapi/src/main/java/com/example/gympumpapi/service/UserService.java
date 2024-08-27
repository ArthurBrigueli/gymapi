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
    private EmailService emailService;

    public UserService(UserRepository userRepository, PasswordEncoder encoder, EmailService emailService){
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.emailService = emailService;
        
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

    public String forgotPassword(String email, String code){
        
        Optional<User> userOpt = userRepository.findByEmail(email);
        String subject = "Seu codigo para trocar senha";

        if(userOpt.isPresent()){
            emailService.sendSimpleEmail(email, subject, code);
            return code;
        }else{
            return "Usuario nao encontrado";
        }

    }


    public String newPassword(String email, String password){
        Optional<User> emailOpt = userRepository.findByEmail(email);
        
        if(emailOpt.isPresent()){
            User user = emailOpt.get();
            String encodePassword = encoder.encode(password);
            user.setPassword(encodePassword);
            userRepository.save(user);
            return "Senha alterada";

        }else{
            return "Usuario nao encontrado";
        }
    }
    



}
