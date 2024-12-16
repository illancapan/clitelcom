package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;

public interface ClienteService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    ClientDTO getClientById(Long id);
    ClientDTO getClientByName(String name);
    ClientDTO deleteClientById(Long id);
}
