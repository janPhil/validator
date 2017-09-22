package com.masterarbeit.compare;

import static java.lang.Math.pow;

/**
 * Created by jan-philippheinrich on 17.07.17.
 */
public class DoubleComp implements ComparerInterface {

    private final IntegerComp integerComp;

    private int DoubleToInt(double x) {
        String temp = String.valueOf(x);
        temp = temp.replace(".", "");
        return Integer.parseInt(temp);
    }

    DoubleComp(IntegerComp i1){
        this.integerComp = i1;
    }

    @Override
    public double compare(Object a, Object b, double sig) {
        System.out.println(((compareCommaPosition(((Double) a), ((Double) b)) + this.integerComp.compare(DoubleToInt(((Double) a)), DoubleToInt(((Double) b)), sig))/2.0));
        return ((compareCommaPosition(((Double) a), ((Double) b)) + this.integerComp.compare(DoubleToInt(((Double) a)), DoubleToInt(((Double) b)), sig))/2.0);

    }

    private double compareCommaPosition(double a, double b){
        String tempA = String.valueOf(a);
        int commaA = tempA.indexOf(".");
        String tempB = String.valueOf(b);
        int commaB = tempB.indexOf(".");

        double q = commaA - commaB;
        if (q==0.0)
            return 0.0;
        return 1.0;
    }
}