package com.masterarbeit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 13.04.2017.
 */
@Controller
public class ViewController {

    PatientRepository patientRepository;
    PatientAnonymRepository patientAnonymRepository;
    ValidatorController validatorController;

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

    @RequestMapping("/compareSelected")
    public String compareSelected(Model model, @RequestParam(name="pid") List<String> selection){

        List<Patient> list = new ArrayList<Patient>();

        for ( String s : selection){
            System.out.println(s);
            list.add(patientRepository.findOne(Integer.parseInt(s)));
        }
        model.addAttribute("patients",list);

        return "compareSelected";
    }


}
