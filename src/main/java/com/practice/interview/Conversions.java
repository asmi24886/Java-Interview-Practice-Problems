package com.practice.interview;

public class Conversions {

    private static void test() {

        // int to Integer
        int i1 = 1;
        Integer I1 = (Integer) i1;
        Integer I11 = Integer.valueOf(i1);

        // Integer to int
        Integer I2 = 1;

        //char to int
        int i2 = '5';
        //numeric value of integer in the form of character
        int i3 = Character.getNumericValue(i2);
        System.out.println(i3);

        //ascii value of character
        System.out.println((int) '5');

        //check if character is letter

        //check if character is digit

        //check if character is letter or digit

        //check if character is whitespace

        //check if ascii int is letter

        //check if ascii int is digit

        //check if ascii int is letter or digit

        //check if ascii int is whitespace
    }

    public static void main(String[] args) {
        test();
    }
}
