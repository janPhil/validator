package com.masterarbeit.anonymize;

import com.masterarbeit.DataProviderService;
import com.masterarbeit.Patient;
import com.masterarbeit.Patient_anonymized;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jan on 25.04.2017.
 */
public class AnonymizeService {


    DataProviderService providerService = new DataProviderService();


    public ArrayList<Patient_anonymized> anonymizePatients(List<Patient> patients) throws IOException {

        Integer amount = patients.size();

        ArrayList<Patient_anonymized> patient_anonymizeds = new ArrayList<>();

        ArrayList<String> firstNames = providerService.getFirst_Names(amount);
        ArrayList<String> lastNames = providerService.getLast_Names(amount);
        ArrayList<String> mails = providerService.getMailAddress(amount);
        ArrayList<Long> phonenumber = providerService.getPhonenumber(amount);

        Integer count = 0;
        for (Patient p: patients) {
            Patient_anonymized anonymus = new Patient_anonymized();
            anonymus.setId(count);
            anonymus.setFirstName(firstNames.get(count));
            anonymus.setLastName(lastNames.get(count));
            anonymus.setEmail(mails.get(count));
            anonymus.setPhoneNumber(phonenumber.get(count));
            anonymus.setInsurance("XXXX");
            anonymus.setInsuranceNumber(1234);
            patient_anonymizeds.add(anonymus);
            count++;
        }

        return patient_anonymizeds;
    }



}
