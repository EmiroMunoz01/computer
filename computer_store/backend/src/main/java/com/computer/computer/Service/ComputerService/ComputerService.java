package com.computer.computer.Service.ComputerService;


import com.computer.computer.Entity.ComputerEntity;
import com.computer.computer.Repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerService implements ComputerImpl {

    @Autowired
    private ComputerRepository computerRepository;

    @Override
    public List<ComputerEntity> listComputer() {
        return computerRepository.findAll();
    }


    @Override
    public ComputerEntity createComputer(ComputerEntity createComputer) {
        return computerRepository.save(createComputer);
    }

    @Override
    public Optional<ComputerEntity> findComputerById(Long id) {
        return computerRepository.findById(id);
    }

    @Override
    public ComputerEntity updateComputer(Long id, ComputerEntity computerEntity) {

        ComputerEntity findUser = computerRepository.findById(id).orElseThrow(() -> new RuntimeException("Computer with ID not found: " + id));

        findUser.setBoard(computerEntity.getBoard());
        findUser.setRamMemory(computerEntity.getRamMemory());
        findUser.setProcessor(computerEntity.getProcessor());
        findUser.setGpu(computerEntity.getGpu());

        return computerRepository.save(findUser);
    }

    @Override
    public void deleteComputer(Long id) {
        computerRepository.deleteById(id);
    }

}
