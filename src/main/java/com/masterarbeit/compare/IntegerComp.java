package com.masterarbeit.compare;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.E;
import static java.lang.Math.pow;

/**
 * Created by jan-philippheinrich on 05.07.17.
 */
public class IntegerComp implements ComparerInterface {

    ArrayList<Integer> splitInteger(int x) {
        int temp = x;
        ArrayList<Integer> array = new ArrayList<>();
        while (temp > 0) {
            array.add(temp % 10);
            temp = temp / 10;
        }
        Collections.reverse(array);
        return array;
    }

    @Override
    public double compare(Object a, Object b, int sig) {

        ArrayList<Integer> org = (splitInteger(((Integer) a)));
        ArrayList<Integer> anonymus = (splitInteger(((Integer) b)));

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

