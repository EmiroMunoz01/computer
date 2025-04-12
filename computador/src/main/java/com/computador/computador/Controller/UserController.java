package com.computador.computador.Controller;


import com.computador.computador.DTO.User.CreateUserDTO;
import com.computador.computador.DTO.User.UpdateUserDTO;
import com.computador.computador.DTO.User.UserDTO;
import com.computador.computador.Entity.UserEntity;
import com.computador.computador.Service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.listUser());
    }

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserDTO dto) {
        UserEntity createdUser = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/get-user/{identificationDocument}")
    public ResponseEntity<UserDTO> getUserByIdentificationDocument(@PathVariable int identificationDocument) {
        UserDTO userDTO = userService.findUserByDocumentIdentification(identificationDocument)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/delete-user/{identificationDocument}")
    public ResponseEntity<?> deleteUserByIdentificationDocument(@PathVariable int identificationDocument) {
        boolean isDeleted = userService.deleteUserByDocumentIdentification(identificationDocument);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("User deleted with identification document: " + identificationDocument);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with identification document: " + identificationDocument);
        }
    }


    @PutMapping("/update-user/{identificationDocument}")
    public ResponseEntity<UpdateUserDTO> updateUser(@PathVariable int identificationDocument, @RequestBody UpdateUserDTO updateUserDTO) {
        userService.updateUserByDocumentIdentification(identificationDocument, updateUserDTO);
        System.out.println("Respuesta desde controlador: " + updateUserDTO);
        return ResponseEntity.ok(updateUserDTO);
    }


}
