package com.example.gympumpapi.service;

import org.springframework.stereotype.Service;

import com.example.gympumpapi.ResourceNotFoundException;
import com.example.gympumpapi.entity.Folha;
import com.example.gympumpapi.entity.Treino;
import com.example.gympumpapi.repository.TreinoRepository;
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


    public List<Treino> createTreino(Treino treino){
        treinoRepository.save(treino);
        return getAllTreino();
    }

    public List<Treino> deleteTreino(Long id){
        treinoRepository.deleteById(id);
        return getAllTreino();
    }

    public String createExercicios(Long id, String exercicios){
        
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

    public List<Treino> updateTreino(Treino treino){

        treinoRepository.save(treino);

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

}
