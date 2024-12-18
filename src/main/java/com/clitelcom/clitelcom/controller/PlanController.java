package com.clitelcom.clitelcom.controller;


import com.clitelcom.clitelcom.dto.PlanDTO;
import com.clitelcom.clitelcom.model.entity.Plan;
import com.clitelcom.clitelcom.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/planes")
public class PlanController {

    private final PlanService planService;

    @GetMapping
    public ResponseEntity<List<PlanDTO>> getAllPlans() {
        List<PlanDTO> plans = planService.getAllPlans();
        return ResponseEntity.status(200).build();
    }

    @PostMapping
    public ResponseEntity<PlanDTO> addPlan(@RequestBody PlanDTO planDTO) {
        PlanDTO newPlan = planService.addPlan(planDTO);
        return ResponseEntity.status(201).body(newPlan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> getPLanById(@PathVariable Long id) {
        PlanDTO plan = planService.getPlanById(id);
        return ResponseEntity.ok(plan);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> deactivatePlan(@PathVariable Long id) {
        planService.deactivatePlan(id);
        return ResponseEntity.noContent().build();
    }
}
