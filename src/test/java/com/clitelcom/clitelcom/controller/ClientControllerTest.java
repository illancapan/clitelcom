package com.clitelcom.clitelcom.controller;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.service.ClientServiceImpl;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.model.entity.Contract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Mock
    private ClientServiceImpl clientServiceImpl;

    @InjectMocks
    private ClientController clientController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createClient_ShouldReturnCreatedStatus() throws Exception {
        // Arrange: Creando los DTO de prueba
        Contract contract = new Contract();
        contract.setId(1L);
        contract.setId(1L);

        Client client = new Client();
        client.setName("Igor");
        client.setRun("12345678-9");
        client.setAddress("Calle siempre viva");
        client.setBirthDate(LocalDate.of(2024, 12, 13));

        // Simulando la creación del cliente en el servicio
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Igor");
        clientDTO.setRun("12345678-9");
        clientDTO.setAddress("Calle siempre viva");
        clientDTO.setBirthDate(LocalDate.of(2024, 12, 13));

        ClientDTO createdClientDTO = new ClientDTO();
        createdClientDTO.setName("Igor");
        createdClientDTO.setRun("12345678-9");
        createdClientDTO.setAddress("Calle siempre viva");
        createdClientDTO.setBirthDate(LocalDate.of(2024, 12, 13));

        when(clientServiceImpl.createClient(clientDTO)).thenReturn(createdClientDTO);

        // Act & Assert: Realizando la petición POST y verificando los resultados
        mockMvc.perform(post("/clients")
                        .contentType("application/json")
                        .content("{"
                                + "\"name\":\"Igor\","
                                + "\"run\":\"12345678-9\","
                                + "\"address\":\"Calle siempre viva\","
                                + "\"birthDate\":\"2024-12-13\","
                                + "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Igor"))
                .andExpect(jsonPath("$.run").value("12345678-9"))
                .andExpect(jsonPath("$.address").value("Calle siempre viva"))
                .andExpect(jsonPath("$.birthDate").value("2024-12-13"));
    }
}
