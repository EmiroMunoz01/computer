package com.computer.computer.Service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.computer.computer.DTO.User.CreateUserDTO;
import com.computer.computer.DTO.User.UpdateUserDTO;
import com.computer.computer.DTO.User.UserDTO;
import com.computer.computer.Entity.UserEntity;
import com.computer.computer.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService implements UserImpl {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> listUser() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(user -> new UserDTO(user.getName(), user.getIdentificationDocument(), user.getEmail(),user.getNumberPhone())).collect(Collectors.toList());
    }


    @Override
    public Optional<UserDTO> findUserByDocumentIdentification(int documentIdentification) {
        return userRepository.findByIdentificationDocument(documentIdentification)
                .map(user -> new UserDTO(user.getName(), user.getIdentificationDocument(), user.getEmail(), user.getNumberPhone()));
    }

    @Override
    @Transactional
    public Optional<UserEntity> updateUserByDocumentIdentification(int userDocumentIdentification, UpdateUserDTO updateUserDTO) {

        UserEntity findUser = userRepository.findByIdentificationDocument(userDocumentIdentification).orElseThrow(() -> new RuntimeException("Usuario no encontrado con documento: " + userDocumentIdentification));


        System.out.println("DTO recibido: " + updateUserDTO.getNumberPhone() + ", " + updateUserDTO.getPassword());

        findUser.setNumberPhone(updateUserDTO.getNumberPhone());
        findUser.setPassword(updateUserDTO.getPassword());

        return Optional.of(userRepository.save(findUser));
    }


    @Override
    public UserEntity createUser(CreateUserDTO createUserDTO) {
        UserEntity user = new UserEntity();
        user.setName(createUserDTO.getName());
        user.setLastname(createUserDTO.getLastname());
        user.setIdentificationDocument(createUserDTO.getIdentificationDocument());
        user.setNumberPhone(createUserDTO.getNumberPhone());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        return userRepository.save(user);
    }


    @Override
    @Transactional
    public boolean deleteUserByDocumentIdentification(int userDocumentIdentification) {
        Optional<UserEntity> user = userRepository.findByIdentificationDocument(userDocumentIdentification);

        if (user.isPresent()) {
            userRepository.deleteByIdentificationDocument(userDocumentIdentification);
            return true; // El usuario fue eliminado
        } else {
            return false; // El usuario no existe
        }
    }




}
