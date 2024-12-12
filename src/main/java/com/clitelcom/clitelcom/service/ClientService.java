package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.model.dto.ClientDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.repository.ClientRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRespository clientRespository;

    @Autowired
    private ModelMapper modelMapper;


    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        client = clientRespository.save(client);

        return modelMapper.map(client, ClientDTO.class);
    }
    public ClientDTO getClientByID(long clientId) {
        Client client = clientRespository.findById(clientId)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        return modelMapper.map(client,ClientDTO.class);
    }
}
