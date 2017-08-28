package com.masterarbeit.controller;

import com.masterarbeit.entities.Patient;
import com.masterarbeit.repositories.PatientAnonymRepository;
import com.masterarbeit.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jan on 12.04.2017.
 */
@RestController
@RequestMapping(value = "/patients")
public class ValidatorController {

    private PatientRepository patientRepository;
    private PatientAnonymRepository patientAnonymRepository;

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


    @RequestMapping(value = "/compareSelected", method = RequestMethod.POST)
    public String compareSelected(@RequestParam(name="pid")  List<String> selection){


        for ( String s : selection){
            System.out.println(s);
        }

        return "test";
    }




}
