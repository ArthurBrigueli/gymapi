package com.example.gympumpapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gympumpapi.entity.Folha;
import java.util.List;

@Repository
public interface FolhaRepository extends JpaRepository<Folha, Long>{
    Folha findByIdUser(Long idUser);
    void deleteByIdUser(Long idUser);
}   
