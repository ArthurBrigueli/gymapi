package com.example.gympumpapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gympumpapi.DTO.LoginRequest;
import com.example.gympumpapi.DTO.LoginResponse;
import com.example.gympumpapi.DTO.PasswordResponse;
import com.example.gympumpapi.DTO.RegisterRequest;
import com.example.gympumpapi.DTO.TokenPersistenceDTO;
import com.example.gympumpapi.DTO.UserDTO;
import com.example.gympumpapi.DTO.VerificationPasswordRequest;
import com.example.gympumpapi.configSecurity.TokenService;
import com.example.gympumpapi.entity.User;
import com.example.gympumpapi.repository.UserRepository;
import com.example.gympumpapi.service.UserService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    UserService userService;
    UserRepository userRepository;
    private final PasswordEncoder encoder;
    TokenService tokenService;
    

    public UserController(UserService userService, UserRepository userRepository, PasswordEncoder encoder, TokenService tokenService){
        this.userService = userService;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.tokenService = tokenService;
    }


    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        return userService.deleteUserById(id);
    }


    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginRequest loginRequest){

        User user = this.userRepository.findByName(loginRequest.getName()).orElseThrow(()-> new RuntimeException("User Not found"));
        if(encoder.matches(loginRequest.getPassword(), user.getPassword())){
            String token = tokenService.generateToken(user);

            UserDTO userDto = new UserDTO(user.getId(), user.getName(), user.getEmail());

            LoginResponse loginResponse = new LoginResponse(token, userDto);

            return ResponseEntity.ok(loginResponse);
        }

        return ResponseEntity.badRequest().build();

    }


    @PostMapping("/forgotpassword/{email}")
    public String sendEmail(@PathVariable String email, @RequestBody String code){
        return userService.forgotPassword(email, code);
    }

    @PostMapping("/forgotpassword/newpassword/{email}")
    public String forgotPassword(@PathVariable String email, @RequestBody PasswordResponse passwordResponse){
        Optional<User> user = this.userRepository.findByEmail(email);


        if(user.isPresent()){
            User newUser = user.get();
            System.out.println(passwordResponse.password());
            newUser.setPassword(encoder.encode(passwordResponse.password()));
            this.userRepository.save(newUser);
            return "Alterado";
        }
        return "quack";
    }


    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RegisterRequest body){
        Optional<User> user = this.userRepository.findByEmail(body.email());

        if(user.isEmpty()){
            User newUser = new User();
            newUser.setPassword(encoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            this.userRepository.save(newUser);

            return ResponseEntity.ok().build();
        }


        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/persistence")
    public ResponseEntity persistenceLogin(@RequestBody TokenPersistenceDTO tokenPersistenceDTO){
        return userService.persistenceLogin(tokenPersistenceDTO.getToken());
    }




    @PostMapping("/verification/password")
    public ResponseEntity verificationPassword(@RequestBody VerificationPasswordRequest verificationPasswordRequest){
        return userService.verificationPassword(verificationPasswordRequest.getEmail(), verificationPasswordRequest.getPassword());
    }





}
