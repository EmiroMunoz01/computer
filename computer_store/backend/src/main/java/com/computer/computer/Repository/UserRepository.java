package com.computer.computer.Repository;

import java.util.Optional;

import com.computer.computer.Entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByIdentificationDocument(int documentIdentification);

    void deleteByIdentificationDocument(int identificationDocument);


}
