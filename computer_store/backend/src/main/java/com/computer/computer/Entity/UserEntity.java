package com.computer.computer.Entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_store")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @NotNull
    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @NotNull
    @Column(name = "lastname", nullable = false, unique = false)
    private String lastname;

    @NotNull
    @Column(name = "identification_document", nullable = false, unique = true)
    private Long identificationDocument;

    @NotNull
    @Column(name = "number_phone", nullable = false, unique = true)
    private Long numberPhone;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false, unique = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "userStore", cascade = CascadeType.ALL)
    private List<ComputerEntity> computers = new ArrayList<>();

}
