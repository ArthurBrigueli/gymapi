package com.example.gympumpapi.DTO;

public class ExercicioRequest {
    private Long id;
    private String exercicios;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getExercicios(){
        return exercicios;
    }

    public void setExercicios(String exercicios){
        this.exercicios = exercicios;
    }

}
