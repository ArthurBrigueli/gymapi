package com.example.gympumpapi.DTO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExercicioRequest {
    
    private Long id;
    private String exercicios;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public JsonNode getExercicios(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(exercicios);
        } catch (Exception e) {
            // Trate a exceção adequadamente, ou retorne null em caso de erro
            return null;
        }
    }

    public void setExercicios(JsonNode exercicios){
        this.exercicios = exercicios.toString();
    }

}
