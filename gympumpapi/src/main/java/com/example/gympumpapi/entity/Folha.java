package com.example.gympumpapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Folha {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_user;
    @Lob
    private String folha;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getIdUser(){
        return id_user;
    }

    public void setIdUser(Long id_user){
        this.id_user = id_user;
    }
    public String getFolha(){
        return folha;
    }

    public void setFolha(String folha){
        this.folha = folha;
    }

}
