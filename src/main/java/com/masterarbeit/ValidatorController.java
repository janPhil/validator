package com.masterarbeit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
