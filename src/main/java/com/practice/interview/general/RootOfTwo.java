package com.practice.interview.general;

public class RootOfTwo {

    public static void main(String [] args) {
        System.out.println(isMultipleOf2());
    }
    public static boolean isMultipleOf2() {
        int number = 64;

        while(number >= 1) {
            if(number % 2 == 1) return false;
            if(number == 1) return false;
            if(number == 2) return true;

            number = number/2;

        }
        return false;
    }
}
