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

    private HashMap<Character, Integer> StringToHashMap(String s){

        char[] _s = s.toLowerCase().toCharArray();
        HashMap<Character, Integer> result = new HashMap<>();

        for (Character c: _s) {
            Integer val = result.get(new Character(c));
            if (val!=null)
                result.put(c, val+1);
            else
                result.put(c,1);
        }
        return result;
    }

    private int findCharInStringRight(char x, String a, int pos){

        if (pos>=a.length())
            pos = a.length()-1;

        int distance=0;
        for (int i=pos; i<a.length();i++){
            if (a.charAt(i)==x)
                return distance;
            distance++;
        }
        return -1;
    }

    private int findCharInStringLeft(char x, String a, int pos){

        if (pos>=a.length())
            pos = a.length()-1;

        int distance=0;
        for (int i=pos; i>=0;i--){
            if (a.charAt(i)==x)
                return distance;
            distance++;
        }
        return -1;
    }

    private int[] getTranspositions(String _a, String _b) {

        String a;
        String b;
        int length;

        if (_a.length()>_b.length()) {
            length = _a.length();
            a = _a;
            b = _b;
        } else {
            length = _b.length();
            a = _b;
            b = _a;
        }

        int[] right= new int[length];
        int[] left = new int[length];

        for (int i=0; i<length; i++){
            right[i] = findCharInStringRight(a.charAt(i), b, i);
            left[i] = findCharInStringLeft(a.charAt(i), b, i);
        }

        int[] res = new int[length];
        for (int i=0; i<length;i++){

            if (right[i]>left[i] && right[i]>-1) {
                res[i] = right[i];
                System.out.println("re größer li");
            }
            if (left[i]>right[i] && left[i]>-1) {
                res[i] = right[i];
                System.out.println("li größer re");
            }
            if (left[i]==right[i] && left[i]>-1) {
                res[i] = right[i];
                System.out.println("li gl re");
            }
            if (left[i]==right[i] && left[i]==-1){
                res[i] = -1;
            }
        }

        int count = 0;
        for (Integer i: res) {
            if (i!=-1)
                count++;
        }
        int[] result = new int[count];
        int temp=0;
        for (Integer i: res) {
            if (i!=-1) {
                result[temp] = i;
                temp++;
            }
        }
        return result;

    }

    private HashMap<Character, Integer> getDistribution(String a, String b) {

        HashMap<Character, Integer> _a = StringToHashMap(a);
        HashMap<Character, Integer> _b = StringToHashMap(b);
        HashMap<Character, Integer> transpositions = new HashMap<>();
        Set<Character> total = new HashSet<>();
        total.addAll(_a.keySet());
        total.addAll(_b.keySet());

        for (Character c:total) {
            int x=0;
            int y=0;

            if (_a.get(c)!=null)
                x=_a.get(c);
            if (_b.get(c)!=null)
                y=_b.get(c);

            transpositions.put(c, Math.abs(x-y));
        }
         return transpositions;

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
        System.out.println("CL length: " + length);
        double result = 1.0 - (exp(-0.5 * (Math.pow(length/sig, 2))));
        System.out.println("compareLength: " + result);
        return result;
    }

    private double compareContent(String a, String b, double sig) {

        double order = compareOrder(a, b, sig);
        double distribution = compareDistribution(a, b, sig);
        double result = 0.5 * (order + distribution);
        System.out.println("compareContent: " + result);
        return result;
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
            sum += 1.0 - exp(-0.5 * (Math.pow(d / sig, 2)));
        }
        int size = distributions.size();
        if (size==0)
            return sum;
        System.out.println("compareDistribution: " + sum * (1.0 / size));
        return (sum * (1.0 / size));
    }

    private double compareOrder(String a, String b, double sig) {

        int[] transpositions = getTranspositions(a, b);
        System.out.println(a);
        System.out.println(b);
        System.out.println(Arrays.toString(transpositions));
        if (transpositions.length == 0)
            return 0.0;
        double sum = 0.0;

        for (Integer i : transpositions) {
            sum = sum + (1.0-exp(-0.5 * Math.pow(i / sig, 2.0)));
        }

        System.out.println("compareOrder: " + (sum * (1.0/transpositions.length)));
        return (sum * (1.0/transpositions.length));
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


        double result = 0.5 * (compareLength(_a, _b, sig) + compareContent(_a, _b, sig));
        System.out.println("Compare: " + result);
        return result;
    }
}
