package com.codecool;

public class helpers {

    public static void main(String[] args) {

        double doubleNumber = 24.04;
        String doubleAsString = String.valueOf(doubleNumber);
        String[] split = doubleAsString.split("\\.");
        String x = split[0];
        String y = split[1];
        int ix = Integer.parseInt(x);
        int iy = Integer.parseInt(y);
        System.out.println(x);
        System.out.println(y);

    }

}
