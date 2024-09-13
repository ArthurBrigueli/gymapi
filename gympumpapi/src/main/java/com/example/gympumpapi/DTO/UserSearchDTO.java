package com.example.gympumpapi.DTO;

import com.example.gympumpapi.repository.Status;

public class UserSearchDTO {
    private Long id;
    private String name;
    private Status status;

    // Construtor
    public UserSearchDTO(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
