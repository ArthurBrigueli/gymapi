package com.example.gympumpapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Folha {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id_user")
    private Long idUser;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String folha;

    // Getters and setters
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

    public JsonNode getFolha() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(folha);
        } catch (Exception e) {
            // Trate a exceção adequadamente, ou retorne null em caso de erro
            return null;
        }
    }

    public void setFolha(JsonNode folha) {
        this.folha = folha.toString();
    }
}
