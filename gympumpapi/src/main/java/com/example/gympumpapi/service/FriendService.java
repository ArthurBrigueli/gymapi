package com.example.gympumpapi.service;


import com.example.gympumpapi.DTO.UserSearchDTO;
import com.example.gympumpapi.entity.Friend;
import com.example.gympumpapi.repository.FriendRespository;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class FriendService {

    FriendRespository friendRespository;
    UserService userService;

    public FriendService(FriendRespository friendRespository, UserService userService){
        this.friendRespository = friendRespository;
        this.userService = userService;
    }


    public List<UserSearchDTO> sendInvitedFriend(Friend friend){
        friendRespository.save(friend);
        return userService.findUsersByName(friend.getNameSender());
    }






}
