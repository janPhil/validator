package com.masterarbeit.compare;

import com.masterarbeit.entities.Patient;
import com.masterarbeit.entities.Patient_anonym;
import com.masterarbeit.entities.sap;
import com.masterarbeit.entities.qup_sap;

import java.lang.reflect.Field;
import java.util.*;


public class CompareService {


    private IntegerComp intCompare = new IntegerComp();
    private DateComp dateCompare = new DateComp(intCompare);
    private DoubleComp doubleCompare = new DoubleComp(intCompare);
    private StringComp stringCompare = new StringComp(intCompare);
    private BoolComp boolComp = new BoolComp();
    private Comparer comp = new Comparer(intCompare,doubleCompare,dateCompare,stringCompare, boolComp);


    public Map<Integer, Double> doCompare(List<Patient> patients, List<Patient_anonym> patient_anonym) throws IllegalAccessException {

        Iterator<Patient> it1 = patients.iterator();
        Iterator<Patient_anonym> it2 = patient_anonym.iterator();
        List<Map<String, Double>> results = new ArrayList<>();
        Map<Integer, Double> overallOutcome = new HashMap<>();

        while(it1.hasNext() && it2.hasNext()){

            Object patientOriginal = it1.next();
            Object patientAnonym = it2.next();

            Field[] fieldsPatientOriginal = patientOriginal.getClass().getDeclaredFields();
            Field[] fieldsPatientAnonym = patientAnonym.getClass().getDeclaredFields();
            Map<String, Double> result = new HashMap<>();

            for (int i=0; i<fieldsPatientOriginal.length; i++){
                fieldsPatientAnonym[i].setAccessible(true);
                fieldsPatientOriginal[i].setAccessible(true);
                double value = comp.compare(fieldsPatientOriginal[i].get(patientOriginal), fieldsPatientAnonym[i].get(patientAnonym),2.0);
                result.put(fieldsPatientOriginal[i].getName(), value);
            }

            Patient p = (Patient) patientOriginal;
            overallOutcome.put(p.getId(), getAbsolute(result));

            results.add(result);
        }
        System.out.println("----------Results:---------------");

        for (Object result : results) {
            HashMap<String, Double> temp = (HashMap<String, Double>) result;
            Set set = temp.entrySet();
            for (Object aSet : set) {
                Map.Entry me = (Map.Entry) aSet;
                System.out.println(me.getKey() + " : " + me.getValue());
            }
            System.out.println("-----------------------------");
        }

        return overallOutcome;
    }

    public Map<Integer, Double> doCompareSAP(List<sap> original, List<qup_sap> anonym) throws IllegalAccessException {

        Iterator<sap> it1 = original.iterator();
        Iterator<qup_sap> it2 = anonym.iterator();
        List<Map<String, Double>> results = new ArrayList<>();
        Map<Integer, Double> overallOutcome = new HashMap<>();

        while(it1.hasNext() && it2.hasNext()){

            Object patientOriginal = it1.next();
            Object patientAnonym = it2.next();

            Field[] fieldsPatientOriginal = patientOriginal.getClass().getDeclaredFields();
            Field[] fieldsPatientAnonym = patientAnonym.getClass().getDeclaredFields();
            Map<String, Double> result = new HashMap<>();

            for (int i=0; i<fieldsPatientOriginal.length; i++){
                fieldsPatientAnonym[i].setAccessible(true);
                fieldsPatientOriginal[i].setAccessible(true);
                double value = comp.compare(fieldsPatientOriginal[i].get(patientOriginal), fieldsPatientAnonym[i].get(patientAnonym),2.0);
                result.put(fieldsPatientOriginal[i].getName(), value);
            }

            sap sap = (sap) patientOriginal;
            overallOutcome.put(sap.getId(), getAbsolute(result));

            results.add(result);
        }
        System.out.println("----------Results:---------------");

        for (Object result : results) {
            HashMap<String, Double> temp = (HashMap<String, Double>) result;
            Set set = temp.entrySet();
            for (Object aSet : set) {
                Map.Entry me = (Map.Entry) aSet;
                System.out.println(me.getKey() + " : " + me.getValue());
            }
            System.out.println("-----------------------------");
        }

        return overallOutcome;
    }

    private double getAbsolute(Map<String,Double> entry){
        
        
        double sum = 0;

        Set set=entry.entrySet();
        for (Object aSet: set) {
            Map.Entry me = (Map.Entry) aSet;
            sum = sum + (Double) me.getValue();
            System.out.println("Key: " + me.getKey() + " Value: " + me.getValue());
        }
        return sum/(set.size()-1);
    }

    public HashMap<String, Double> compareOne(Patient p, Patient_anonym pa) throws IllegalAccessException{

        HashMap result = new HashMap();
        Field[] fieldPatient = p.getClass().getDeclaredFields();
        Field[] fieldPatientAnonym = pa.getClass().getDeclaredFields();

            for (int i=0; i<fieldPatientAnonym.length; i++) {
                fieldPatient[i].setAccessible(true);
                fieldPatientAnonym[i].setAccessible(true);
                result.put(fieldPatient[i].getName(), comp.compare(fieldPatient[i].get(p), fieldPatientAnonym[i].get(pa), 2.0));
            }
        Set set = result.entrySet();
        Iterator i = set.iterator();

        System.out.println("------CompareOne------------");
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
        System.out.println("-----------------------------");


        return result;
    }

    public double compareAttribute(Object a, Object b){

        return comp.compare(a,b,2.0);

    }

}
