package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientServiceImplTest {

//    private ClientRepository clientRepository;
//    private ModelMapper modelMapper;
//    private ClientServiceImpl clientServiceImpl;
//
//    private Client client;
//    private ClientDTO clientDTO;
//
//    @BeforeEach
//    void setUp() {
//
//
//
//        //  mocks manualmente
//        clientRepository = mock(ClientRepository.class);
//        modelMapper = mock(ModelMapper.class);
//
//        //inyectando manualmente los mocks
//        clientServiceImpl = new ClientServiceImpl(clientRepository, modelMapper);
//    }
//
//    @Test
//    void testCreateClient_ShouldReturnClientDTO_WhenClientIsCreated() {
//        // Crear un DTO de cliente
//        ClientDTO clientDTO = new ClientDTO();
//        clientDTO.setName("John Doe");
//        clientDTO.setRun("12345678");
//        clientDTO.setAddress("123 Main St");
//        clientDTO.setBirthDate(LocalDate.now());
//
//        // Crear un cliente (entidad) de ejemplo
//        Client client = new Client();
//        client.setName(clientDTO.getName());
//        client.setRun(clientDTO.getRun());
//        client.setAddress(clientDTO.getAddress());
//        client.setBirthDate(clientDTO.getBirthDate());
//
//        // Comportamiento esperado de los mocks
//        when(modelMapper.map(clientDTO, Client.class)).thenReturn(client);
//        when(clientRepository.save(client)).thenReturn(client);
//        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);
//
//        // Llamar al método de servicio
//        ClientDTO result = clientServiceImpl.createClient(clientDTO);
//
//        // Verificar el resultado
//        assertNotNull(result);
//        assertEquals(clientDTO.getName(), result.getName());
//        assertEquals(clientDTO.getRun(), result.getRun());
//
//        // Verificar que los métodos de los mocks fueron llamados
//        verify(modelMapper, times(1)).map(clientDTO, Client.class);
//        verify(clientRepository, times(1)).save(client);
//        verify(modelMapper, times(1)).map(client, ClientDTO.class);
//    }
//
//
//    @Test
//    void testUpdateClient_ShouldReturnUpdatedClientDTO_WhenClientExists() {
//        Long clientId = 1L;
//
//        Client updatedClient = new Client(
//                clientId,
//                "Updated Name",
//                "87654321-0",
//                "Updated Address",
//                LocalDate.of(1992, 2, 2),
//                null
//        );
//
//        ClientDTO updatedClientDTO = new ClientDTO(
//                clientId,
//                "Updated Name",
//                "87654321-0",
//                "Updated Address",
//                LocalDate.of(1992, 2, 2),
//                null
//        );
//
//        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
//        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);
//        when(modelMapper.map(updatedClient, ClientDTO.class)).thenReturn(updatedClientDTO);
//
//        ClientDTO result = clientServiceImpl.updateClient(clientId, updatedClientDTO);
//
//        assertNotNull(result);
//        assertEquals("Updated Name", result.getName());
//        verify(clientRepository).findById(clientId);
//        verify(clientRepository).save(client);
//        verify(modelMapper).map(updatedClient, ClientDTO.class);
//    }
//
//    @Test
//    void testGetClientById_ShouldReturnClientDTO_WhenClientExists() {
//        Long clientId = 1L;
//
//        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
//        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);
//
//        ClientDTO result = clientServiceImpl.getClientById(clientId);
//
//        assertNotNull(result);
//        assertEquals(clientDTO.getId(), result.getId());
//        verify(clientRepository).findById(clientId);
//        verify(modelMapper).map(client, ClientDTO.class);
//    }
//
//    @Test
//    void testDeleteClient_ShouldDeleteClient_WhenClientExists() {
//        Long clientId = 1L;
//
//        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
//
//        clientServiceImpl.deleteClientById(clientId);
//
//        verify(clientRepository).findById(clientId);
//        verify(clientRepository).delete(client);
//    }
}