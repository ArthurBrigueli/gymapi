package com.example.gympumpapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gympumpapi.entity.Treino;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long>{
    
}
