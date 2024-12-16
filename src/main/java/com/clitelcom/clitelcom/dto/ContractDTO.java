package com.clitelcom.clitelcom.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {

     private Long id;
     private ClientDTO client;
     private PlanDTO plan;

     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate startDate;

     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate endDate;

     private Boolean isActive;
}
