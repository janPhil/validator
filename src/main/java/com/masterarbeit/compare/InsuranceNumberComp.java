package com.masterarbeit.compare;

import java.text.ParseException;
import java.time.LocalDate;

/**
 * Created by jan-philippheinrich on 07.09.17.
 */
public class InsuranceNumberComp implements ComparerInterface {

    private IntegerComp integerComp;
    private DateComp dateComp;

    InsuranceNumberComp(IntegerComp i1, DateComp d1){
        this.integerComp = i1;
        this.dateComp = d1;
    }


    @Override
    public double compare(Object a, Object b, double sig) throws ParseException {

        String _a = (String) a;
        String _b = (String) b;

        String regionCode_a = _a.substring(0,2);
        String regionCode_b = _b.substring(0,2);

        double region = integerComp.compare(regionCode_a, regionCode_b, sig);

        LocalDate dateA = LocalDate.of(Integer.parseInt("19" + _a.substring(6,8)), Integer.parseInt(_a.substring(4,6)), Integer.parseInt(_a.substring(2,4)));
        LocalDate dateB = LocalDate.of(Integer.parseInt("19" + _b.substring(6,8)), Integer.parseInt(_b.substring(4,6)), Integer.parseInt(_b.substring(2,4)));

        double birthday = dateComp.compare(dateA, dateB, sig);

        String sex_a = _a.substring(9,11);
        String sex_b = _b.substring(9,11);

        double sex = integerComp.compare(sex_a, sex_b,sig);

        String checkNumber_a = _a.substring(11,12);
        String checkNumber_b = _b.substring(11,12);

        double checkNumber = integerComp.compare(checkNumber_a,checkNumber_b,sig);

        double result = (region+birthday+sex+checkNumber)/4;

        return result;
    }
}
