package com.example.gympumpapi.controller;


import com.example.gympumpapi.DTO.UserSearchDTO;
import com.example.gympumpapi.entity.Friend;
import com.example.gympumpapi.repository.FriendRespository;
import com.example.gympumpapi.service.FriendService;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/friends")
public class FriendController {

    FriendService friendService;
    FriendRespository friendRespository;

    public FriendController(FriendService friendService, FriendRespository friendRespository){
        this.friendService = friendService;
        this.friendRespository = friendRespository;
    }


    @PostMapping("/envited")
    public List<UserSearchDTO> sendEnvitedFriend(@RequestBody Friend friend){
        return friendService.sendInvitedFriend(friend);
    }


    @GetMapping
    public List<Friend> getAll(){
        return friendRespository.findAll();
    }







}
