package com.computer.computer.Service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.computer.computer.DTO.Computer.ComputerDTO;
import com.computer.computer.DTO.User.CreateUserDTO;
import com.computer.computer.DTO.User.UpdateUserDTO;
import com.computer.computer.DTO.User.UserDTO;
import com.computer.computer.Entity.Role;
import com.computer.computer.Entity.UserEntity;
import com.computer.computer.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> listUser() {
        List<UserEntity> users = userRepository.findAll();

        return users.stream().map(user -> {
            List<ComputerDTO> computerDTOs = user.getComputers().stream()
                    .map(computer -> new ComputerDTO(
                            computer.getId_computer(),
                            computer.getProcessor(),
                            computer.getRamMemory(),
                            computer.getGpu(),
                            computer.getBoard()
                            // Agregar más campos si es necesario
                    ))
                    .collect(Collectors.toList());

            return new UserDTO(
                    user.getName(),
                    user.getIdentificationDocument(),
                    user.getEmail(),
                    user.getNumberPhone(),
                    computerDTOs
            );
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findUserByDocumentIdentification(Long documentIdentification) {
        return userRepository.findByIdentificationDocument(documentIdentification)

                .map(user -> {
                    // Mapeamos cada ComputerEntity a ComputerDTO
                    List<ComputerDTO> computerDTOs = user.getComputers().stream()
                            .map(computer -> new ComputerDTO(
                                    computer.getId_computer(),
                                    computer.getProcessor(),
                                    computer.getRamMemory(),
                                    computer.getGpu(),
                                    computer.getBoard()
                                    // Agrega más campos si necesitas
                            ))
                            .collect(Collectors.toList());

                    // Retornamos el DTO con la lista de computadores convertidos
                    return new UserDTO(
                            user.getName(),
                            user.getIdentificationDocument(),
                            user.getEmail(),
                            user.getNumberPhone(),
                            computerDTOs
                    );
                });
    }


    @Override
    @Transactional
    public Optional<UserEntity> updateUserByDocumentIdentification(Long userDocumentIdentification, UpdateUserDTO updateUserDTO) {

        UserEntity findUser = userRepository.findByIdentificationDocument(userDocumentIdentification).orElseThrow(() -> new RuntimeException("User not found document identification: " + userDocumentIdentification));


        System.out.println("DTO recibido: " + updateUserDTO.getNumberPhone() + ", " + updateUserDTO.getPassword());

        findUser.setNumberPhone(updateUserDTO.getNumberPhone());
        findUser.setPassword(updateUserDTO.getPassword());

        return Optional.of(userRepository.save(findUser));
    }


    @Override
    public UserEntity createUser(CreateUserDTO createUserDTO) {
        UserEntity user = new UserEntity();
        user.setRole(Role.ROLE_USER);
        user.setName(createUserDTO.getName());
        user.setLastname(createUserDTO.getLastname());
        user.setIdentificationDocument(createUserDTO.getIdentificationDocument());
        user.setNumberPhone(createUserDTO.getNumberPhone());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<UserDTO> findUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .map(user -> {
                    // Mapeamos cada ComputerEntity a ComputerDTO
                    List<ComputerDTO> computerDTOs = user.getComputers().stream()
                            .map(computer -> new ComputerDTO(
                                    computer.getId_computer(),
                                    computer.getProcessor(),
                                    computer.getRamMemory(),
                                    computer.getGpu(),
                                    computer.getBoard()
                            ))
                            .collect(Collectors.toList());

                    // Retornamos el DTO con la lista de computadores convertidos
                    return new UserDTO(
                            user.getName(),
                            user.getIdentificationDocument(),
                            user.getEmail(),
                            user.getNumberPhone(),
                            computerDTOs
                    );
                })
                .or(() -> Optional.empty()); // Evita lanzar RuntimeException dentro de `map()`
    }


    @Override
    @Transactional
    public boolean deleteUserByDocumentIdentification(Long userDocumentIdentification) {
        Optional<UserEntity> user = userRepository.findByIdentificationDocument(userDocumentIdentification);

        if (user.isPresent()) {
            userRepository.deleteByIdentificationDocument(userDocumentIdentification);
            return true; // El usuario fue eliminado
        } else {
            return false; // El usuario no existe
        }
    }


}
