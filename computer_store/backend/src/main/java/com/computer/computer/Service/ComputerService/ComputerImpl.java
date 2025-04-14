package com.computer.computer.Service.ComputerService;


import com.computer.computer.Entity.ComputerEntity;

import java.util.List;
import java.util.Optional;

public interface ComputerImpl {


    public List<ComputerEntity> listComputer();

    public ComputerEntity createComputer(ComputerEntity createComputer);

    public Optional<ComputerEntity> findComputerById(Long id);

    public ComputerEntity updateComputer(Long id, ComputerEntity computerEntity);

    public void deleteComputer(Long id);

}
