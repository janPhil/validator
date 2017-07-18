package com.masterarbeit.compare;


/**
 * Created by jan-philippheinrich on 18.07.17.
 */
public class StringCompare implements ComparerInterface {

    private final IntegerComp integerComp;

    public StringCompare(IntegerComp integerComp) {
        this.integerComp = integerComp;
    }

    private Integer StringToInt(String x) {

        return Integer.parseInt(x);

    }

    @Override
    public double compare(Object a, Object b, int sig) {

        String x = ((String) a);
        String y = ((String) b);

        if (x.matches("[A-z]+[0-9]$") && y.matches("[A-z]+[0-9]$")){
                System.out.println("String ist alphanumerisch " + x);
            } else if (x.matches("[0-9]+") && y.matches("[0-9]+")){
                System.out.println("String ist numerisch " + x);
                return this.integerComp.compare( StringToInt(((String) a)), StringToInt(((String) b)),1);
            }
            // @To-Do check if BOTH Obj. are same type!!!
            else
                System.out.println("String ist alpha " + x);


        return 0;
    }
}
