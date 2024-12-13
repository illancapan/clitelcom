package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        client = clientRepository.save(client);

        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public ClientDTO updateClient(Long clientId, ClientDTO clientDTO) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        existingClient.setName(clientDTO.getName());
        existingClient.setRun(clientDTO.getRun());
        existingClient.setAddress(clientDTO.getAddress());
        existingClient.setBirthdate(clientDTO.getBirthDate());

        Client updatedClient = clientRepository.save(existingClient);

        return modelMapper.map(updatedClient, ClientDTO.class);
    }

    @Override
    public ClientDTO getClientById(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public List<ClientDTO> getClientByName(String name) {
        List<Client> clients = clientRepository.findByNameContainingIgnoreCase(name);
        if (clients.isEmpty()) {
            throw new RuntimeException("No clients found with the given name");
        }
        return clients.stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO deleteClientById(Long clientId) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        clientRepository.delete(existingClient);
        return modelMapper.map(existingClient, ClientDTO.class);
    }
}

