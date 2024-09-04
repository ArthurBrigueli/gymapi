package com.example.gympumpapi.controller;

import com.example.gympumpapi.entity.Folha;
import com.example.gympumpapi.service.FolhaService;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/folhas")
@RestController
public class FolhaController {
    
    FolhaService folhaService;

    public FolhaController(FolhaService folhaService){
        this.folhaService = folhaService;
    }


    @GetMapping
    public List<Folha> getAllFolha(){
        return folhaService.getAllFolha();
    }


    @PostMapping("/folha/create")
    public List<Folha> createFolha(@RequestBody Folha folha){
        return folhaService.createFolha(folha);
    }

    @DeleteMapping("/folha/delete/{id}")
    public String deleteFolha(@PathVariable Long id){
        return folhaService.deleteFolha(id);
    }


    @GetMapping("/user/{idUser}")
    public Folha getAllFolhasById(@PathVariable Long idUser){
        return folhaService.getAllFolhasById(idUser);
    }


    @DeleteMapping("/folha/delete/user/{idUser}")
    public ResponseEntity deleteFolhaByIdUser(@PathVariable Long idUser){
        return folhaService.deleteFolhaByIdUser(idUser);
    }

}
