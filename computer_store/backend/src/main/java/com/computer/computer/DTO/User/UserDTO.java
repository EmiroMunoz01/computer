package com.computer.computer.DTO.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    private String name;
    private int identificationDocument;
    private String email;
    private int numberPhone;

}
