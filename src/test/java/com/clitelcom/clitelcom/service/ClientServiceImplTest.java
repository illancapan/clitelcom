package com.clitelcom.clitelcom.service;


import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    ClientRepository clientRepository;
    ModelMapper modelMapper;
    ClientServiceImpl clientService;

    Client client;
    ClientDTO clientBuild;


    @BeforeEach
    void setUp() {
        clientRepository = Mockito.mock(ClientRepository.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        clientService = new ClientServiceImpl(clientRepository, modelMapper);

        client = new Client();
        client.setId(1L);
        client.setName("Juan Doe");
        client.setRun("12345678-9");
        client.setAddress("siempre viva");
        client.setBirthDate(LocalDate.of(1990,1,1));
        client.setActive(true);
        
        clientBuild = ClientDTO.builder()
                .name("John Doe")
                .run("12345678-9")
                .address("123 Street")
                .birthDate(LocalDate.of(1991,1,1))
                .build();
    }

    @Test
    void should_mapAndSaveClient_when_createClientIsCalled() {
        Mockito.when(modelMapper.map(clientBuild, Client.class)).thenReturn(client);
        Mockito.when(clientRepository.save(client)).thenReturn(client);
        Mockito.when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientBuild);

        ClientDTO response = clientService.createClient(clientBuild);

        assertNotNull(response);
        assertEquals(clientBuild.getName(), response.getName());
    }

    @Test
    void should_updateExistingClientDetails_when_updateClientIsCalledWithValidId() {
        Long clientId = 1L;
        Mockito.when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        Mockito.when(clientRepository.save(client)).thenReturn(client);
        Mockito.when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientBuild);

        clientBuild.setName("Juan Doe");
        ClientDTO response = clientService.updateClient(clientId, clientBuild);

        assertNotNull(response);
        assertEquals("Juan Doe", response.getName());
    }

    @Test
    void should_returnClientDTO_when_getClientByIdIsCalledWithExistingId() {
        Long clientId = 1L;
        Mockito.when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        Mockito.when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientBuild);

        ClientDTO response = clientService.getClientById(clientId);

        assertNotNull(response);
        assertEquals(clientBuild.getName(), response.getName());
    }

    @Test
    void should_findClientByName_when_getClientByNameIsCalled() {
        String clientName = "Juan";
        Mockito.when(clientRepository.findByNameContainingIgnoreCase(clientName))
                .thenReturn(Collections.singletonList(client));
        Mockito.when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientBuild);

        ClientDTO response = clientService.getClientByName(clientName);

        assertNotNull(response);
        assertEquals(clientBuild.getName(), response.getName());
    }

    @Test
    void should_deleteClientAndReturnClientDTO_when_deleteClientByIdIsCalledWithExistingId() {
        Long clientId = 1L;
        Mockito.when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        Mockito.when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientBuild);

        ClientDTO response = clientService.deleteClientById(clientId);

        assertNotNull(response);
        assertEquals(clientBuild.getName(), response.getName());
    }

    @Test
    void should_toggleClientActiveState_when_changeClientStatusByIdIsCalledWithValidId() {
        Long clientId = 1L;
        Mockito.when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        Mockito.when(clientRepository.save(client)).thenReturn(client);
        Mockito.when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientBuild);

        boolean initialStatus = client.isActive();
        ClientDTO response = clientService.changeClientStatusById(clientId);

        assertNotNull(response);
        assertNotEquals(initialStatus, client.isActive());
    }

}