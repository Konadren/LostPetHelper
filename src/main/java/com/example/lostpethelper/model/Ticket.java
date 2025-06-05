package com.example.lostpethelper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String ticketType;

    @Column(nullable = false)
    private String petName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String imgURI;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private UUID userId;
}
