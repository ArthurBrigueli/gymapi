package com.example.gympumpapi.service;

import org.springframework.stereotype.Service;

import com.example.gympumpapi.entity.Folha;
import com.example.gympumpapi.repository.FolhaRepository;
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
    
    public List<Folha> getAllFolhasById(Long idUser){

        List<Folha> folhaOpt = folhaRepository.findByIdUser(idUser);

        return folhaOpt;
        

    }

}
