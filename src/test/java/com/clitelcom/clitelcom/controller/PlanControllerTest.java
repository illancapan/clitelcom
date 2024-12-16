package com.clitelcom.clitelcom.controller;

import com.clitelcom.clitelcom.dto.PlanDTO;
import com.clitelcom.clitelcom.model.entity.Plan;
import com.clitelcom.clitelcom.service.PlanService;
import com.clitelcom.clitelcom.service.PlanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlanController.class)
class PlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PlanServiceImpl planService; // Mock del servicio

    @InjectMocks
    private PlanController planController; // Inyectamos el mock del servicio en el controlador

    @Test
    void getAllPlans_ShouldReturnListOfPlans() throws Exception {
        List<PlanDTO> mockPlans = Arrays.asList(
                new PlanDTO(1L, "Plan Básico", 10.0, true, null),
                new PlanDTO(2L, "Plan Premium", 20.0, true, null)
        );
        when(planService.getAllPlans()).thenReturn(mockPlans);

        mockMvc.perform(get("/planes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))  // Asegúrate de que la respuesta es correcta
                .andExpect(jsonPath("$[0].name", is("Plan Básico")))
                .andExpect(jsonPath("$[1].name", is("Plan Premium")));
    }

    @Test
    void addPlan_ShouldCreateNewPlan() throws Exception {
        PlanDTO mockPlanDTO = new PlanDTO(1L, "Plan Básico", 10.0, true, null);
        when(planService.addPlan(any(Plan.class))).thenReturn(mockPlanDTO);

        mockMvc.perform(post("/planes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Plan Básico\",\"price\":10.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))  // Verifica que el id está correctamente asignado
                .andExpect(jsonPath("$.name", is("Plan Básico")))
                .andExpect(jsonPath("$.price", is(10.0)));
    }

    @Test
    void getPlanById_ShouldReturnPlan() throws Exception {
        PlanDTO mockPlanDTO = new PlanDTO(1L, "Plan Básico", 10.0, true, null);
        when(planService.getPlanById(1L)).thenReturn(mockPlanDTO);

        mockMvc.perform(get("/planes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))  // Verifica que la id es correcta
                .andExpect(jsonPath("$.name", is("Plan Básico")))
                .andExpect(jsonPath("$.price", is(10.0)));
    }

    @Test
    void deactivatePlan_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/planes/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(planService).deactivatePlan(1L);
    }
}
