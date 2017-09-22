package com.masterarbeit.compare;

import java.text.ParseException;

/**
 * Created by jan-philippheinrich on 18.07.17.
 */
public interface ComparerInterface {

    double compare(Object a, Object b, double sig) throws ParseException;

}
