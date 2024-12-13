package com.clitelcom.clitelcom.controller;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        clientDTO = new ClientDTO(
                1L,
                "Igor",
                "12345678-9",
                "Calle siempre viva",
                LocalDate.now(),
                null
        );
    }

    @Test
    void testCreateClient_ShouldReturnCreatedClientDTO() throws Exception {
        when(clientService.createClient(any(ClientDTO.class))).thenReturn(clientDTO);

        mockMvc.perform(post("/clients")
                        .contentType("application/json")
                        .content("{\"name\":\"igor\",\"run\":\"123456789\",\"address\":\"Calle siempre viva\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Igor"))
                .andExpect(jsonPath("$.run").value("12345678-9"));
    }
}
