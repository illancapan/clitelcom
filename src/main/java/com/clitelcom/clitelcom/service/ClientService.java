package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;

public interface ClientService
{
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO getClientByID(Long clientId);
    ClientDTO updateClient(Long clientId, ClientDTO clientDTO);

}
