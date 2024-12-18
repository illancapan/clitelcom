package com.clitelcom.clitelcom.controller;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.service.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientServiceImpl clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO createClient = clientService.createClient(clientDTO);
        return new ResponseEntity<>(createClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        ClientDTO updateClient = clientService.updateClient(id, clientDTO);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO clientById = clientService.getClientById(id);
        return ResponseEntity.ok(clientById);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ClientDTO> getClientByName(@PathVariable String name) {
        ClientDTO clientByName = clientService.getClientByName(name);
        return ResponseEntity.ok(clientByName);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDTO> deleteClientBYId(@PathVariable Long id) {
        ClientDTO deletedClient = clientService.deleteClientById(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
