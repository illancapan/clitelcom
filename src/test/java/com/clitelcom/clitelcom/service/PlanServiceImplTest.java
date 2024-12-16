package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.PlanDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.model.entity.Contract;
import com.clitelcom.clitelcom.model.entity.Plan;
import com.clitelcom.clitelcom.repository.PlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlanServiceImplTest {

    @Mock
    private PlanRepository planRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PlanServiceImpl planService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPlans_ShouldReturnListOfPlans() {
        Client client1 = new Client(
                1L,
                "Client A",
                "RUN001",
                "Address A",
                LocalDate.of(1990, 1, 1),
                null);
        Client client2 = new Client(
                2L,
                "Client B",
                "RUN002",
                "Address B",
                LocalDate.of(1985, 5, 10),
                null);

        Plan plan1 = new Plan(
                1L,
                "Plan A",
                100.0,
                true,null
                );
        Plan plan2 = new Plan(
                2L,
                "Plan B",
                150.0,
                true,
                null);

        Contract contract1 = new Contract(
                1L,
                client1,
                plan1,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                true);
        Contract contract2 = new Contract(
                2L,
                client2,
                plan2,
                LocalDate.of(2023, 6, 1),
                LocalDate.of(2024, 5, 31),
                true);

        plan1.setContract(Arrays.asList(contract1));
        plan2.setContract(Arrays.asList(contract2));

        when(planRepository.findAll()).thenReturn(Arrays.asList(plan1, plan2));

        when(modelMapper.map(plan1, PlanDTO.class)).thenReturn(new PlanDTO(
                1L,
                "Plan A",
                100.0,
                true,
                null));
        when(modelMapper.map(plan2, PlanDTO.class)).thenReturn(new PlanDTO(
                2L,
                "Plan B",
                150.0,
                true,
                null));

        List<PlanDTO> plans = planService.getAllPlans();

        assertEquals(2, plans.size());
        assertEquals("Plan A", plans.get(0).getName());
        assertEquals("Plan B", plans.get(1).getName());

        verify(planRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(plan1, PlanDTO.class);
        verify(modelMapper, times(1)).map(plan2, PlanDTO.class);
    }

    @Test
    void getPlanById_ShouldReturnPlanDTO_WhenPlanExists() {
        Plan plan = new Plan(
                1L,
                "Plan A",
                100.0,
                true,
                null);
        PlanDTO planDTO = new PlanDTO(
                1L,
                "Plan A",
                100.0,
                true,
                null);

        when(planRepository.findById(1L)).thenReturn(java.util.Optional.of(plan));
        when(modelMapper.map(plan, PlanDTO.class)).thenReturn(planDTO);
        PlanDTO result = planService.getPlanById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Plan A", result.getName());

        verify(planRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(plan, PlanDTO.class);
    }

    @Test
    void getPlanById_ShouldReturnNull_WhenPlanDoesNotExist() {
        when(planRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        PlanDTO result = planService.getPlanById(1L);

        assertNull(result);

        verify(planRepository, times(1)).findById(1L);
        verify(modelMapper, times(0)).map(any(), any());
    }


}
