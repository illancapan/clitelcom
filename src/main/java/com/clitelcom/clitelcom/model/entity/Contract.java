package com.clitelcom.clitelcom.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contract {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @ManyToOne
     @JoinColumn(name = "client_id", nullable = false)
     private Client client;

     @ManyToOne
     @JoinColumn(name = "plan_id", nullable = false)
     private Plan plan;

     private LocalDate startDate;
     private LocalDate endDate;
     private boolean isActive;
}
