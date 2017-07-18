package com.masterarbeit.compare;

import java.util.ArrayList;

import static java.lang.Math.E;
import static java.lang.Math.pow;

/**
 * Created by jan-philippheinrich on 18.07.17.
 */
public class LongComp implements ComparerInterface {

    private ArrayList<Integer> LongToInt(long x) {
        ArrayList<Integer> array = new ArrayList<>();
        String temp = String.valueOf(x);
        temp = temp.replace(".", "");
        for (int i=0; i<temp.length(); i++){
            char c = temp.charAt(i);
            array.add(Character.getNumericValue(c));
        }
        return array;
    }

    @Override
    public double compare(Object a, Object b, int sig) {

        ArrayList<Integer> org = (LongToInt(((long) a)));
        ArrayList<Integer> anonymus = (LongToInt(((long) b)));

        if (org.size() != anonymus.size()) {
            System.out.println("Long unequal size - ToDo: handle exception");
            return 0.0;

        }
        int length = org.size();
        double sum = 0.0;

        for (int i = 0; i < length; i++) {

            double q = org.get(i) - anonymus.get(i);
            double x = q / sig;
            double v = pow(x, 2);
            double f = -0.5 * v;
            double result = pow(E, f);
            sum += result;
        }
        return sum / length;
        
    }
}
