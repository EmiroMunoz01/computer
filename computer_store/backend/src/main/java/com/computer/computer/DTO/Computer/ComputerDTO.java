package com.computer.computer.DTO.Computer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ComputerDTO {

    private Long id_computer;
    private String processor;
    private String ramMemory;
    private String gpu;
    private String board;

}
