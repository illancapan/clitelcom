package com.clitelcom.clitelcom.repository;

import com.clitelcom.clitelcom.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRespository extends JpaRepository<Client,Long> {
}
