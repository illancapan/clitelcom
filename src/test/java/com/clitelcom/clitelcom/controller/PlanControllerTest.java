package com.clitelcom.clitelcom.controller;

import com.clitelcom.clitelcom.dto.PlanDTO;
import com.clitelcom.clitelcom.service.PlanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PlanControllerTest {

    PlanController planController;
    PlanServiceImpl planService;
    PlanDTO planDTO1, planDTO2, planDTO3;
    List<PlanDTO> expectedPlans;

    @BeforeEach
    void setUp() {
        planService = Mockito.mock(PlanServiceImpl.class);
        planController = new PlanController(planService);

        planDTO1 = new PlanDTO(1L, "Basic Plan", 29.0, true, null);
        planDTO2 = new PlanDTO(2L, "Premium Plan", 59.0, true, null);
        planDTO3 = new PlanDTO(3L, "Enterprise Plan", 99.0, true, null);

        expectedPlans = Arrays.asList(planDTO1, planDTO2, planDTO3);
    }

    @Test
    void should_return200_when_getAllPlans_successful() {
        Mockito.when(planService.getAllPlans()).thenReturn(expectedPlans);

        ResponseEntity<List<PlanDTO>> response = planController.getAllPlans();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedPlans, response.getBody());
    }

    @Test
    void should_return201_when_addPlan_successful() {
        PlanDTO newPlanRequest = PlanDTO.builder()
                .id(1L)
                .name("Basic Plan")
                .price(29.0)
                .isActive(true)
                .build();

        Mockito.when(planService.addPlan(newPlanRequest)).thenReturn(newPlanRequest);

        ResponseEntity<PlanDTO> response = planController.addPlan(newPlanRequest);

        assertEquals(201, response.getStatusCode().value());
        assertEquals(newPlanRequest, response.getBody());
    }

    @Test
    void should_return200_when_getPlanById_successful() {
        Long planId = 1L;
        Mockito.when(planService.getPlanById(planId)).thenReturn(planDTO1);

        ResponseEntity<PlanDTO> response = planController.getPLanById(planId);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(planDTO1, response.getBody());
    }

    @Test
    void should_return204_when_deactivatePlan_successful() {
        Long planId = 1L;

        Mockito.doNothing().when(planService).deactivatePlan(planId);

        ResponseEntity<Void> response = planController.deactivatePlan(planId);

        assertEquals(204, response.getStatusCode().value());
    }
}
