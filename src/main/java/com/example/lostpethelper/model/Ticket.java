package com.example.lostpethelper.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketID;

    @ManyToOne // каждый тикет связан с одним пользователем
    @JoinColumn(name = "user_id") // @Column(name = ...) нельзя
    private User user;

    @Column(name = "ticket_type")
    private String ticketType;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "img_uri")
    private String imgURI;

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

}
