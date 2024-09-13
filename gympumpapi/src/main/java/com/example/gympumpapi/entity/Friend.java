package com.example.gympumpapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.example.gympumpapi.repository.Status;


@Entity
public class Friend {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long senderId;
    private String nameSender;
    private Long receiverId;
    private String nameReceiver;
    private Status status = Status.PENDING;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getSenderId(){
        return senderId;
    }

    public void setSenderId(Long senderId){
        this.senderId = senderId;
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

    public Long getReceiverId(){
        return receiverId;
    }

    public void setReceiverId(Long receiverId){
        this.receiverId = receiverId;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }


}
