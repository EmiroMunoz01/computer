package com.computer.computer.DTO.User;
import com.computer.computer.DTO.Computer.ComputerDTO;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    private String name;
    private Long identificationDocument;
    private String email;
    private Long numberPhone;
    private List<ComputerDTO> computers;
}


