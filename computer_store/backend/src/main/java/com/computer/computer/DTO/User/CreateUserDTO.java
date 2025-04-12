package com.computer.computer.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateUserDTO {


    private String name;

    private String lastname;

    private int identificationDocument;

    private int numberPhone;

    private String email;

    private String password;

}
