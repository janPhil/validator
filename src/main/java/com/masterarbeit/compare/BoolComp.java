package com.masterarbeit.compare;

/**
 * Created by jan-philippheinrich on 17.08.17.
 */
public class BoolComp implements ComparerInterface {
    @Override
    public double compare(Object a, Object b, double sig) {

        boolean _a = (Boolean)a;
        boolean _b = (Boolean)b;

        if (_a == _b)
            return 0.0;
        return 1.0;


    }
}
