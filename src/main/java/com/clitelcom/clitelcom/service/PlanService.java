package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.PlanDTO;
import com.clitelcom.clitelcom.model.entity.Plan;

import java.util.List;

public interface PlanService {

    List<PlanDTO> getAllPlans();
    PlanDTO addPlan(Plan plan);
    void deactivatePlan(Long planId);
    PlanDTO getPlanById(Long id);
}
