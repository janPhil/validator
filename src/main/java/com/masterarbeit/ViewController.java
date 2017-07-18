package com.masterarbeit;

import com.masterarbeit.anonymize.AnonymizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 13.04.2017.
 */
@Controller
public class ViewController {

    private PatientRepository patientRepository;
    private PatientAnonymRepository patientAnonymRepository;
    private PatientAnonymizedRepository patientAnonymizedRepository;

    @Autowired
    public ViewController(PatientRepository patientRepository, PatientAnonymRepository patientAnonymRepository, PatientAnonymizedRepository patientAnonymizedRepository){
        this.patientRepository = patientRepository;
        this.patientAnonymRepository = patientAnonymRepository;
        this.patientAnonymizedRepository = patientAnonymizedRepository;
    }

    @RequestMapping("/")
    public String index(Model theModel){

        theModel.addAttribute("patients", patientRepository.findAll());
        theModel.addAttribute("patientsAnonym", patientAnonymRepository.findAll());

        return "index";
    }


    @RequestMapping("/compare")
    public String compare(Model theModel) throws IllegalAccessException {

        ComparePatients compare = new ComparePatients();
        compare.comparePatients(patientRepository.findAll(), patientAnonymRepository.findAll());

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

    @RequestMapping("/anonymize")
    public String anonymize(Model model) throws IOException {

        AnonymizeService anonymizeService = new AnonymizeService();
        ArrayList<Patient_anonymized> p = anonymizeService.anonymizePatients(patientRepository.findAll());
        patientAnonymizedRepository.save(p);
        System.out.println("saved :)");
        ComparePatients_anonym compare = new ComparePatients_anonym();
        compare.comparePatientsAnonymized(patientRepository.findAll(), patientAnonymizedRepository.findAll());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("patientsAnonymized", patientAnonymizedRepository.findAll());

        return "anonymize";
    }

    @RequestMapping("/deleteAnonymizedPatients")
    public String deleteAnonymizedPatients(Model model){
        patientAnonymizedRepository.deleteAll();
        model.addAttribute("patients", patientRepository.findAll());



        return "anonymize";
    }



}
