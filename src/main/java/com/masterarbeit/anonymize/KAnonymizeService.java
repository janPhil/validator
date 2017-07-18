package com.masterarbeit.anonymize;

import com.masterarbeit.DataProviderService;
import com.masterarbeit.Patient;
import com.masterarbeit.Patient_anonymized;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jan on 25.04.2017.
 */
public class KAnonymizeService {


    DataProviderService providerService = new DataProviderService();


    public ArrayList<Patient_anonymized> anonymizePatients(List<Patient> patients) throws IOException {


        int numberOfHits;
        Iterator<Patient> it1 = patients.iterator();
        while (it1.hasNext()){

            Field[] fieldsPatientOriginal = patients.getClass().getDeclaredFields();
            for(int i=0; i<fieldsPatientOriginal.length; i++){
                fieldsPatientOriginal[i].setAccessible(true);

                try {
                    if(fieldsPatientOriginal[i].getType() == String.class){
                        if (fieldsPatientOriginal[i].getName().toLowerCase().equals(("vor")));
                    }

                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }


        }

        return null;

        }



}
