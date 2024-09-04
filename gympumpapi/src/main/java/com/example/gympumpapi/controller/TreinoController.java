package com.example.gympumpapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gympumpapi.DTO.ExercicioRequest;
import com.example.gympumpapi.entity.Treino;
import com.example.gympumpapi.service.TreinoService;
import java.util.List;

@RequestMapping("/api/treinos")
@RestController
public class TreinoController {
    TreinoService treinoService;

    public TreinoController(TreinoService treinoService){
        this.treinoService = treinoService;
    }


    @GetMapping
    public List<Treino> getAllTreino(){
        return treinoService.getAllTreino();
    }


    @PostMapping("/treino/create")
    public Treino creatTreinos(@RequestBody Treino treino){

        return treinoService.createTreino(treino);
    }

    @PutMapping("/exercicios/add")
    public List<Treino> createExercicios(@RequestBody ExercicioRequest exercicioRequest){

        System.out.println(exercicioRequest.getId());
        System.out.println(exercicioRequest.getExercicios());

        treinoService.createExercicios(exercicioRequest.getId(), exercicioRequest.getExercicios());

        return getAllTreino();
    }

    @PutMapping("/treino/update/{id}")
    public List<Treino> updateTreino(@PathVariable Long id, @RequestBody Treino treino){
        treinoService.updateTreino(id, treino);
        return getAllTreino();
    }


    @DeleteMapping("/treino/delete/{id}")
    public List<Treino> deleteTreino(@PathVariable Long id){
        treinoService.deleteTreino(id);
        return getAllTreino();
    }


    @GetMapping("/treino/{id}")
    public Treino getAllById(@PathVariable Long id){

        return treinoService.getAllById(id);

    }

    @GetMapping("/user/{idUser}")
    public List<Treino> getAllTreinosByIdUser(@PathVariable Long idUser){
        return treinoService.getAllTreinosByIdUser(idUser);
    }

    @GetMapping("/user/treino/{id}/{idUser}")
    public Treino getTreinoByIdAndIdUser(@PathVariable Long id, @PathVariable Long idUser){
        return treinoService.getTreinoByIdAndIdUser(id, idUser);
    }


    //temporario
    @PostMapping("/delete/all")
    public String deleteAll(){
        return treinoService.deleteAllTreino();
    }
    


    @DeleteMapping("/delete/{id}/user/{idUser}")
    public ResponseEntity deleteByIdAndIdUser(@PathVariable Long id,  @PathVariable Long idUser){
        System.out.println(id);
        System.out.println(idUser);
        return treinoService.deleteByIdAndIdUser(id, idUser);
    }
}

