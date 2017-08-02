package com.masterarbeit.compare;

import com.masterarbeit.Patient;
import com.masterarbeit.Patient_anonym;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Jan on 25.04.2017.
 */
public abstract class CompareService {

    private IntegerComp intCompare = new IntegerComp();
    private DateComp dateCompare = new DateComp(intCompare);
    private DoubleComp doubleCompare = new DoubleComp(intCompare);
    private StringComp stringCompare = new StringComp(intCompare);
    private Comparer comp = new Comparer(intCompare,doubleCompare,dateCompare,stringCompare);

    public List<Map<String, Double>> doCompare(List<Patient> patients, List<Patient_anonym> patient_anonym) throws IllegalAccessException {

        Iterator<Patient> it1 = patients.iterator();
        Iterator<Patient_anonym> it2 = patient_anonym.iterator();
        List<Map<String, Double>> results = new ArrayList<>();

        while(it1.hasNext() && it2.hasNext()){

            Object patientOriginal = it1.next();
            Object patientAnonym = it2.next();

            Field[] fieldsPatientOriginal = patientOriginal.getClass().getDeclaredFields();
            Field[] fieldsPatientAnonym = patientAnonym.getClass().getDeclaredFields();
            Map<String, Double> result = new HashMap<>();

            for (int i=0; i<fieldsPatientOriginal.length; i++){

                fieldsPatientAnonym[i].setAccessible(true);
                fieldsPatientOriginal[i].setAccessible(true);
                result.put(fieldsPatientOriginal[i].getName(), comp.compare(fieldsPatientOriginal[i].get(patientOriginal), fieldsPatientAnonym[i].get(patientAnonym), 1));
            }
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

        return results;

    }


    public HashMap<String, Double> compareOne(Patient p, List<Patient_anonym> patient_anonym) throws IllegalAccessException{

        HashMap result = new HashMap();
        Iterator it = patient_anonym.iterator();
        Field[] fieldPatient = p.getClass().getDeclaredFields();


        while (it.hasNext()){
            Object patientAnonym = it.next();

            Field[] fieldPatientAnonym = patientAnonym.getClass().getDeclaredFields();
            for (int i=0; i<fieldPatientAnonym.length; i++) {
                fieldPatient[i].setAccessible(true);
                fieldPatientAnonym[i].setAccessible(true);
                result.put(fieldPatient[i].getName(), comp.compare(fieldPatient[i].get(p), fieldPatientAnonym[i].get(patientAnonym), 1));

            }

        }

        Set set = result.entrySet();

        Iterator i = set.iterator();

        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }


        return result;
    }

}
