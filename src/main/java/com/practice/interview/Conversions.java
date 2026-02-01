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
        System.out.println(Character.isLetter('c'));

        //check if character is digit
        System.out.println(Character.isDigit('5'));
        //check if character is letter or digit
        System.out.println(Character.isLetterOrDigit('6'));
        //check if character is whitespace
        Character.isWhitespace(' ');
        //check if ascii int is letter
        Character.isLetter((int) 'a');
        //check if ascii int is digit
        Character.isLetter((int) '7');
        //check if ascii int is letter or digit
        Character.isLetterOrDigit((int) 'a');
        //check if ascii int is whitespace
        System.out.println(Character.isWhitespace((int) ' '));

        //Integer to String
        Integer i4 = 12;
        i4.toString();

        //String to integer
        Integer.parseInt("12");
        Integer.valueOf("12");

        //Integer to Character then to string
        Integer i5 = 65;
        System.out.println(String.valueOf((char) i5.intValue()));
    }

    public static void main(String[] args) {
        test();
    }
}
