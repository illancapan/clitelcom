package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.repository.ClientRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRespository clientRespository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    private Client client;
    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        client = new Client(
                1L,
                "igor",
                "12345678",
                "Calle siempre viva",
                LocalDate.now(),
                null);

        clientDTO = new ClientDTO(
                1L,
                "igor",
                "12345678",
                "Calle siempre viva",
                LocalDate.now(),
                null);
    }

    @Test
    void testCreateClient_ShouldReturnClientDTO_WhenClientIsCreated() {
        // Configura los mocks
        when(clientRespository.save(any(Client.class))).thenReturn(client);
        when(modelMapper.map(clientDTO, Client.class)).thenReturn(client);
        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);

        ClientDTO result = clientServiceImpl.createClient(clientDTO);

        assertNotNull(result);
        assertEquals(clientDTO.getName(), result.getName());
        assertEquals(clientDTO.getRun(), result.getRun());

        verify(clientRespository, times(1)).save(any(Client.class));
        verify(modelMapper, times(1)).map(clientDTO, Client.class);
        verify(modelMapper, times(1)).map(client, ClientDTO.class);
    }



    @Test
    void testUpdateClient_ShouldReturnUpdatedClientDTO_WhenClientIsUpdated() {

    }




    @Test
    void testGetClientClientDTO_WhenUserExists() {
        // Configura los mocks
        when(clientRespository.findById(1L)).thenReturn(Optional.of(client));
        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);

        ClientDTO result = clientServiceImpl.getClientByID(1L);

        assertNotNull(result);
        assertEquals(clientDTO.getId(), result.getId());
        assertEquals(clientDTO.getName(), result.getName());
        assertEquals(clientDTO.getRun(), result.getRun());

        verify(clientRespository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(client, ClientDTO.class);
    }
}
