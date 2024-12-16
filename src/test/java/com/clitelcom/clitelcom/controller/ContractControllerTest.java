package com.clitelcom.clitelcom.controller;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.dto.PlanDTO;
import com.clitelcom.clitelcom.service.ContractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ContractControllerTest {

    @Mock
    private ContractService contractService;

    @InjectMocks
    private ContractController contractController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Configuramos MockMvc para las pruebas
        mockMvc = MockMvcBuilders.standaloneSetup(contractController).build();
    }

    @Test
    void getAllContracts_ShouldReturnListOfContracts() throws Exception {
        // Arrange
        PlanDTO planA = new PlanDTO(1L, "Plan A", 100.0, true, null);
        PlanDTO planB = new PlanDTO(2L, "Plan B", 150.0, true, null);

        ClientDTO clientA = new ClientDTO(
                1L,
                "Client A",
                "12345678-9",
                null
                ,LocalDate.now()
                , "clientA@example.com");
        ClientDTO clientB = new ClientDTO(
                2L,
                "Client b",
                "12345678-9",
                null
                ,LocalDate.now()
                , "clientA@example.com");

        List<ContractDTO> mockContracts = Arrays.asList(
                new ContractDTO(
                        1L,
                        clientA,
                        planA,
                        LocalDate.now(),
                        LocalDate.now().plusMonths(1),
                        true),
                new ContractDTO(
                        2L,
                        clientB,
                        planB,
                        LocalDate.now(),
                        LocalDate.now().plusMonths(2),
                        true)
        );
        when(contractService.getAllContracts()).thenReturn(mockContracts);

        // Act & Assert
        mockMvc.perform(get("/contracts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))  // Verifica que la respuesta tiene 2 contratos
                .andExpect(jsonPath("$[0].client.name", is("Client A")))  // Verifica cliente
                .andExpect(jsonPath("$[0].plan.name", is("Plan A")))    // Verifica plan
                .andExpect(jsonPath("$[1].client.name", is("Client B")))  // Verifica cliente
                .andExpect(jsonPath("$[1].plan.name", is("Plan B")));   // Verifica plan
    }

    @Test
    void createContract_ShouldCreateNewContract() throws Exception {
        // Arrange
        PlanDTO mockPlanDTO = new PlanDTO(1L, "Plan A", 100.0, true, null);
        ClientDTO mockClientDTO = new ClientDTO(1L, "Client A", "clientA@example.com");

        ContractDTO mockContractDTO = new ContractDTO(1L, mockClientDTO, mockPlanDTO, LocalDate.now(), LocalDate.now().plusMonths(1), true);
        when(contractService.createContract(any(ContractDTO.class))).thenReturn(mockContractDTO);

        // Act & Assert
        mockMvc.perform(post("/contracts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"client\":{\"id\":1,\"name\":\"Client A\",\"email\":\"clientA@example.com\"}, \"plan\":{\"id\":1,\"name\":\"Plan A\",\"price\":100.0,\"isActive\":true}, \"startDate\":\"2024-12-16\", \"endDate\":\"2025-01-16\", \"isActive\":true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))  // Verifica que el id se asignó correctamente
                .andExpect(jsonPath("$.client.name", is("Client A")))  // Verifica cliente
                .andExpect(jsonPath("$.plan.name", is("Plan A")))  // Verifica plan
                .andExpect(jsonPath("$.startDate").exists())  // Verifica que startDate está presente
                .andExpect(jsonPath("$.endDate").exists())    // Verifica que endDate está presente
                .andExpect(jsonPath("$.isActive", is(true))); // Verifica que isActive es verdadero
    }

    @Test
    void deactivateContract_ShouldReturnNoContent() throws Exception {
        // Act & Assert
        mockMvc.perform(put("/contracts/1/deactivate"))
                .andExpect(status().isNoContent());

        // Verifica que el servicio fue llamado
        verify(contractService).desactiveContract(1L);
    }

    @Test
    void getDiscount_ShouldReturnDiscount() throws Exception {
        // Arrange
        Double mockDiscount = 0.1;  // Descuento del 10%
        when(contractService.calculateDiscount(1L)).thenReturn(mockDiscount);

        // Act & Assert
        mockMvc.perform(get("/contracts/1/discount"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(0.1)));
    }
}