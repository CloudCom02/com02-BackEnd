package com._02server.com02backendproject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;
}
