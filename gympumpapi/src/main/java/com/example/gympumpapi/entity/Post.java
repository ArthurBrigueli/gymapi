package com.example.gympumpapi.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;

@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idUser;
    
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;


    private Integer likes = 0;

    private LocalDateTime createAt;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JsonNode getContent() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(content);
        } catch (Exception e) {
            // Trate a exceção adequadamente, ou retorne null em caso de erro
            return null;
        }
    }

    public void setContent(JsonNode content) {
        this.content = content.toString();
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLike(Integer likes) {
        this.likes = likes;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
