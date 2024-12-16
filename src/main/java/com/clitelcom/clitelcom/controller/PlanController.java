package com.clitelcom.clitelcom.controller;


import com.clitelcom.clitelcom.dto.PlanDTO;
import com.clitelcom.clitelcom.model.entity.Plan;
import com.clitelcom.clitelcom.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planes")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping
    public ResponseEntity<List<PlanDTO>> getAllPlans(){
        List<PlanDTO> plans =planService.getAllPlans();
        return ResponseEntity.ok(plans);
    }

    @PostMapping
    public ResponseEntity<PlanDTO> addPlan(@RequestBody Plan plan){
        PlanDTO newPlan = planService.addPlan(plan);
        return ResponseEntity.status(201).body(newPlan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> getPLanById(@PathVariable Long id){
        PlanDTO plan = planService.getPlanById(id);
        return ResponseEntity.ok(plan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivatePlan(@PathVariable Long id){
        planService.deactivatePlan(id);
        return ResponseEntity.noContent().build();
    }
}
