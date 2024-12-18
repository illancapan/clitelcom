package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    ClientDTO getClientById(Long id);
    ClientDTO getClientByName(String name);
    ClientDTO deleteClientById(Long id);
    ClientDTO changeClientStatusById(Long id);
}
