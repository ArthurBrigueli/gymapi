package com.example.gympumpapi.DTO;

public class YourFriendsRequest {


    private Long senderId;
    private Long receiverId;

    public Long getSenderId(){
        return senderId;
    }

    public void setSenderId(Long senderId){
        this.senderId = senderId;
    }

    public Long getReceiverId(){
        return receiverId;
    }
    

    public void setReceiverId(Long receiverId){
        this.receiverId = receiverId;
    }
}
