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
    private final LongComp longComp;
    private final StringCompare stringCompare;

    Comparer(IntegerComp intComp, DoubleComp doubleComp, DateComp dateComp, LongComp longComp, StringCompare stringCompare){

        this.intComp = intComp;
        this.doubleComp = doubleComp;
        this.dateComp = dateComp;
        this.longComp = longComp;
        this.stringCompare = stringCompare;
    }

    @Override
    public double compare(Object a, Object b, int sig) {

        if (a.getClass() != b.getClass())
            return 0.0;

        if (a.getClass() == Integer.class){
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
        if (a.getClass() == Long.class){
            System.out.println("Long");
            System.out.println(this.longComp.compare(a,b,sig));
        }
        if (a.getClass() == String.class){
            System.out.println("String");
            return this.stringCompare.compare(a,b,1);
        }

        System.out.println(a.getClass());

        return 0.0;
    }
}
