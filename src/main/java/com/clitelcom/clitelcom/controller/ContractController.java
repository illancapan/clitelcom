package com.clitelcom.clitelcom.controller;

import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.service.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("{/id}/deactivate")
    public ResponseEntity<Void> deactivateContract(@PathVariable Long id){
        contractService.desactiveContract(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{clienteId}/discount")
    public ResponseEntity<Double> getDiscount(@PathVariable Long clientId){
        Double discount =contractService.calculateDiscount(clientId);
        return ResponseEntity.ok(discount);
    }
}

