package com.masterarbeit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jan on 12.04.2017.
 */
@RestController
@RequestMapping(value = "/patients")
public class ValidatorController {

    PatientRepository patientRepository;

    @Autowired
    public ValidatorController(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @RequestMapping("/all")
    public List<Patient> getAll(){
        return patientRepository.findAll();
    }

    @RequestMapping("/create")
    public List<Patient> create(@RequestBody Patient patient){
        patientRepository.save(patient);
        return patientRepository.findAll();
    }

    @RequestMapping("/delete/{id}")
    public List<Patient> remove(@PathVariable int id){
        patientRepository.delete(id);
        return patientRepository.findAll();
    }
}
