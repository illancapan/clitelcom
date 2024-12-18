package com.clitelcom.clitelcom.controller;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.service.ClientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;


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
    void should_return404_when_updateClient_notFound() {
        Long clientId = 1L;
        Mockito.when(clientService.updateClient(clientId, clientDto)).thenReturn(null);
        ResponseEntity<ClientDTO> actual = clientController.updateClient(clientId, clientDto);
        Assertions.assertEquals(404, actual.getStatusCode().value());
        Assertions.assertNull(actual.getBody());
    }

    @Test
    void should_return200_when_getClientById_successful() {
        Long clientId = 1L;
        Mockito.when(clientService.getClientById(clientId)).thenReturn(expectedBody);
        ResponseEntity<ClientDTO> actual = clientController.getClientById(clientId);
        Assertions.assertEquals(200, actual.getStatusCode().value());
        Assertions.assertEquals(expectedBody, actual.getBody());
    }

    @Test
    void should_return200_when_getClientByName_successful() {
        String clientName = "John Doe";
        Mockito.when(clientService.getClientByName(clientName)).thenReturn(expectedBody);
        ResponseEntity<ClientDTO> actual = clientController.getClientByName(clientName);
        Assertions.assertEquals(200, actual.getStatusCode().value());
        Assertions.assertEquals(expectedBody, actual.getBody());
    }

    @Test
    void should_return404_when_deleteClient_notFound() {
        Long clientId = 1L;
        Mockito.when(clientService.deleteClientById(clientId)).thenReturn(null);
        ResponseEntity<ClientDTO> actual = clientController.deleteClientBYId(clientId);
        Assertions.assertEquals(404, actual.getStatusCode().value());
        Assertions.assertNull(actual.getBody());
    }
}



//TEST REALIZADO CON MATI

//class ClientControllerTest {
//
//    ClientDTO clientDto;
//    ClientDTO expectedBody;
//
//    ClientServiceImpl clientService;
//
//    ClientController clientController;
//
//    @BeforeEach
//    void setUp() {
//
//        clientDto = new ClientDTO();
//
//        expectedBody = new ClientDTO();
//
//        clientService = Mockito.mock(ClientServiceImpl.class);
//
//        clientController = new ClientController(clientService);
//
//    }
//
//    @Test
//    void should_return201() {
//
//        Mockito.when(clientService.createClient(clientDto)).thenReturn(expectedBody);
//
//        //ACTUAL
//        ResponseEntity<ClientDTO> actual = clientController.createClient(clientDto);
//
////        ASSERT
//        Assertions.assertEquals(201, actual.getStatusCode().value());
//        Assertions.assertNotNull(actual.getBody());
//
//    }
//}