package com.example.gympumpapi.service;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.gympumpapi.DTO.TokenPersistenceDTO;
import com.example.gympumpapi.DTO.UserResponsePersistenceDTO;
import com.example.gympumpapi.configSecurity.TokenService;
import com.example.gympumpapi.entity.User;
import com.example.gympumpapi.repository.FolhaRepository;
import com.example.gympumpapi.repository.TreinoRepository;
import com.example.gympumpapi.repository.UserRepository;


import java.util.List;
import java.util.Optional;

@Repository
public class UserService {
    
    UserRepository userRepository;
    private final PasswordEncoder encoder;
    private EmailService emailService;
    FolhaRepository folhaRepository;
    TreinoRepository treinoRepository;
    TokenService tokenService;

    public UserService(UserRepository userRepository, PasswordEncoder encoder, EmailService emailService, FolhaRepository folhaRepository, TreinoRepository treinoRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.emailService = emailService;
        this.folhaRepository = folhaRepository;
        this.treinoRepository = treinoRepository;
        this.tokenService = tokenService;
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

    @Transactional
    public String deleteUserById(Long id){
        folhaRepository.deleteByIdUser(id);
        treinoRepository.deleteAllByIdUser(id);
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


    public ResponseEntity persistenceLogin(String token){
        String email = tokenService.validateToken(token);

        Optional<User> userOpt = userRepository.findByEmail(email);


        if(userOpt.isPresent()){
            User newUser = userOpt.get();
            UserResponsePersistenceDTO response = new UserResponsePersistenceDTO();
            response.setId(newUser.getId());
            response.setName(newUser.getName());
            response.setEmail(newUser.getEmail());

            return ResponseEntity.ok(response);

        }


        return ResponseEntity.badRequest().build();

    }


    public ResponseEntity verificationPassword(String email, String password){

        Optional<User> userOpt = userRepository.findByEmail(email);

        if(userOpt.isPresent()){
            User user = userOpt.get();

            if(encoder.matches(password, user.getPassword())){
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        }


        return ResponseEntity.badRequest().build();


        
    }
    



}
