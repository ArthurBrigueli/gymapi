package com.example.gympumpapi.controller;

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
    public List<Treino> creatTreinos(@RequestBody Treino treino){

        treinoService.createTreino(treino);

        return getAllTreino();
    }

    @PostMapping("/exercicios/add")
    public List<Treino> createExercicios(@RequestBody ExercicioRequest exercicioRequest){

        treinoService.createExercicios(exercicioRequest.getId(), exercicioRequest.getExercicios());

        return getAllTreino();
    }

    @PutMapping("/exercicio/edit")
    public List<Treino> updateTreino(@RequestBody Treino treino){
        treinoService.updateTreino(treino);
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
}
