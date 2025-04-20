package com.computer.computer.Repository;

import com.computer.computer.Entity.ComputerEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<ComputerEntity, Long> {

    List<ComputerEntity> findComputerEntitiesByUserStore_Email(String userStoreIdentificationDocument);
}
