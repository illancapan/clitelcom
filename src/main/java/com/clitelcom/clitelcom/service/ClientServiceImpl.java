package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.repository.ClientRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRespository clientRespository;

    @Autowired
    private ModelMapper modelMapper;




    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        client = clientRespository.save(client);

        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public ClientDTO updateClient(Long clientId, ClientDTO clientDTO) {
        Client existingClient = clientRespository.findById(clientId)
                .orElseThrow(()-> new RuntimeException("Client not found"));

        existingClient.setName(clientDTO.getName());
        existingClient.setRun(clientDTO.getRun());
        existingClient.setAddress(clientDTO.getAddress());
        existingClient.setBirthdate(clientDTO.getBirthDate());

        Client updatedClient = clientRespository.save(existingClient);

        return modelMapper.map(updatedClient,ClientDTO.class);
    }

    @Override
    public ClientDTO getClientByID(Long clientId) {
        Client client = clientRespository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        return modelMapper.map(client, ClientDTO.class);
    }
}
