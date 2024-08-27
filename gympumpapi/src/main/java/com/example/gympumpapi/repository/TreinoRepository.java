package com.example.gympumpapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gympumpapi.entity.Treino;
import java.util.List;
import java.util.Optional;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long>{
    List<Treino> findTreinoByIdUser(Long idUser);
    Optional<Treino> findTreinoByIdAndIdUser(Long id, Long idUser);
}
