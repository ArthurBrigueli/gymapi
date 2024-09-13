package com.example.gympumpapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gympumpapi.entity.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findByIdUserIn(List<Long> idUser);
}
