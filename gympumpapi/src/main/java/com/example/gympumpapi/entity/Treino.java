package com.example.gympumpapi.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "id_user")
    private Long idUser;
    private String name;
    private String date;
    @Lob
    private String exercicios;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long id_user) {
        this.idUser = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public JsonNode getExercicios() {
       try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(exercicios);
        } catch (Exception e) {
            // Trate a exceção adequadamente, ou retorne null em caso de erro
            return null;
        }
    }

    public void setExercicios(JsonNode exercicios) {
        this.exercicios = exercicios.toString();
    }



}
