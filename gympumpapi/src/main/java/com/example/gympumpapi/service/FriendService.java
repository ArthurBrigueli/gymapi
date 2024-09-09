package com.example.gympumpapi.service;


import com.example.gympumpapi.entity.Friend;
import com.example.gympumpapi.repository.FriendRespository;
import org.springframework.stereotype.Service;


@Service
public class FriendService {

    FriendRespository friendRespository;

    public FriendService(FriendRespository friendRespository){
        this.friendRespository = friendRespository;
    }


    public String sendInvitedFriend(Friend friend){
        friendRespository.save(friend);
        return "enviado";
    }






}
