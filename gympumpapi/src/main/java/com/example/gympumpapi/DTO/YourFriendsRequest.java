package com.example.gympumpapi.DTO;

public class YourFriendsRequest {


    private Long senderId;
    private String nameSender;
    private Long receiverId;
    private String nameReceiver;

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

    public String getNameSender(){
        return nameSender;
    }

    public void setNameSender(String nameSender){
        this.nameSender = nameSender;
    }

    public String getNameReceiver(){
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver){
        this.nameReceiver = nameReceiver;
    }
}
