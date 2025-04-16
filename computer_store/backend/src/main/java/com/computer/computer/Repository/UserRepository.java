package com.computer.computer.Repository;

import java.util.Optional;

import com.computer.computer.Entity.UserEntity;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByIdentificationDocument(Long documentIdentification);

    Optional<UserEntity> findByEmail(String email);

    void deleteByIdentificationDocument(Long identificationDocument);


}
