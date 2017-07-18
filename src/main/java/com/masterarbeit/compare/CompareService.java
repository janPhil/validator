package com.masterarbeit.compare;

import com.masterarbeit.Patient;
import com.masterarbeit.Patient_anonym;
import com.masterarbeit.Patient_anonymized;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jan on 25.04.2017.
 */
public abstract class CompareService {

    private IntegerComp intCompare = new IntegerComp();
    private DateComp dateCompare = new DateComp(intCompare);
    private DoubleComp doubleCompare = new DoubleComp(intCompare);
    private LongComp longComp = new LongComp();
    private StringCompare stringCompare = new StringCompare(intCompare);
    private Comparer comp = new Comparer(intCompare,doubleCompare,dateCompare,longComp, stringCompare);

    public void comparePatients(List<Patient> patients, List<Patient_anonym> patient_anonym) throws IllegalAccessException {

        Iterator<Patient> it1 = patients.iterator();
        Iterator<Patient_anonym> it2 = patient_anonym.iterator();

        while(it1.hasNext() && it2.hasNext()){
            Object patientOriginal = it1.next();
            Object patientAnonym = it2.next();


            Field[] fieldsPatientOriginal = patientOriginal.getClass().getDeclaredFields();
            Field[] fieldsPatientAnonym = patientAnonym.getClass().getDeclaredFields();


            for (int i=0; i<fieldsPatientOriginal.length; i++){
                fieldsPatientAnonym[i].setAccessible(true);
                fieldsPatientOriginal[i].setAccessible(true);
                System.out.println(comp.compare(fieldsPatientOriginal[i].get(patientOriginal), fieldsPatientAnonym[i].get(patientAnonym), 1));
            }

            /*
            for (Field f: fieldsPatientOriginal){
                f.setAccessible(true);
                if (f.getType() == String.class) {
                    if (f.get(patientOriginal).toString().matches("[A-z]+[0-9]$")){
                        System.out.println("String ist alphanumerisch " + f.getName() + " " +  f.get(patientOriginal).toString());
                    }
                    else if (f.get(patientOriginal).toString().matches("[0-9]+")){
                        System.out.println("String ist numerisch " + f.getName() + " " + f.get(patientOriginal).toString());
                    }
                    else
                        System.out.println("String ist alpha " + f.getName() + " " + f.get(patientOriginal).toString());
                }
                else if (f.getType() == Integer.class)
                    System.out.println("Integer: " + f.getName() + " " + f.get(patientOriginal));
                else if (f.getType() == long.class)
                    System.out.println("Long: " + f.getName() + " " +  f.get(patientOriginal));
                else if (f.getType() == Date.class)
                    System.out.println("Date: " + f.getName() + " " +  f.get(patientOriginal));
                else{
                    System.out.println("Keinen Datentyp für: " + f.getName() + " " + f.get(patientOriginal));

                }
            }*/
        }
    }

    public void comparePatientsAnonymized(List<Patient> patients, List<Patient_anonymized> patient_anonym){

        Iterator<Patient> it1 = patients.iterator();
        Iterator<Patient_anonymized> it2 = patient_anonym.iterator();

        while(it1.hasNext() && it2.hasNext()){
            Object patientOriginal = it1.next();
            Object patientAnonym = it2.next();

            Field[] fieldsPatientOriginal = patientOriginal.getClass().getDeclaredFields();
            Field[] fieldsPatientAnonym = patientAnonym.getClass().getDeclaredFields();

            for(int i=0; (i<fieldsPatientOriginal.length && i<fieldsPatientAnonym.length); i++){
                fieldsPatientOriginal[i].setAccessible(true);
                fieldsPatientAnonym[i].setAccessible(true);
                try {
                    System.out.println(fieldsPatientOriginal[i].get(patientOriginal) + " || " + fieldsPatientAnonym[i].get(patientAnonym));
                    System.out.println(fieldsPatientOriginal[i].getType() + " || " + fieldsPatientAnonym[i].getType());
                    System.out.println(fieldsPatientOriginal[i].getName());
                    if (fieldsPatientOriginal[i].getType() == String.class){
                        String s1 = (String) fieldsPatientOriginal[i].get(patientOriginal);
                        String s2 = (String) fieldsPatientAnonym[i].get(patientAnonym);
                        System.out.println(StringCompare(s1, s2));
                    }
                    if (fieldsPatientOriginal[i].getType() == Integer.class){
                        System.out.println("Integer");
                    }


                } catch (IllegalArgumentException | IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
    }


    public double StringCompare(String s1, String s2){

        double result = 100.0;

        if (s1.equals(s2)) {
            result -= 100.0;
            return result;
        }

        if(s1.length() == s2.length())
            result -= 5.0;

        char[] charArray = s1.toCharArray();

        for (int i=0; i<s1.length(); i++){
            if (s2.contains(""+s1.charAt(i))){
                result -= 2.0;
                System.out.println("-2.0 für: " + s1.charAt(i));
            }
        }

        return result;
    }



}
