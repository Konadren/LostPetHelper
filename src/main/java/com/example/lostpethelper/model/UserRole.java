package com.example.lostpethelper.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // hashCode, equals, toString, get/set
@NoArgsConstructor // без аргументов
@AllArgsConstructor // со всеми аргументами
@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleID;

    @Column(name = "role_name")
    private String roleName;
}
