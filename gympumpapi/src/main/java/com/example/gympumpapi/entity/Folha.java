package com.example.gympumpapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Column;

@Entity
public class Folha {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id_user")
    private Long idUser;
    @Lob
    private String folha;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getIdUser(){
        return idUser;
    }

    public void setIdUser(Long idUser){
        this.idUser = idUser;
    }
    public String getFolha(){
        return folha;
    }

    public void setFolha(String folha){
        this.folha = folha;
    }

}
