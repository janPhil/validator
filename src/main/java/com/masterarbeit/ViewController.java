package com.masterarbeit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 13.04.2017.
 */
@Controller
public class ViewController {

    private PatientRepository patientRepository;
    private PatientAnonymRepository patientAnonymRepository;

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
    public String compare(Model theModel) throws IllegalAccessException {

        ComparePatients compareAll = new ComparePatients();
        List<Map<String, Double>> results = compareAll.doCompare(patientRepository.findAll(), patientAnonymRepository.findAll());

        theModel.addAttribute("patients", patientRepository.findAll());
        theModel.addAttribute("patientsAnonym", patientAnonymRepository.findAll());

        return "compare";
    }

    @RequestMapping("/compareSelected")
    public String compareSelected(Model model, @RequestParam(name="pid") List<String> selection){

        List<Patient> list = new ArrayList<>();

        for ( String s : selection){
            System.out.println(s);
            list.add(patientRepository.findOne(Integer.parseInt(s)));
        }
        model.addAttribute("patients",list);

        return "compareSelected";
    }

    @RequestMapping("/compareOne")
    public String compareOne(Model model, @RequestParam(name="pid") String selection) throws IllegalAccessException {
        CompareSelected compareSelected =new CompareSelected();


        Patient p = patientRepository.findOne(Integer.parseInt(selection));
        List<Patient_anonym> list = patientAnonymRepository.findAll();
        compareSelected.compareOne(p, list);

        model.addAttribute("patient",p);

        return "compareOne";
    }




}
