package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.PlanDTO;

import java.util.List;

public interface PlanService {

    List<PlanDTO> getAllPlans();
    PlanDTO addPlan(PlanDTO plan);
    void deactivatePlan(Long planId);
    PlanDTO getPlanById(Long id);
}
