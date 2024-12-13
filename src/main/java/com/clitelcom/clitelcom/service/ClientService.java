package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;

import java.util.List;

public interface ClientService
{
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO getClientById(Long clientId);
    ClientDTO updateClient(Long clientId, ClientDTO clientDTO);
    ClientDTO deleteClientById(Long clientId);
    List<ClientDTO> getClientByName(String name);

}
