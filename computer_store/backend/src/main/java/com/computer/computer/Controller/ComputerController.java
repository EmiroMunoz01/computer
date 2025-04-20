package com.computer.computer.Controller;


import com.computer.computer.DTO.Computer.ComputerDTO;
import com.computer.computer.Entity.ComputerEntity;
import com.computer.computer.Service.ComputerService.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;



@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("store")
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    @GetMapping("/admin/computer/get-all")
    public ResponseEntity<List<ComputerEntity>> getComputers() {
        return ResponseEntity.ok(computerService.listComputer());
    }

    @GetMapping("/computer/my-computer")
    public List<ComputerDTO> obtenerMisComputadores(Principal principal) {
        return computerService.getComputersUser(principal);
    }

    @PostMapping("/admin/computer/create")
    public ResponseEntity<ComputerEntity> createComputer(@RequestBody ComputerEntity computerEntity) {
        return ResponseEntity.ok(computerService.createComputer(computerEntity));
    }

    @GetMapping("/admin/computer/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(computerService.findComputerById(id));
    }

    @PutMapping("/admin/computer/update/{id}")
    public ResponseEntity<?> UpdateComputerById(@PathVariable Long id, @RequestBody ComputerEntity computerEntity) {
        computerService.updateComputer(id, computerEntity);
        return ResponseEntity.ok().body("Computer Update");
    }

    @DeleteMapping("/admin/computer/delete-by-id/{id}")
    public ResponseEntity<?> deleteComputerById(@PathVariable Long id) {

        computerService.deleteComputer(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Computer Delete");
    }
}
