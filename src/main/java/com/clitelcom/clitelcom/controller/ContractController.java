package com.clitelcom.clitelcom.controller;

import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.service.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractServiceImpl contractService;

    @GetMapping
    public ResponseEntity<List<ContractDTO>> getAllContracts() {
        List<ContractDTO> contracts = contractService.getAllContracts();
        return ResponseEntity.ok(contracts);
    }

    @PostMapping
    public ResponseEntity<ContractDTO> createContract(@RequestBody ContractDTO contractDTO) {
        ContractDTO newContract = contractService.createContract(contractDTO);
        return ResponseEntity.status(201).body(newContract);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivateContract(@PathVariable Long id) {
        contractService.desactiveContract(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/discount/{clientId}")
    public ResponseEntity<Double> getDiscount(@PathVariable Long clientId) {
        Double discount = contractService.calculateDiscount(clientId);
        return ResponseEntity.ok(discount);
    }
}


