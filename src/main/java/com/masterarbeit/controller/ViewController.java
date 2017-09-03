package com.masterarbeit.controller;

import com.masterarbeit.dbOperations.DatabaseOperations;
import com.masterarbeit.compare.CompareService;
import com.masterarbeit.entities.Patient;
import com.masterarbeit.entities.Patient_anonym;
import com.masterarbeit.repositories.PatientAnonymRepository;
import com.masterarbeit.repositories.PatientRepository;
import com.masterarbeit.repositories.QupSapRepository;
import com.masterarbeit.repositories.SapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Jan on 13.04.2017.
 */
@Controller
public class ViewController {

    private PatientRepository patientRepository;
    private PatientAnonymRepository patientAnonymRepository;
    private SapRepository sapRepository;
    private QupSapRepository qupSapRepository;

    @Autowired
    public ViewController(PatientRepository patientRepository, PatientAnonymRepository patientAnonymRepository, SapRepository sapRepository, QupSapRepository qupSapRepository){
        this.patientRepository = patientRepository;
        this.patientAnonymRepository = patientAnonymRepository;
        this.sapRepository = sapRepository;
        this.qupSapRepository = qupSapRepository;
    }

    @RequestMapping("/")
    public String index(Model theModel){

        theModel.addAttribute("patients", patientRepository.findAll());
        theModel.addAttribute("patientsAnonym", patientAnonymRepository.findAll());

        return "index";
    }

    @RequestMapping("/compare")
    public String compare(Model theModel) throws IllegalAccessException {

        CompareService compareService = new CompareService();
        Map<Integer,Double> results = compareService.compareOneOnOne(patientRepository.findAll(), patientAnonymRepository.findAll());
        double table = compareService.resultForTable(results);
        theModel.addAttribute("patients", patientRepository.findAll());
        theModel.addAttribute("patientsAnonym", patientAnonymRepository.findAll());
        theModel.addAttribute("result", results);
        theModel.addAttribute("table", table);

        return "compare";
    }

    @RequestMapping("/compareSelected")
    public String compareSelected(Model theModel, @RequestParam(name="pid") List<String> selection){

        List<Patient> list = new ArrayList<>();

        for ( String s : selection){
            System.out.println(s);
            list.add(patientRepository.findOne(Integer.parseInt(s)));
        }
        theModel.addAttribute("patients",list);

        return "compareSelected";
    }

    @RequestMapping("/compareOne")
    public String compareOne(Model model, @RequestParam(name="pid") String selection) throws IllegalAccessException {

        CompareService compareService = new CompareService();
        Patient p = patientRepository.findOne(Integer.parseInt(selection));
        Patient_anonym pa = patientAnonymRepository.findOne(Integer.parseInt(selection));
        HashMap<String, Double> results = compareService.compareOne(p, pa);
        model.addAttribute("patient",p);
        model.addAttribute("patientAnonym", pa);
        model.addAttribute("results", results);

        return "compareOne";
    }

    @RequestMapping("/comparesap")
    public String compareSAP(Model model) throws IllegalAccessException {
        CompareService compareService = new CompareService();
        Map<Integer, Double> results = compareService.doCompareSAP(sapRepository.findAll(), qupSapRepository.findAll());

        return "sap";
    }

    @RequestMapping("/selectTables")
    public String tablenames(Model model) throws Exception {

        DatabaseOperations DatabaseOperations = new DatabaseOperations();
        List<String> tablenames = DatabaseOperations.readDBTables();
        System.out.println(Arrays.toString(tablenames.toArray()));
        model.addAttribute("tablenames",tablenames);
        return "showTables";
    }

    @RequestMapping("/showSelectedTables")
    public String schowSelectedTables(Model model, @RequestParam(name="table") List<String> selection) throws Exception {

        DatabaseOperations databaseOperations = new DatabaseOperations();
        List<String> selectedTables = new ArrayList<>();

        List<ArrayList> tableOne = databaseOperations.getTable(selection.get(0));
        List<ArrayList> tableTwo = databaseOperations.getTable(selection.get(1));


        for (String s: selection) {
            selectedTables.add(s);
        }
        model.addAttribute("selectedTables",selectedTables);
        model.addAttribute("tableOne", tableOne);
        model.addAttribute("tableTwo", tableTwo);


        return "showSelectedTables";
    }

    @RequestMapping("testMail")
    public String testMail(){

        String mailOne = "heinrich.jan@googlemail.com";
        String mailTwo = "heimlich.karl@gmail.com";
        CompareService compareService = new CompareService();
        compareService.compareAttribute(mailOne,mailTwo);

        return "test";

    }

}
