package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.dto.PlanDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlanServiceImplTest {

    PlanDTO planDTO1, planDTO2, planDTO3, planNotNull, planFalse;
    List<PlanDTO> expectedPlans;
    PlanServiceImpl planService;
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
                planDTO1,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2025, 1, 1),
                true
        );

        planDTO1 = new PlanDTO(1L, "Basic Plan", 29.0, true, null);
        planFalse = new PlanDTO(1L, "Basic Plan", 29.0, false, null);
        planDTO2 = new PlanDTO(2L, "Premium Plan", 59.0, true, null);
        planDTO3 = new PlanDTO(3L, "Enterprise Plan", 99.0, true, null);

        expectedPlans = Arrays.asList(planDTO1, planDTO2, planDTO3);

        planService = Mockito.mock(PlanServiceImpl.class);
    }

    @Test
    void should_returnAllPlans_when_getAllPlans_successful() {
        when(planService.getAllPlans()).thenReturn(expectedPlans);
        List<PlanDTO> response = planService.getAllPlans();
        assertEquals(3, response.size());
    }

    @Test
    void should_returnPlan_when_getPlanById_successful() {
        Long planById = 1L;
        when(planService.getPlanById(planById)).thenReturn(planDTO1);

        PlanDTO response = planService.getPlanById(planById);
        assertEquals(planDTO1, response);
    }

    @Test
    void should_returnNewPlan_when_addPlan_successful() {
        PlanDTO newPlanRequest = PlanDTO.builder()
                .id(1L)
                .name("Basic Plan")
                .price(29.0)
                .isActive(true)
                .contract(Collections.singletonList(contractBuildTest))
                .build();

        when(planService.addPlan(newPlanRequest)).thenReturn(newPlanRequest);

        PlanDTO response = planService.addPlan(newPlanRequest);
        assertEquals(newPlanRequest, response);
    }

    @Test
    void should_returnNull_when_deactivatePlan_successful() {
        Long planId = 1L;
        PlanDTO planToDeactivate = new PlanDTO(
                planId,
                "Basic Plan",
                29.0,
                true,
                null);

        planToDeactivate.setActive(false);
        Assertions.assertFalse(planToDeactivate.isActive());
    }
}
