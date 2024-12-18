package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.controller.PlanController;
import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.dto.PlanDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PlanServiceImplTest {

    PlanDTO planDTO1, planDTO2, planDTO3, planDTO, planNull, planNotNull;
    List<PlanDTO> expectedPlans;
    PlanServiceImpl planService;
    PlanController planController;
    ContractDTO contractBuildTest;

    @BeforeEach
    void setUp() {
        ClientDTO clientBuildTest = ClientDTO.builder()
                .id(1L)
                .name("Pepe")
                .run("12345678-9")
                .address("Siempre viva")
                .birthDate(LocalDate.now())
                .contractsDTO(Collections.emptyList())
                .build();

        contractBuildTest = new ContractDTO(
                1L,
                clientBuildTest,
                planDTO,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2025, 1, 1),
                true
        );

        planNull = new PlanDTO(null, null, null, true, null);

        planDTO1 = new PlanDTO(1L, "Basic Plan", 29.0, true, null);
        planDTO2 = new PlanDTO(2L, "Premium Plan", 59.0, true, null);
        planDTO3 = new PlanDTO(3L, "Enterprise Plan", 99.0, true, null);

        planNotNull = PlanDTO.builder()
                .id(1L)
                .name("Basic Plan")
                .price(29.0)
                .isActive(true)
                .contract(Collections.singletonList(contractBuildTest))
                .build();

        expectedPlans = Arrays.asList(planDTO1, planDTO2, planDTO3);

        planService = Mockito.mock(PlanServiceImpl.class);
        planController = new PlanController(planService);
    }

    @Test
    void should_return200_when_getClientAll_successful() {
        Mockito.when(planService.getAllPlans()).thenReturn(expectedPlans);
        ResponseEntity<List<PlanDTO>> response = planController.getAllPlans();

        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void should_return201_when_addPlan_successful() {
        PlanDTO newPlanRequest = PlanDTO.builder()
                .id(1L)
                .name("Basic Plan")
                .price(29.0)
                .isActive(true)
                .contract(Collections.singletonList(contractBuildTest))
                .build();

        PlanDTO createdPlanResponse = PlanDTO.builder()
                .id(1L)
                .name("Basic Plan")
                .price(29.0)
                .isActive(true)
                .contract(Collections.singletonList(contractBuildTest))
                .build();

        Mockito.when(planService.addPlan(newPlanRequest)).thenReturn(createdPlanResponse);

        ResponseEntity<PlanDTO> response = planController.addPlan(newPlanRequest);

        Assertions.assertEquals(201, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(createdPlanResponse, response.getBody());
    }

    @Test
    void should_returnPlan_when_addPlan_successful() {
        PlanDTO newPlanRequest = PlanDTO.builder()
                .id(1L)
                .name("Basic Plan")
                .price(29.0)
                .isActive(true)
                .contract(Collections.singletonList(contractBuildTest))
                .build();

        PlanDTO createdPlanResponse = PlanDTO.builder()
                .id(1L)
                .name("Basic Plan")
                .price(29.0)
                .isActive(true)
                .contract(Collections.singletonList(contractBuildTest))
                .build();

        Mockito.when(planService.addPlan(newPlanRequest)).thenReturn(createdPlanResponse);

        ResponseEntity<PlanDTO> response = planController.addPlan(newPlanRequest);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(createdPlanResponse, response.getBody());
    }

    @Test
    void should_return200_when_getPlanById_successful() {
        Long planById = 1L;
        Mockito.when(planService.getPlanById(planById)).thenReturn(planDTO1);

        ResponseEntity<PlanDTO> response = planController.getPLanById(planById);

        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void should_returnNonNullPlan_when_getPlanById_successful() {
        Long planByIdNotNull = 1L;

        Mockito.when(planService.getPlanById(planByIdNotNull)).thenReturn(planNotNull);

        ResponseEntity<PlanDTO> response = planController.getPLanById(planByIdNotNull);

        Assertions.assertNotNull(response.getBody(), "The response body should not be null.");

        PlanDTO responseBody = response.getBody();
        Assertions.assertNotNull(response.getBody(), "The response body should not be null.");

        Assertions.assertNotNull(responseBody.getId(), "The ID should not be null.");
        Assertions.assertNotNull(responseBody.getName(), "The name should not be null.");
        Assertions.assertNotNull(responseBody.getPrice(), "The price should not be null.");
        Assertions.assertNotNull(responseBody.getContract(), "The contract list should not be null.");
    }

    @Test
    void should_return204_when_deactivatePlan_successful() {
        Long planId = 1L;

        Mockito.when(plan)

    }
}


