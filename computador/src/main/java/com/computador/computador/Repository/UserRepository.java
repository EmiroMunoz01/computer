package com.computador.computador.Repository;

import java.util.Optional;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.computador.computador.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByIdentificationDocument(int documentIdentification);

    void deleteByIdentificationDocument(int identificationDocument);


}
