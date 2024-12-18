package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

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
        existingClient.setBirthDate(clientDTO.getBirthDate());

        Client updatedClient = clientRepository.save(existingClient);

        return modelMapper.map(updatedClient, ClientDTO.class);
    }

    @Override
    public ClientDTO getClientById(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client Not Found"));
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public ClientDTO getClientByName(String name) {
        Client client = clientRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No clients found with the given name"));

        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public ClientDTO deleteClientById(Long clientId) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client Not Found"));
        ClientDTO deletedCLientDto = modelMapper.map(existingClient, ClientDTO.class);
        clientRepository.delete(existingClient);
        return deletedCLientDto;
    }
}
