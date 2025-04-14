package com.computer.computer.Controller;



import com.computer.computer.DTO.User.CreateUserDTO;
import com.computer.computer.DTO.User.UpdateUserDTO;
import com.computer.computer.DTO.User.UserDTO;
import com.computer.computer.Entity.UserEntity;
import com.computer.computer.Service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("store")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/user/get-all")
    public ResponseEntity<List<UserDTO>> getUsers() {

        return ResponseEntity.ok(userService.listUser());

    }

    @PostMapping("/user/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserDTO dto) {
        UserEntity createdUser = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/user/get-user/{identificationDocument}")
    public ResponseEntity<UserDTO> getUserByIdentificationDocument(@PathVariable int identificationDocument) {
        UserDTO userDTO = userService.findUserByDocumentIdentification(identificationDocument)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/user/delete-user/{identificationDocument}")
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


    @PutMapping("/user/update-user/{identificationDocument}")
    public ResponseEntity<UpdateUserDTO> updateUser(@PathVariable int identificationDocument, @RequestBody UpdateUserDTO updateUserDTO) {
        userService.updateUserByDocumentIdentification(identificationDocument, updateUserDTO);
        System.out.println("Respuesta desde controlador: " + updateUserDTO);
        return ResponseEntity.ok(updateUserDTO);
    }


}
