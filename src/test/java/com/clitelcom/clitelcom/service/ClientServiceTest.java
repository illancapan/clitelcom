package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClientService clientService;

    private Client client;
    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        client = new Client(
                1L,
                "Igor",
                "12345678-9",
                "Calle siempre viva",
                LocalDate.of(1990, 1, 1),
                null
        );

        clientDTO = new ClientDTO(
                1L,
                "Igor",
                "12345678-9",
                "Calle siempre viva",
                LocalDate.of(1990, 1, 1),
                null
        );
    }

    @Test
    void testCreateClient_ShouldReturnClientDTO_WhenClientIsCreated() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(modelMapper.map(clientDTO, Client.class)).thenReturn(client);
        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);

        ClientDTO result = clientService.createClient(clientDTO);

        assertNotNull(result);
        assertEquals(clientDTO.getName(), result.getName());
        assertEquals(clientDTO.getRun(), result.getRun());

        verify(clientRepository, times(1)).save(any(Client.class));
        verify(modelMapper, times(1)).map(clientDTO, Client.class);
        verify(modelMapper, times(1)).map(client, ClientDTO.class);
    }

    @Test
    void testUpdateClient_ShouldReturnUpdatedClientDTO_WhenClientIsUpdated() {
        Long clientId = 1L;

        ClientDTO inputDTO = new ClientDTO(
                clientId,
                "Updated Name",
                "12345678-9",
                "Updated Address",
                LocalDate.of(1995, 6, 15),
                null
        );

        Client existingClient = new Client(
                clientId,
                "Original Name",
                "87654321-0",
                "Original Address",
                LocalDate.of(1985, 5, 5),
                null
        );

        Client updatedClient = new Client(
                clientId,
                "Updated Name",
                "12345678-9",
                "Updated Address",
                LocalDate.of(1995, 6, 15),
                null
        );

        ClientDTO expectedDTO = new ClientDTO(
                clientId,
                "Updated Name",
                "12345678-9",
                "Updated Address",
                LocalDate.of(1995, 6, 15),
                null
        );

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(updatedClient);
        when(modelMapper.map(updatedClient, ClientDTO.class)).thenReturn(expectedDTO);

        ClientDTO result = clientService.updateClient(clientId, inputDTO);

        assertNotNull(result);
        assertEquals(expectedDTO.getName(), result.getName());
        assertEquals(expectedDTO.getRun(), result.getRun());
        assertEquals(expectedDTO.getAddress(), result.getAddress());
        assertEquals(expectedDTO.getBirthDate(), result.getBirthDate());

        verify(clientRepository).findById(clientId);
        verify(clientRepository).save(existingClient);
        verify(modelMapper).map(updatedClient, ClientDTO.class);
    }

    @Test
    void testGetClientClientDTO_WhenUserExists() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);

        ClientDTO result = clientService.getClientById(1L);

        assertNotNull(result);
        assertEquals(clientDTO.getId(), result.getId());
        assertEquals(clientDTO.getName(), result.getName());
        assertEquals(clientDTO.getRun(), result.getRun());

        verify(clientRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(client, ClientDTO.class);
    }

    @Test
    void testDeleteClientDTO_WhenUserExist() {
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        clientService.deleteClientById(client.getId());

        verify(clientRepository).findById(client.getId());
        verify(clientRepository).delete(client);
    }

    @Test
    void testGetClientByName_ShouldReturnClientDTOList_WhenClientsExist() {
        String name = "Igor";

        Client client1 = new Client(
                1L,
                "Igor",
                "12345678-9",
                "Calle siempre viva",
                LocalDate.of(1990, 1, 1),
                null
        );

        Client client2 = new Client(
                2L,
                "Igorito",
                "98765432-1",
                "Otra dirección",
                LocalDate.of(1992, 2, 2),
                null
        );

        ClientDTO clientDTO1 = new ClientDTO(
                1L,
                "Igor",
                "12345678-9",
                "Calle siempre viva",
                LocalDate.of(1990, 1, 1),
                null
        );

        ClientDTO clientDTO2 = new ClientDTO(
                2L,
                "Igorito",
                "98765432-1",
                "Otra dirección",
                LocalDate.of(1992, 2, 2),
                null
        );

        List<Client> clients = List.of(client1, client2);
        List<ClientDTO> expectedDTOs = List.of(clientDTO1, clientDTO2);

        when(clientRepository.findByNameContainingIgnoreCase(name)).thenReturn(clients);
        when(modelMapper.map(client1, ClientDTO.class)).thenReturn(clientDTO1);
        when(modelMapper.map(client2, ClientDTO.class)).thenReturn(clientDTO2);

        List<ClientDTO> result = clientService.getClientByName(name);

        assertNotNull(result);
        assertEquals(expectedDTOs.size(), result.size());
        assertEquals(expectedDTOs.get(0).getName(), result.get(0).getName());
        assertEquals(expectedDTOs.get(1).getName(), result.get(1).getName());

        verify(clientRepository, times(1)).findByNameContainingIgnoreCase(name);
        verify(modelMapper, times(1)).map(client1, ClientDTO.class);
        verify(modelMapper, times(1)).map(client2, ClientDTO.class);
    }

    @Test
    void testGetClientByName_ShouldThrowException_WhenNoClientsFound() {
        String name = "NonExistentName";

        when(clientRepository.findByNameContainingIgnoreCase(name)).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clientService.getClientByName(name);
        });

        assertEquals("No clients found with the given name", exception.getMessage());

        verify(clientRepository, times(1)).findByNameContainingIgnoreCase(name);
    }

}
