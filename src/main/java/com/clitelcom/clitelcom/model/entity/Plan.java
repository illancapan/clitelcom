package com.clitelcom.clitelcom.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @ElementCollection
    private List<String> serviceFacilitated;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL,orphanRemoval = true )
    private List<Contract> contracts;
}
