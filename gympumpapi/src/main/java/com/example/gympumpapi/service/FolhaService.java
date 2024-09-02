package com.example.gympumpapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.gympumpapi.entity.Folha;
import com.example.gympumpapi.repository.FolhaRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class FolhaService {

    FolhaRepository folhaRepository;

    public FolhaService(FolhaRepository folhaRepository){
        this.folhaRepository = folhaRepository;
    }

    public List<Folha> getAllFolha(){
        return folhaRepository.findAll();
    }


    public List<Folha> createFolha(Folha folha){
        folhaRepository.save(folha);
        return getAllFolha();
    }

    public String deleteFolha(Long id){
        folhaRepository.deleteById(id);
        return "Deletado";
    }
    
    public Folha getAllFolhasById(Long idUser){
        return folhaRepository.findByIdUser(idUser);
        

    }


    @Transactional
    public ResponseEntity deleteFolhaByIdUser(Long idUser){
        folhaRepository.deleteByIdUser(idUser);
        return ResponseEntity.ok().build();
    }

}
