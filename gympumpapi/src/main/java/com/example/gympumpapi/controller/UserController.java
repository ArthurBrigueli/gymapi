package com.example.gympumpapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gympumpapi.DTO.LoginRequest;
import com.example.gympumpapi.entity.User;
import com.example.gympumpapi.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
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
    public String loginUser(@RequestBody LoginRequest loginRequest){
        boolean isAuthenticated = userService.loginUser(loginRequest.getName(), loginRequest.getPassword());

        if(isAuthenticated){
            return "Usuario logado";
        }else{
            return "Usuario incorreto";
        }
    }


    @PostMapping("/forgotpassword/{email}")
    public String sendEmail(@PathVariable String email, @RequestBody String code){
        return userService.forgotPassword(email, code);
    }

    @PostMapping("/forgotpassword/newpassword/{email}")
    public String forgotPassword(@PathVariable String email, @RequestBody String password){
        return userService.newPassword(email, password);
    }





}
