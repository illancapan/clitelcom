package com.clitelcom.clitelcom.controller;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.dto.PlanDTO;
import com.clitelcom.clitelcom.service.ContractServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ContractControllerTest {

    ContractController contractController;
    ContractServiceImpl contractService;
    ContractDTO contractDTO1, contractDTO2, contractDTO3;
    List<ContractDTO> expectedContracts;


    @BeforeEach
    void setUp() {
        contractService = Mockito.mock(ContractServiceImpl.class);
        contractController = new ContractController(contractService);

        ClientDTO clientDTO = ClientDTO.builder()
                .id(1L)
                .name("Juan Doe")
                .build();

        PlanDTO planDTO = PlanDTO.builder()
                .id(1L)
                .name("Basic Plan")
                .price(29.0)
                .isActive(true)
                .build();

        contractDTO1 = ContractDTO.builder()
                .id(1L)
                .client(clientDTO)
                .plan(planDTO)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(12))
                .isActive(true)
                .build();

        contractDTO2 = ContractDTO.builder()
                .id(2L)
                .client(clientDTO)
                .plan(planDTO)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(12))
                .isActive(true)
                .build();

        contractDTO3 = ContractDTO.builder()
                .id(3L)
                .client(clientDTO)
                .plan(planDTO)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(12))
                .isActive(true)
                .build();

        expectedContracts = Arrays.asList(contractDTO1, contractDTO2, contractDTO3);
    }

    @Test
    void should_return200_when_getAllContracts_successful() {
        Mockito.when(contractService.getAllContracts()).thenReturn(expectedContracts);

        ResponseEntity<List<ContractDTO>> response = contractController.getAllContracts();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedContracts, response.getBody());
    }

    @Test
    void should_return201_when_createContract_successful() {
        ClientDTO clientDTO = ClientDTO.builder()
                .id(1L)
                .name("Juan Doe")
                .build();

        PlanDTO planDTO = PlanDTO.builder()
                .id(1L)
                .name("Basic Plan")
                .price(29.0)
                .isActive(true)
                .build();

        ContractDTO newContractRequest = ContractDTO.builder()
                .id(4L)
                .client(clientDTO)
                .plan(planDTO)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(12))
                .isActive(true)
                .build();

        Mockito.when(contractService.createContract(newContractRequest)).thenReturn(newContractRequest);

        ResponseEntity<ContractDTO> response = contractController.createContract(newContractRequest);

        assertEquals(201, response.getStatusCode().value());
        assertEquals(newContractRequest, response.getBody());
    }


    @Test
    void should_return204_when_deactivateContract_successful() {
        Long contractId = 1L;
        Mockito.doNothing().when(contractService).deactiveContract(contractId);

        ResponseEntity<Void> response = contractController.deactivateContract(contractId);

        assertEquals(204, response.getStatusCode().value());
    }

    @Test
    void should_return200_when_getDiscount_successful() {
        Long clientId = 1L;
        Double expectedDiscount = 0.1;
        Mockito.when(contractService.calculateDiscount(clientId)).thenReturn(expectedDiscount);

        ResponseEntity<Double> response = contractController.getDiscount(clientId);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedDiscount, response.getBody());
    }

}
