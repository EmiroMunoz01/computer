package com.computador.computador.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "computer_desktop")

public class ComputerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_computer;

    @NotNull
    @Column(name = "processor", nullable = false, unique = false)
    private String processor;

    @NotNull
    @Column(name = "ram_memory", nullable = false, unique = false)
    private String ramMemory;

    @NotNull
    @Column(name = "gpu", nullable = false, unique = false)
    private String gpu;

    @NotNull
    @Column(name = "board", nullable = false, unique = false)
    private String board;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "id_user_store")
    private UserEntity userStore;
}
