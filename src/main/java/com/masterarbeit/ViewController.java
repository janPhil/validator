package com.masterarbeit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jan on 13.04.2017.
 */
@Controller
public class ViewController {

    PatientRepository patientRepository;
    PatientAnonymRepository patientAnonymRepository;
    @Autowired
    public ViewController(PatientRepository patientRepository, PatientAnonymRepository patientAnonymRepository){
        this.patientRepository = patientRepository;
        this.patientAnonymRepository = patientAnonymRepository;
    }

    @RequestMapping("/")
    public String index(Model theModel){

        theModel.addAttribute("patients", patientRepository.findAll());
        theModel.addAttribute("patientsAnonym", patientAnonymRepository.findAll());

        return "index";
    }

    @RequestMapping("/compare")
    public String compare(Model theModel){

        CompareService compareService = new CompareService();
        compareService.comparePatients(patientRepository.findAll(), patientAnonymRepository.findAll());

        theModel.addAttribute("patients", patientRepository.findAll());
        theModel.addAttribute("patientsAnonym", patientAnonymRepository.findAll());

        return "compare";
    }

}
