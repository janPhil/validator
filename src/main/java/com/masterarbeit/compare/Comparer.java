package com.masterarbeit.compare;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by jan-philippheinrich on 18.07.17.
 */
public class Comparer implements ComparerInterface {

    private final IntegerComp intComp;
    private final DoubleComp doubleComp;
    private final DateComp dateComp;
    private final StringComp stringComp;

    Comparer(IntegerComp intComp, DoubleComp doubleComp, DateComp dateComp, StringComp stringComp){

        this.intComp = intComp;
        this.doubleComp = doubleComp;
        this.dateComp = dateComp;
        this.stringComp = stringComp;
    }

    @Override
    public double compare(Object a, Object b, double sig) {

        if (a.getClass() != b.getClass()) {
            System.out.println("Error: Objects are not same type");
            return 0.0;
        }
        if (a.getClass() == Integer.class || a.getClass() == Long.class){
            System.out.println("int");
            return this.intComp.compare(a,b,sig);
        }
        if (a.getClass() == Double.class){
            System.out.println("double");
            return this.doubleComp.compare(a,b,sig);
        }
        if (a.getClass() == LocalDate.class || a.getClass() == Date.class){
            System.out.println("Date");
            return this.dateComp.compare(a,b,sig);
        }
        if (a.getClass() == String.class){
            return this.stringComp.compare(a,b,sig);
        }

        return 0.0;
    }
}
