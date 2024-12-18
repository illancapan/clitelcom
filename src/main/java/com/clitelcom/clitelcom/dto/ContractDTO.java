package com.clitelcom.clitelcom.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {

     private Long id;

     @JsonManagedReference
     private ClientDTO client;

     @JsonManagedReference
     private PlanDTO plan;

     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate startDate;

     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate endDate;

     private Boolean isActive;
}
