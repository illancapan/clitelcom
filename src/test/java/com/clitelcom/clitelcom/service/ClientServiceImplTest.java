package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    private ClientRepository clientRepository;
    private ModelMapper modelMapper;
    private ClientServiceImpl clientServiceImpl;

    private Client client;
    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {


        // Inicialización del objeto a testear con los mocks

        // Crear los mocks manualmente
        clientRepository = mock(ClientRepository.class);
        modelMapper = mock(ModelMapper.class);

        // Crear la clase bajo prueba, inyectando manualmente los mocks
        clientServiceImpl = new ClientServiceImpl(clientRepository, modelMapper);


//        client = new Client(
//                1L,
//                "Igor",
//                "12345678-9",
//                "Calle siempre viva",
//                LocalDate.of(1990, 1, 1),
//                null
//        );
//
//        clientDTO = new ClientDTO(
//                1L,
//                "Igor",
//                "12345678-9",
//                "Calle siempre viva",
//                LocalDate.of(1990, 1, 1),
//                null
//        );
//        clientServiceImpl = new ClientServiceImpl(clientDTO);

        // Inicialización manual de los mocks
//        clientRepository = Mockito.mock(ClientRepository.class);
//        modelMapper = Mockito.mock(ModelMapper.class);



    }

    @Test
    void testCreateClient_ShouldReturnClientDTO_WhenClientIsCreated() {
        // Crear un DTO de cliente
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John Doe");
        clientDTO.setRun("12345678");
        clientDTO.setAddress("123 Main St");
        clientDTO.setBirthDate(LocalDate.now());

        // Crear un cliente (entidad) de ejemplo
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setRun(clientDTO.getRun());
        client.setAddress(clientDTO.getAddress());
        client.setBirthDate(clientDTO.getBirthDate());

        // Comportamiento esperado de los mocks
        when(modelMapper.map(clientDTO, Client.class)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);

        // Llamar al método de servicio
        ClientDTO result = clientServiceImpl.createClient(clientDTO);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(clientDTO.getName(), result.getName());
        assertEquals(clientDTO.getRun(), result.getRun());

        // Verificar que los métodos de los mocks fueron llamados
        verify(modelMapper, times(1)).map(clientDTO, Client.class);
        verify(clientRepository, times(1)).save(client);
        verify(modelMapper, times(1)).map(client, ClientDTO.class);
    }

//    @Test
//    void testCreateClient_ShouldReturnClientDTO_WhenClientIsCreated() {
//        when(modelMapper.map(clientDTO, Client.class)).thenReturn(client);
//        when(clientRepository.save(any(Client.class))).thenReturn(client);
//        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);
//
//        ClientDTO result = clientServiceImpl.createClient(clientDTO);
//
//        assertNotNull(result);
//        assertEquals(clientDTO.getName(), result.getName());
//        verify(clientRepository, times(1)).save(any(Client.class));
//        verify(modelMapper, times(1)).map(clientDTO, Client.class);
//        verify(modelMapper, times(1)).map(client, ClientDTO.class);
//    }

    @Test
    void testUpdateClient_ShouldReturnUpdatedClientDTO_WhenClientExists() {
        Long clientId = 1L;

        Client updatedClient = new Client(
                clientId,
                "Updated Name",
                "87654321-0",
                "Updated Address",
                LocalDate.of(1992, 2, 2),
                null
        );

        ClientDTO updatedClientDTO = new ClientDTO(
                clientId,
                "Updated Name",
                "87654321-0",
                "Updated Address",
                LocalDate.of(1992, 2, 2),
                null
        );

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);
        when(modelMapper.map(updatedClient, ClientDTO.class)).thenReturn(updatedClientDTO);

        ClientDTO result = clientServiceImpl.updateClient(clientId, updatedClientDTO);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        verify(clientRepository).findById(clientId);
        verify(clientRepository).save(client);
        verify(modelMapper).map(updatedClient, ClientDTO.class);
    }

    @Test
    void testGetClientById_ShouldReturnClientDTO_WhenClientExists() {
        Long clientId = 1L;

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);

        ClientDTO result = clientServiceImpl.getClientById(clientId);

        assertNotNull(result);
        assertEquals(clientDTO.getId(), result.getId());
        verify(clientRepository).findById(clientId);
        verify(modelMapper).map(client, ClientDTO.class);
    }

    @Test
    void testDeleteClient_ShouldDeleteClient_WhenClientExists() {
        Long clientId = 1L;

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        clientServiceImpl.deleteClientById(clientId);

        verify(clientRepository).findById(clientId);
        verify(clientRepository).delete(client);
    }
}


//package com.clitelcom.clitelcom.service;
//
//import com.clitelcom.clitelcom.dto.ClientDTO;
//import com.clitelcom.clitelcom.model.entity.Client;
//import com.clitelcom.clitelcom.repository.ClientRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class ClientServiceImplTest {
//
//    @Mock
//    private ClientRepository clientRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private ClientServiceImpl clientServiceImpl;
//
//    private Client client;
//    private ClientDTO clientDTO;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        client = new Client(
//                1L,
//                "Igor",
//                "12345678-9",
//                "Calle siempre viva",
//                LocalDate.of(1990, 1, 1),
//                null
//        );
//
//        clientDTO = new ClientDTO(
//                1L,
//                "Igor",
//                "12345678-9",
//                "Calle siempre viva",
//                LocalDate.of(1990, 1, 1),
//                null
//        );
//    }
//
//    @Test
//    void testCreateClient_ShouldReturnClientDTO_WhenClientIsCreated() {
//        when(clientRepository.save(any(Client.class))).thenReturn(client);
//        when(modelMapper.map(clientDTO, Client.class)).thenReturn(client);
//        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);
//
//        ClientDTO result = clientServiceImpl.createClient(clientDTO);
//
//        assertNotNull(result);
//        assertEquals(clientDTO.getName(), result.getName());
//        assertEquals(clientDTO.getRun(), result.getRun());
//
//        verify(clientRepository, times(1)).save(any(Client.class));
//        verify(modelMapper, times(1)).map(clientDTO, Client.class);
//        verify(modelMapper, times(1)).map(client, ClientDTO.class);
//    }
//
//    @Test
//    void testUpdateClient_ShouldReturnUpdatedClientDTO_WhenClientIsUpdated() {
//        Long clientId = 1L;
//
//        ClientDTO inputDTO = new ClientDTO(
//                clientId,
//                "Updated Name",
//                "12345678-9",
//                "Updated Address",
//                LocalDate.of(1995, 6, 15),
//                null
//        );
//
//        Client existingClient = new Client(
//                clientId,
//                "Original Name",
//                "87654321-0",
//                "Original Address",
//                LocalDate.of(1985, 5, 5),
//                null
//        );
//
//        Client updatedClient = new Client(
//                clientId,
//                "Updated Name",
//                "12345678-9",
//                "Updated Address",
//                LocalDate.of(1995, 6, 15),
//                null
//        );
//
//        ClientDTO expectedDTO = new ClientDTO(
//                clientId,
//                "Updated Name",
//                "12345678-9",
//                "Updated Address",
//                LocalDate.of(1995, 6, 15),
//                null
//        );
//
//        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
//        when(clientRepository.save(existingClient)).thenReturn(updatedClient);
//        when(modelMapper.map(updatedClient, ClientDTO.class)).thenReturn(expectedDTO);
//
//        ClientDTO result = clientServiceImpl.updateClient(clientId, inputDTO);
//
//        assertNotNull(result);
//        assertEquals(expectedDTO.getName(), result.getName());
//        assertEquals(expectedDTO.getRun(), result.getRun());
//        assertEquals(expectedDTO.getAddress(), result.getAddress());
//        assertEquals(expectedDTO.getBirthDate(), result.getBirthDate());
//
//        verify(clientRepository).findById(clientId);
//        verify(clientRepository).save(existingClient);
//        verify(modelMapper).map(updatedClient, ClientDTO.class);
//    }
//
//    @Test
//    void testGetClientClientDTO_WhenUserExists() {
//        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
//        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);
//
//        ClientDTO result = clientServiceImpl.getClientById(1L);
//
//        assertNotNull(result);
//        assertEquals(clientDTO.getId(), result.getId());
//        assertEquals(clientDTO.getName(), result.getName());
//        assertEquals(clientDTO.getRun(), result.getRun());
//
//        verify(clientRepository, times(1)).findById(1L);
//        verify(modelMapper, times(1)).map(client, ClientDTO.class);
//    }
//
//    @Test
//    void testDeleteClientDTO_WhenUserExist() {
//        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
//
//        clientServiceImpl.deleteClientById(client.getId());
//
//        verify(clientRepository).findById(client.getId());
//        verify(clientRepository).delete(client);
//    }
//
//    @Test
//    void testGetClientByName_ShouldReturnClientDTOList_WhenClientsExist() {
//        String name = "Igor";
//
//        Client client1 = new Client(
//                1L,
//                "Igor",
//                "12345678-9",
//                "Calle siempre viva",
//                LocalDate.of(1990, 1, 1),
//                null
//        );
//
//        Client client2 = new Client(
//                2L,
//                "Igorito",
//                "98765432-1",
//                "Otra dirección",
//                LocalDate.of(1992, 2, 2),
//                null
//        );
//
//        ClientDTO clientDTO1 = new ClientDTO(
//                1L,
//                "Igor",
//                "12345678-9",
//                "Calle siempre viva",
//                LocalDate.of(1990, 1, 1),
//                null
//        );
//
//        ClientDTO clientDTO2 = new ClientDTO(
//                2L,
//                "Igorito",
//                "98765432-1",
//                "Otra dirección",
//                LocalDate.of(1992, 2, 2),
//                null
//        );
//
//        List<Client> clients = List.of(client1, client2);
//        List<ClientDTO> expectedDTOs = List.of(clientDTO1, clientDTO2);
//
//        when(clientRepository.findByNameContainingIgnoreCase(name)).thenReturn(clients);
//        when(modelMapper.map(client1, ClientDTO.class)).thenReturn(clientDTO1);
//        when(modelMapper.map(client2, ClientDTO.class)).thenReturn(clientDTO2);
//
//        List<ClientDTO> result = (List<ClientDTO>) clientServiceImpl.getClientByName(name);
//
//        assertNotNull(result);
//        assertEquals(expectedDTOs.size(), result.size());
//        assertEquals(expectedDTOs.get(0).getName(), result.get(0).getName());
//        assertEquals(expectedDTOs.get(1).getName(), result.get(1).getName());
//
//        verify(clientRepository, times(1)).findByNameContainingIgnoreCase(name);
//        verify(modelMapper, times(1)).map(client1, ClientDTO.class);
//        verify(modelMapper, times(1)).map(client2, ClientDTO.class);
//    }
//
//    @Test
//    void testGetClientByName_ShouldThrowException_WhenNoClientsFound() {
//        String name = "NonExistentName";
//
//        when(clientRepository.findByNameContainingIgnoreCase(name)).thenReturn(Collections.emptyList());
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            clientServiceImpl.getClientByName(name);
//        });
//
//        assertEquals("No clients found with the given name", exception.getMessage());
//
//        verify(clientRepository, times(1)).findByNameContainingIgnoreCase(name);
//    }
//
//}
