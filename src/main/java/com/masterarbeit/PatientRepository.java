package com.masterarbeit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jan on 12.04.2017.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
