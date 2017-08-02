package com.masterarbeit.compare;

import java.util.*;

import static java.lang.Math.exp;

/**
 * Created by jan-philippheinrich on 27.07.17.
 */
public class StringComp implements ComparerInterface {


    private final IntegerComp integerComp;

    public StringComp(IntegerComp integerComp) {
        this.integerComp = integerComp;
    }

    private Long StringToLong(String x) {

        return Long.parseLong(x);

    }


    private List<Integer> getTranspositions(String a, String b) {

        a = a.toLowerCase();
        b = b.toLowerCase();

        List<Integer> results = new LinkedList<>();


        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            boolean leftMatch = false;
            boolean rightMatch = false;
            int distance;
            for (distance = 0; distance < b.length(); distance++) {
                if (i + distance > b.length()) {
                    int dis=0;
                    for (int x=b.length()-1; x>=0;x--){
                        if (c == b.charAt(x)) {
                            results.add(dis+1);
                        }else {
                            dis++;
                        }
                    }
                    //continue;
                }
                if (i < b.length()) {
                    if (i >= distance) {
                        if (c == b.charAt(i - distance)) {
                            leftMatch = true;
                            break;
                        }
                    }

                    if (i + distance < b.length()) {
                        if (c == b.charAt(i + distance)) {
                            rightMatch = true;
                            break;
                        }
                    }
                } else
                    break;
            }
            if (leftMatch || rightMatch) {
                //System.out.println(distance);
                results.add(distance);
            }
        }

        System.out.println("getTranspositions: " + results);
        return results;

    }

    private HashMap<Character, Integer> getDistribution(String a, String b) {

        char[] s1 = a.toLowerCase().toCharArray();
        char[] s2 = b.toLowerCase().toCharArray();
        HashMap<Character, Integer> charsInS1 = new HashMap<>();

        for (int i = 0; i < s1.length; i++) {
            char c = s1[i];
            Integer val = charsInS1.get(new Character(c));
            if (val != null) {
                charsInS1.put(c, new Integer(val + 1));
            } else {
                charsInS1.put(c, 1);
            }
        }
        HashMap<Character, Integer> charsInS2 = new HashMap<>();

        for (int j = 0; j < s2.length; j++) {
            char c = s2[j];
            Integer val = charsInS1.get(new Character(c));
            if (val == null) {
                continue;
            } else {
                if (charsInS2.get(new Character(c)) != null) {
                    Integer num = charsInS2.get(new Character(c));
                    charsInS2.put(c, new Integer(num + 1));
                } else
                    charsInS2.put(c, new Integer(1));
            }
        }
        System.out.println("getDistribution: " + charsInS2.entrySet());
        return charsInS2;
    }

    private double hammingDistance(String a, String b) {

        char[] s1 = a.toCharArray();
        char[] s2 = b.toCharArray();

        int shortest = Math.min(s1.length, s2.length);
        int longest = Math.max(s1.length, s2.length);

        double result = 0;
        for (int i = 0; i < shortest; i++) {
            if (s1[i] != s2[i]) result++;
        }

        result += longest - shortest;
        System.out.println(s1 + " " + s2 + " :" + result);
        return result;
    }

    private double compareLength(String a, String b, double sig) {

        double length = Math.abs(a.length() - b.length());
        System.out.println("compareLength: " + (1 - exp(-0.5 * (Math.pow(length / sig, 2)))));
        return 1 - exp(-0.5 * (Math.pow(length / sig, 2)));
    }

    private double compareContent(String a, String b, double sig) {

        double order = compareOrder(a, b, sig);
        double distribution = compareDistribution(a, b, sig);
        //double hamming = hammingDistance(a,b);

        System.out.println("compareContent: " + 0.5 * (order + distribution));
        return 0.5 * (order + distribution);
    }

    private double compareDistribution(String a, String b, double sig) {

        HashMap<Character, Integer> distributions = getDistribution(a, b);
        Set set = distributions.entrySet();
        Iterator i = set.iterator();
        double sum = 0;

        while (i.hasNext()) {
            Map.Entry<Character, Integer> entry = (Map.Entry) i.next();
            double d = entry.getValue();
            System.out.println(d + " " + entry.getKey());
            sum += exp(-0.5 * (Math.pow(d / sig, 2)));
        }
        int size = distributions.size();
        if (size==0)
            return sum;
        System.out.println("compareDistribution: " + sum * (1 / size));
        return (sum * (1 / size));
    }

    private double compareOrder(String a, String b, double sig) {

        List<Integer> transpositions = getTranspositions(a, b);
        if (transpositions.size() == 0)
            return 0.0;
        double sum = 0.0;
        for (Integer i : transpositions) {
            sum = sum + 1 - exp(-0.5 * Math.pow(i / sig, 2));
        }
        System.out.println("compareOrder: " + (sum * 1 / transpositions.size()));
        return (sum * 1 / transpositions.size());
    }

    @Override
    public double compare(Object a, Object b, double sig) {


        String _a = (String) a;
        String _b = (String) b;


        if (_a.matches("[A-z]+[0-9]$") && _b.matches("[A-z]+[0-9]$")){
            System.out.println("String ist alphanumerisch " + _a);
        } else if (_a.matches("[0-9]+") && _b.matches("[0-9]+")){
            System.out.println("String ist numerisch " + _a);
            return this.integerComp.compare( StringToLong(((String) a)), StringToLong(((String) b)),1.0);
        }
        // @To-Do check if BOTH Obj. are same type!!!
        else
            System.out.println("String ist alpha " + _a);




        double result = compareLength(_a, _b, sig) + compareContent(_a, _b, sig);
        System.out.println("Compare: " + result * 0.5);
        return (result * 0.5);
    }
}
