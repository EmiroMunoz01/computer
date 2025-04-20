package com.computer.computer.Service.ComputerService;


import com.computer.computer.DTO.Computer.ComputerDTO;
import com.computer.computer.Entity.ComputerEntity;
import com.computer.computer.Repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComputerService implements ComputerImpl {

    @Autowired
    private ComputerRepository computerRepository;

    @Override
    public List<ComputerEntity> listComputer() {
        return computerRepository.findAll();
    }


    public List<ComputerDTO> getComputersUser(Principal principal) {

        String userEmail = getUserEmailFromPrincipal(principal);

        return computerRepository.findComputerEntitiesByUserStore_Email(userEmail).stream
                ()
                    .map(computer -> new ComputerDTO(
                            computer.getId_computer(),
                            computer.getProcessor(),
                            computer.getRamMemory(),
                            computer.getGpu(),
                            computer.getBoard()

                    )).collect(Collectors.toList());

    }


    public String getUserEmailFromPrincipal(Principal principal) {
        return principal.getName();
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
