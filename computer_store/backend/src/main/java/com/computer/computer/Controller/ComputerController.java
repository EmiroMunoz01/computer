package com.computer.computer.Controller;


import com.computer.computer.Entity.ComputerEntity;
import com.computer.computer.Service.ComputerService.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("store")
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    @GetMapping("/computer/get-all")
    public ResponseEntity<List<ComputerEntity>> getComputers() {
        return ResponseEntity.ok(computerService.listComputer());
    }

    @PostMapping("/computer/create")
    public ResponseEntity<ComputerEntity> createComputer(@RequestBody ComputerEntity computerEntity) {
        return ResponseEntity.ok(computerService.createComputer(computerEntity));
    }

    @GetMapping("/computer/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(computerService.findComputerById(id));
    }

    @PutMapping("/computer/update/{id}")
    public ResponseEntity<?> UpdateComputerById(@PathVariable Long id, @RequestBody ComputerEntity computerEntity) {
        computerService.updateComputer(id, computerEntity);
        return ResponseEntity.ok().body("Computer Update");
    }

    @DeleteMapping("/computer/delete-by-id/{id}")
    public ResponseEntity<?> deleteComputerById(@PathVariable Long id) {

        computerService.deleteComputer(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Computer Delete");
    }
}
