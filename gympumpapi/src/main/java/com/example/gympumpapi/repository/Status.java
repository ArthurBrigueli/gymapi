package com.example.gympumpapi.repository;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum Status {
    @Enumerated(EnumType.STRING)
    PENDING,
    ACCEPTED,
    REJECTED;
}
