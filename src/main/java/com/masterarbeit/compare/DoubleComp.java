package com.masterarbeit.compare;

import static java.lang.Math.E;
import static java.lang.Math.pow;

/**
 * Created by jan-philippheinrich on 17.07.17.
 */
public class DoubleComp implements ComparerInterface {

    private final IntegerComp integerComp;

    private int DoubleToInt(double x) {
        System.out.println(x);
        String temp = String.valueOf(x);
        temp = temp.replace(".", "");
        return Integer.parseInt(temp);
    }

    DoubleComp(IntegerComp i1){
        this.integerComp = i1;
    }

    @Override
    public double compare(Object a, Object b, double sig) {

        return (compareCommaPosition(((Double) a), ((Double) b)) + this.integerComp.compare(DoubleToInt(((Double) a)), DoubleToInt(((Double) b)), sig))/2.0;

    }

    double compareCommaPosition(double a, double b){
        String tempA = String.valueOf(a);
        int commaA = tempA.indexOf(".");
        String tempB = String.valueOf(b);
        int commaB = tempB.indexOf(".");

        double q = commaA - commaB;
        double x = q / 1;
        double v = pow(x, 2);
        double p = -0.5 * v;
        return pow(E, p);
    }
}