package com.clitelcom.clitelcom.controller;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.service.ClientServiceImpl;
import com.clitelcom.clitelcom.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ClientControllerTest {

    ClientDTO clientDto;
    ClientDTO expectedBody;

    ClientServiceImpl clientService;

    ClientController clientController;

    @BeforeEach
    void setUp() {

        clientDto = new ClientDTO();

        expectedBody = new ClientDTO();

        clientService = Mockito.mock(ClientServiceImpl.class);

        clientController = new ClientController(clientService);

    }

    @Test
    void should_return201() {

        Mockito.when(clientService.createClient(clientDto)).thenReturn(expectedBody);

        //ACTUAL
        ResponseEntity<ClientDTO> actual = clientController.createClient(clientDto);

//        ASSERT
        Assertions.assertEquals(201, actual.getStatusCode().value());
        Assertions.assertNotNull(actual.getBody());

    }
}