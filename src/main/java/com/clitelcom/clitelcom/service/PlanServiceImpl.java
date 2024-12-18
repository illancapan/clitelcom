package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.PlanDTO;
import com.clitelcom.clitelcom.model.entity.Plan;
import com.clitelcom.clitelcom.repository.PlanRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PlanServiceImpl implements PlanService {

    private PlanRepository planRepository;
    private ModelMapper modelMapper;

    @Override
    public List<PlanDTO> getAllPlans() {
        List<Plan> plans = planRepository.findAll();
        return plans.stream()
                .map(plan -> modelMapper.map(plan, PlanDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlanDTO addPlan(PlanDTO planDTO) {
        Plan plan = modelMapper.map(planDTO, Plan.class);
        Plan newPlan = planRepository.save(plan);
        return modelMapper.map(newPlan,PlanDTO.class);
    }

    @Transactional
    @Override
    public void deactivatePlan(Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(()-> new RuntimeException("Cannot found whit id: " + planId));
        plan.setActive(!plan.isActive());
        planRepository.save(plan);
    }

    @Override
    public PlanDTO getPlanById(Long id) {
        Plan plan = planRepository.findById(id).orElse(null);
        if (plan == null) {
            return null;
        }
        return modelMapper.map(plan, PlanDTO.class);

    }
}
