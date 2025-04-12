package com.computador.computador.Service.UserService;

import java.util.List;
import java.util.Optional;

import com.computador.computador.DTO.User.CreateUserDTO;
import com.computador.computador.DTO.User.UpdateUserDTO;
import com.computador.computador.DTO.User.UserDTO;
import com.computador.computador.Entity.UserEntity;

public interface UserImpl {

    public List<UserDTO> listUser();

    public UserEntity createUser(CreateUserDTO createUserDTO);

    public Optional<UserDTO> findUserByDocumentIdentification(int userDocumentIdentification);

    public boolean deleteUserByDocumentIdentification(int userDocumentIdentification);

    public Optional<UserEntity> updateUserByDocumentIdentification(int userDocumentIdentification, UpdateUserDTO updateUserDTO);

}
