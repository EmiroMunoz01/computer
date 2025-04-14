package com.computer.computer.Repository;

import com.computer.computer.Entity.ComputerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<ComputerEntity, Long> {

}
