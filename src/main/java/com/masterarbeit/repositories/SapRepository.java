package com.masterarbeit.repositories;

import com.masterarbeit.entities.sap;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jan-philippheinrich on 22.08.17.
 */
public interface SapRepository extends JpaRepository<sap, Integer> {
}
