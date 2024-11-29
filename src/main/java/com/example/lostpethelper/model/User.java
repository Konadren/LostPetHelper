package com.example.lostpethelper.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// потом сменить на sequence
    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

//    @ManyToOne
//    @JoinColumn(name = "role", referencedColumnName = "role_id") // name указывает на имя колонки из БД
//    private UserRole role;

    @Column(name = "roles")
    private String roles;

}
