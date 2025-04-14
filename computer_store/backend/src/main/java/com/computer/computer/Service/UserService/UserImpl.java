package com.computer.computer.Service.UserService;

import com.computer.computer.DTO.User.CreateUserDTO;
import com.computer.computer.DTO.User.UpdateUserDTO;
import com.computer.computer.DTO.User.UserDTO;
import com.computer.computer.Entity.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserImpl {

    public List<UserDTO> listUser();

    public UserEntity createUser(CreateUserDTO createUserDTO);

    public Optional<UserDTO> findUserByDocumentIdentification(int userDocumentIdentification);

    public boolean deleteUserByDocumentIdentification(int userDocumentIdentification);

    public Optional<UserEntity> updateUserByDocumentIdentification(int userDocumentIdentification, UpdateUserDTO updateUserDTO);

}
