package com.example.gympumpapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.gympumpapi.ResourceNotFoundException;
import com.example.gympumpapi.entity.Treino;
import com.example.gympumpapi.repository.TreinoRepository;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.transaction.Transactional;

import java.util.Optional;

import java.util.List;

@Service
public class TreinoService {
    
    TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository){
        this.treinoRepository = treinoRepository;
    }


    public List<Treino> getAllTreino(){
        return treinoRepository.findAll();
    }


    public Treino createTreino(Treino treino){
        return treinoRepository.save(treino);
        
    }

    public List<Treino> deleteTreino(Long id){
        treinoRepository.deleteById(id);
        return getAllTreino();
    }

    public String createExercicios(Long id, JsonNode exercicios){
        
        Optional<Treino> treinoOpt = treinoRepository.findById(id);

        if(treinoOpt.isPresent()){
            Treino treino = treinoOpt.get();
            treino.setExercicios(exercicios);
            treinoRepository.save(treino);
        }else{
            return "nao";
        }
        return "foi";
    }

    public List<Treino> updateTreino(Long id, Treino treino){

        Optional<Treino> treinoOpt = treinoRepository.findById(id);

        if(treinoOpt.isPresent()){
            Treino newTreino = treinoOpt.get();

            newTreino.setName(treino.getName());
            newTreino.setDate(treino.getDate());
            newTreino.setExercicios(treino.getExercicios());

            treinoRepository.save(newTreino);
        }

        return getAllTreino();
    }


    public Treino getAllById(Long id){

        Optional<Treino> treinoOpt = treinoRepository.findById(id);


        if(treinoOpt.isPresent()){
            return treinoOpt.get();
        }

        throw new RuntimeException("Treino não encontrado para o id: " + id);


    }

    public List<Treino> getAllTreinosByIdUser(Long idUser){
        return treinoRepository.findTreinoByIdUser(idUser);
    }


    public Treino getTreinoByIdAndIdUser(Long id, Long idUser){
        
        return treinoRepository.findTreinoByIdAndIdUser(id, idUser)
        .orElseThrow(() -> new ResourceNotFoundException("Treino não encontrada para o id " + id + " e usuário " + idUser));
        
        

    }



    @Transactional
    public ResponseEntity deleteByIdAndIdUser(Long id, Long idUser){

        treinoRepository.deleteByIdAndIdUser(id, idUser);

        return ResponseEntity.ok().build();


    }



    //temporario
    public String deleteAllTreino(){
        treinoRepository.deleteAll();
        return "deletado";
    }

}
