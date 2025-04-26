package com.practice.interview;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class Main {

    /*
            Things to look into:
            1. Integer to int
            2. int to Integer
            3. Integer to Character
            4. Character to Integer
            5. Character to String
     */
    public static void main(String[] args) {
        String input = "aabbcdeeeff";

        /********************************************************/

        char[] charArr = input.toCharArray();
        Character ch = (char) 15;

        /********************************************************/

        IntStream.rangeClosed(1, 500).boxed().map(
                i -> (char) i.intValue()
        );

        /********************************************************/

        Deque<Character> dq = new LinkedList<>();

        for(int i = 0; i < charArr.length; i++) {

            if(dq.contains(charArr[i])) continue;

            if(charArr[i] == 'a' || charArr[i] == 'b') {
                dq.addLast(charArr[i]);
            }
            else {
                dq.addFirst(charArr[i]);
            }
        }

        System.out.println(dq);

        /********************************************************/

        new String(charArr).chars().mapToObj(i -> (char) i).forEach(System.out::println);

        /*********************************************************/

        int value = 5;
        int doubleValue = (int) Math.ceil((double)value/2);
        int intValue = value/2;

        System.out.println(intValue + " ---- " + doubleValue);
    }


    public void data_type_transform() {
        //Integer to int And back -> this can also be auto boxed and unboxed
        Integer a = Integer.valueOf(10);
        int b = a.intValue();

        //char To Character And back -> this can also be auto boxed and unboxed
        Character charA = Character.valueOf('z');
        char charB = Character.valueOf(charA);

        //Integer to Character And back
        Integer aa = 11;
        char charAA = (char) aa.intValue();
        Character charBB = (char) aa.intValue();

        int cc = Character.getNumericValue(charAA);

        String s = String.valueOf(charBB);

    }

}